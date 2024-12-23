package utils;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.entities.Aluno;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class TestUtils {

    public static AlunoDTO buildAlunoDTOwithoutId() {
        return AlunoDTO.builder()
                .nome("John Doe")
                .email("john.doe@example.com")
                .idade(20)
                .serie("3rd Year")
                .instituicaoDeEnsino("Example University")
                .telefoneContato("123-456-7890")
                .cidade("Example City")
                .estado("Example State")
                .nomeResponsavel("Jane Doe")
                .telefoneResponsavel("098-765-4321")
                .matriculaProjeto("123456")
                .dataInscricao(LocalDate.now())
                .observacoes("No observations")
                .build();
    }

    public static AlunoDTO buildAlunoDTOwithId(Long id) {
        return AlunoDTO.builder()
                .id(id)
                .nome("Jane Doe")
                .email("jane.doe@example.com")
                .idade(20)
                .serie("3rd Year")
                .instituicaoDeEnsino("Example University")
                .telefoneContato("123-456-7890")
                .cidade("Example City")
                .estado("Example State")
                .nomeResponsavel("Jane Doe")
                .telefoneResponsavel("098-765-4321")
                .matriculaProjeto("123456")
                .dataInscricao(LocalDate.now())
                .observacoes("No observations")
                .build();
    }

    public static WorkshopDTO buildWorkshopDTO() {
        return WorkshopDTO.builder()
                .titulo("New Workshop")
                .descricao("New Description for new workshop")
                .data(LocalDate.now())
                .build();
    }

    public static ParticipacaoDTO buildParticipacaoDTO() {
        return ParticipacaoDTO.builder()
                .id(1L)
                .aluno(buildAlunoDTOwithoutId())
                .workshop(buildWorkshopDTOWithoutParticipacoes())
                .presente(true)
                .build();
    }

    public static ParticipacaoDTO buildParticipacaoDTOwithoutId() {
        return ParticipacaoDTO.builder()
                .aluno(AlunoDTO.builder().id(998L).build())
                .workshop(WorkshopDTO.builder().id(997L).build())
                .presente(true)
                .build();
    }

    public static CertificadoDTO buildCertificadoDTO() {
        return CertificadoDTO.builder()
                .id(1L)
                .assinaturaDigital("new-digital-signature")
                .dataEmissao(LocalDate.now())
                .build();
    }

    public static CertificadoDTO buildCertificadoDTOwithoutId() {
        return CertificadoDTO.builder()
                .assinaturaDigital("new-digital-signature")
                .dataEmissao(LocalDate.now())
                .aluno(AlunoDTO.builder().id(998L).build())
                .workshop(WorkshopDTO.builder().id(997L).build())
                .build();
    }

    public static WorkshopDTO buildWorkshopDTOWithoutParticipacoes() {
        return WorkshopDTO.builder()
                .id(1L)
                .titulo("New Workshop")
                .descricao("New Description for new workshop")
                .data(LocalDate.now())
                .build();
    }

    public static Aluno buildAluno() {
        return Aluno.builder()
                .id(1L)
                .nome("Test Aluno")
                .email("test@example.com")
                .idade(20)
                .serie("10th Grade")
                .instituicaoDeEnsino("Test School")
                .telefoneContato("1234567890")
                .cidade("Test City")
                .estado("Test State")
                .nomeResponsavel("Test Parent")
                .telefoneResponsavel("0987654321")
                .matriculaProjeto("12345")
                .dataInscricao(LocalDate.now())
                .observacoes("No observations")
                .participacoes(Collections.emptyList())
                .certificados(Collections.emptyList())
                .build();
    }
}