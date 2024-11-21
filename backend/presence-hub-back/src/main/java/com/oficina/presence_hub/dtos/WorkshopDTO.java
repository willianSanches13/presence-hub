package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record WorkshopDTO(Long id, String titulo, String descricao, LocalDateTime data, @JsonInclude(JsonInclude.Include.NON_EMPTY) List<ParticipacaoDTO> participacoes) {
    public WorkshopDTO {
        participacoes = participacoes != null ? participacoes : new ArrayList<>();
    }
}
