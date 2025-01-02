package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
public record ParticipacaoDTO(Long id, AlunoDTO aluno, WorkshopDTO workshop, boolean presente) { }