package com.oficina.presence_hub.dtos;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

public record CertificadoDTO(Long id, AlunoDTO aluno, WorkshopDTO workshop, String assinaturaDigital, LocalDate dataEmissao) {}
