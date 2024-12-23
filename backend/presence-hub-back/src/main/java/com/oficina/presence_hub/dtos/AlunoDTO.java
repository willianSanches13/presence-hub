package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record AlunoDTO(Long id, String nome, String email,
                       Integer idade, String serie, String instituicaoDeEnsino,
                       String telefoneContato, String cidade, String estado,
                       String nomeResponsavel, String telefoneResponsavel,
                       String matriculaProjeto, String statusParticipacao,
                       LocalDate dataInscricao, String observacoes,
                       @JsonInclude(JsonInclude.Include.NON_EMPTY) List<ParticipacaoDTO> participacoes,
                       @JsonInclude(JsonInclude.Include.NON_EMPTY) List<CertificadoDTO> certificados) {

    public AlunoDTO {
        participacoes = participacoes != null ? participacoes : new ArrayList<>();
        certificados = certificados != null ? certificados : new ArrayList<>();
    }
}