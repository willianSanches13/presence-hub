package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ProfessorDTO(Long id, String nome, String email, String telefoneContato,
                           String especializacao, String instituicaoVinculo) {
}