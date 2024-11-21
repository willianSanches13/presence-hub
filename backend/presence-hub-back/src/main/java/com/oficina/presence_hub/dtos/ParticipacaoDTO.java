package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record ParticipacaoDTO(Long id, AlunoDTO aluno, WorkshopDTO workshop, boolean presente) { }