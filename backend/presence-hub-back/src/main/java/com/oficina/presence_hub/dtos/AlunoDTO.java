package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AlunoDTO(Long id, String nome, String email, String senha,
                       @JsonInclude(JsonInclude.Include.NON_EMPTY) List<ParticipacaoDTO> participacoes,
                       @JsonInclude(JsonInclude.Include.NON_EMPTY) List<CertificadoDTO> certificados) {

    public AlunoDTO {
        participacoes = participacoes != null ? participacoes : new ArrayList<>();
        certificados = certificados != null ? certificados : new ArrayList<>();
    }
}
