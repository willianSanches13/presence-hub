package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.Builder;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
public record CertificadoDTO(Long id, AlunoDTO aluno, WorkshopDTO workshop, String assinaturaDigital, LocalDate dataEmissao) {}
