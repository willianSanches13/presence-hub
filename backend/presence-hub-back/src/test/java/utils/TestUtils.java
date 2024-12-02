package utils;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TestUtils {

    public static AlunoDTO buildAlunoDTO() {
        return AlunoDTO.builder()
                .id(1L)
                .nome("John Doe")
                .email("john.doe@example.com")
                .senha("password")
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
                .aluno(buildAlunoDTO())
                .workshop(buildWorkshopDTOWithoutParticipacoes())
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

    public static WorkshopDTO buildWorkshopDTOWithoutParticipacoes() {
        return WorkshopDTO.builder()
                .id(1L)
                .titulo("New Workshop")
                .descricao("New Description for new workshop")
                .data(LocalDate.now())
                .build();
    }
}