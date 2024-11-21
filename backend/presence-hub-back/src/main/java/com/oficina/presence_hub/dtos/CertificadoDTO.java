package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record CertificadoDTO(Long id, AlunoDTO aluno, WorkshopDTO workshop, String assinaturaDigital, LocalDate dataEmissao) {}
