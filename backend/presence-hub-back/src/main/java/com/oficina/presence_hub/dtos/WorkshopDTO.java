package com.oficina.presence_hub.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record WorkshopDTO(Long id, String titulo, String descricao, LocalDateTime data, List<ParticipacaoDTO> participacoes) {}
