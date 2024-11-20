package com.oficina.presence_hub.dtos;

public record ParticipacaoDTO(Long id, AlunoDTO aluno, WorkshopDTO workshop, boolean presente) {}