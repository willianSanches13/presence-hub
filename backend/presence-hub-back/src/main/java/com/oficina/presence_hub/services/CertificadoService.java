package com.oficina.presence_hub.services;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.StampingProperties;
import com.itextpdf.signatures.BouncyCastleDigest;
import com.itextpdf.signatures.IExternalDigest;
import com.itextpdf.signatures.IExternalSignature;
import com.itextpdf.signatures.PdfPKCS7;
import com.itextpdf.signatures.PdfSignatureAppearance;
import com.itextpdf.signatures.PdfSigner;
import com.itextpdf.signatures.PrivateKeySignature;
import com.itextpdf.signatures.SignatureUtil;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Certificado;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.mappers.CertificadoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import com.oficina.presence_hub.repositories.CertificadoRepository;
import com.oficina.presence_hub.repositories.WorkshopRepository;
import jakarta.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@Getter
@Setter
public class CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CertificadoMapper certificadoMapper;

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private EmailService emailService;

    public void createCertificado(Workshop workshop, Long alunoId) {
        log.info("Creating Certificado for workshopId: {} and alunoId: {}", workshop.getId(), alunoId);
        Certificado certificado = new Certificado();
        Aluno aluno = findAluno(alunoId);
        if (!alunoParticipouDoWorkshop(aluno, workshop)) {
            log.info("Aluno with id: {} did not participate in workshop with id: {}", alunoId, workshop.getId());
            return;
        }
        certificado.setAluno(aluno);
        certificado.setDataEmissao(LocalDate.now());
        certificado.setWorkshop(workshop);
        try {
            String url = gerarCertificado(certificado);
            certificado.setPath(url);
            log.info("Certificado generated successfully for alunoId: {}", alunoId);
            sendEmailWithCertificadoLink(aluno.getEmail(), url);
        } catch (Exception e) {
            log.error("Error generating Certificado for alunoId: {}", alunoId, e);
            throw new RuntimeException(e);
        }
        certificadoRepository.save(certificado);
    }


    public List<CertificadoDTO> getAllCertificados() {
        return certificadoMapper.toCertificadoDTO(certificadoRepository.findAll());
    }

    public CertificadoDTO getCertificadoById(Long id) {
        Certificado certificado = certificadoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado not found"));

        return certificadoMapper.toCertificadoDTO(certificado);
    }

    public Certificado updateCertificado(Long id, CertificadoDTO certificadoDTO) {
        log.info("Updating Certificado with id: {}", id);
        Certificado certificado = certificadoRepository.findById(id).orElseThrow(() -> {
            log.error("Certificado not found with id: {}", id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado not found");
        });
        certificadoMapper.updateCertificadoFromDTO(certificadoDTO, certificado);
        try {
            Certificado updatedCertificado = certificadoRepository.save(certificado);
            log.info("Certificado updated successfully with id: {}", id);
            return updatedCertificado;
        } catch (Exception e) {
            log.error("Error saving Certificado with id: {}", id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving Certificado", e);
        }
    }

    public void deleteCertificado(Long id) {
        Certificado certificado = certificadoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado not found"));
        certificadoRepository.delete(certificado);
    }

    private Aluno findAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    private Workshop findWorkshop(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workshop não encontrado"));
    }


    public String gerarCertificado(Certificado certificado) throws Exception {
        Document document = new Document(PageSize.A4.rotate());

        Aluno aluno = certificado.getAluno();
        Workshop workshop = certificado.getWorkshop();
        String fileName = aluno.getNome().replace(" ", "_") +
                LocalDateTime.now() + "_certificado.pdf";
        String caminhoCertificado = System.getenv("CERTIFICADO_PATH") + fileName;
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminhoCertificado));
        document.open();

        ClassPathResource imgFile = new ClassPathResource("templates/certificado.png");
        Image fundo = Image.getInstance(imgFile.getURL());

        float pageWidth = PageSize.A4.rotate().getWidth();
        float pageHeight = PageSize.A4.rotate().getHeight();
        fundo.scaleToFit(pageWidth, pageHeight);

        float xPosition = (pageWidth - fundo.getScaledWidth()) / 2;
        float yPosition = (pageHeight - fundo.getScaledHeight()) / 2;

        fundo.setAbsolutePosition(xPosition, yPosition);
        document.add(fundo);

        PdfContentByte canvas = writer.getDirectContent();
        Font fontTexto = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);

        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("Certificado de Participação", fontTexto),
                PageSize.A4.getWidth() - 175, 380, 0);

        Font fontCertificado = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.ITALIC, BaseColor.DARK_GRAY);
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("Certificamos que " + aluno.getNome() + " Participou do workshop " + workshop.getTitulo(), fontCertificado),
                PageSize.A4.getWidth() - 179, 300, 0);

        Font fontData = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.DARK_GRAY);
        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("Realizado em: " + workshop.getData(), fontData),
                PageSize.A4.getWidth() - 179, 250, 0);

        ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER,
                new Phrase("Com duração de " + workshop.getDuration(), fontData),
                PageSize.A4.getWidth() - 179, 230, 0);

        document.close();
        String certificadoAssinado = assinarCertificado(certificado, caminhoCertificado);

        return publishToGcpBucket(fileName, certificadoAssinado);
    }


    private String assinarCertificado(Certificado certificado, String caminhoPdf) throws Exception {

        String signerName = certificado.getWorkshop().getProfessor().getNome();

        Security.addProvider(new BouncyCastleProvider());

        String caminhoPdfAssinado = caminhoPdf.replace(".pdf", "_assinado.pdf");

        String senhaKeystore = System.getenv("KEYSTORE_PASSWORD");

        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(new FileInputStream(System.getenv("KEYSTORE_PATH")), senhaKeystore.toCharArray());

        String alias = keystore.aliases().nextElement();
        PrivateKey privateKey = (PrivateKey) keystore.getKey(alias, senhaKeystore.toCharArray());
        Certificate[] chain = keystore.getCertificateChain(alias);
        PdfReader reader = new PdfReader(caminhoPdf);
        FileOutputStream os = new FileOutputStream(caminhoPdfAssinado);

        PdfSigner signer = new PdfSigner(reader, os, new StampingProperties());

        PdfSignatureAppearance appearance = signer.getSignatureAppearance();
        appearance
                .setReason("Certificado emitido digitalmente")
                .setLocation("Cornélio Procópio - PR")
                .setReuseAppearance(false)
                .setLayer2Text("Digitally signed by " + signerName)
                .setLayer2Text("Digitally signed by " + signerName +
                        "\nLocation: Cornélio Procópio - PR\nReason: Certificado emitido digitalmente");

        appearance.setPageRect(new com.itextpdf.kernel.geom.Rectangle(400, 350, 50, 150));
        appearance.setPageNumber(1);

        IExternalDigest digest = new BouncyCastleDigest();
        IExternalSignature signature = new PrivateKeySignature(privateKey, "SHA-256", "BC");

        signer.signDetached(
                digest,
                signature,
                chain,
                null,
                null,
                null,
                0,
                PdfSigner.CryptoStandard.CMS
        );

        Files.delete(Path.of(caminhoPdf));

        return caminhoPdfAssinado;
    }

    private boolean alunoParticipouDoWorkshop(Aluno aluno, Workshop workshop) {
        if(aluno.getParticipacoes() == null){
            return false;
        }
        return aluno.getParticipacoes().stream()
                .anyMatch(e -> Objects.equals(e.getWorkshop().getId(), workshop.getId()) && e.isPresente());
    }

    private static String publishToGcpBucket(String fileName, String certificadoAssinado) throws IOException {
        String bucketName = System.getenv("GCP_BUCKET_NAME");
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of(bucketName, "certificados/" + fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        Path path = Path.of(certificadoAssinado);
        Blob blob = storage.createFrom(blobInfo, path);
        log.info("Certificado uploaded to GCP bucket: {}/certificados/{}", bucketName, fileName);
        Files.delete(path);
        return "https://storage.cloud.google.com/presence_hub_certificados/" + blob.getBlobId().getName();
    }


    private void sendEmailWithCertificadoLink(String email, String url) {
        String subject = "Seu certificado está pronto";
        try {
            emailService.sendEmail(email, subject, url);
        } catch (Exception e) {
            log.error("Error sending email to: {}", email, e);
        }
    }


    public boolean validarAssinatura(String base64) {
        Security.addProvider(new BouncyCastleProvider());
        String securityProvider = BouncyCastleProvider.PROVIDER_NAME;
        byte[] pdfBytes = Base64.getDecoder().decode(base64);
        try (PdfReader pdfReader = new PdfReader(new ByteArrayInputStream(pdfBytes)))  {
            PdfDocument pdfDocument = new PdfDocument(pdfReader);
            SignatureUtil signatureUtil = new SignatureUtil(pdfDocument);
            List<String> signatures = signatureUtil.getSignatureNames();

            if (signatures.isEmpty()) {
                log.info("No signatures found.");
                return false;
            }

            for (String signatureName : signatures) {
                PdfPKCS7 pkcs7 = signatureUtil.readSignatureData(signatureName, securityProvider);
                if (pkcs7 == null) {
                    log.info("Signature data is null for signature: {}", signatureName);
                    return false;
                }
                boolean isSignatureValid = pkcs7.verifySignatureIntegrityAndAuthenticity();
                log.info("Signature valid: {}", isSignatureValid);

                if (!isSignatureValid) {
                    log.info("Invalid signature found: {}", signatureName);
                    return false;
                }
            }
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        log.info("All signatures are valid.");
        return true;
    }
}