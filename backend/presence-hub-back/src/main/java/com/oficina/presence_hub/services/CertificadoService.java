package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Certificado;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.mappers.CertificadoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import com.oficina.presence_hub.repositories.CertificadoRepository;
import com.oficina.presence_hub.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CertificadoService {

    @Autowired
    private CertificadoRepository certificadoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CertificadoMapper certificadoMapper;

    @Autowired
    private WorkshopRepository workshopRepository;

    public CertificadoDTO createCertificado(CertificadoDTO certificadoDto) {
        Certificado certificado = certificadoMapper.toCertificado(certificadoDto);
        Certificado certificadoOrm = certificadoRepository.save(certificado);
        certificadoOrm.setAluno(findAluno(certificadoDto.aluno().id()));
        certificadoOrm.setWorkshop(findWorkshop(certificadoDto.workshop().id()));
        return certificadoMapper.toCertificadoDTO(certificadoOrm);
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
        Certificado certificado = certificadoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado not found"));
        certificadoMapper.updateCertificadoFromDTO(certificadoDTO, certificado);
        return certificadoRepository.save(certificado);
    }

    public void deleteCertificado(Long id) {
        Certificado certificado = certificadoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Certificado not found"));
        certificadoRepository.delete(certificado);
    }

    private Aluno findAluno(Long id){
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    private Workshop findWorkshop(Long id){
       return  workshopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workshop não encontrado"));
    }
}