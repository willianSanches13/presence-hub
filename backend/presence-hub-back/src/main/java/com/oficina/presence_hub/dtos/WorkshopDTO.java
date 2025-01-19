package com.oficina.presence_hub.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Builder
public record WorkshopDTO(Long id, String titulo, String descricao, LocalDate data, LocalTime horaInicio, LocalTime horaFim, ProfessorDTO professor, boolean certificadosGerados, @JsonInclude(JsonInclude.Include.NON_EMPTY) List<ParticipacaoDTO> participacoes) {
    public WorkshopDTO {
        participacoes = participacoes != null ? participacoes : new ArrayList<>();
    }
}
