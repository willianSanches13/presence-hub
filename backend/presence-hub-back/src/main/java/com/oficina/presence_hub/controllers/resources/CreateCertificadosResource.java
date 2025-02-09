package com.oficina.presence_hub.controllers.resources;

import java.util.List;
import lombok.Builder;

@Builder
public record CreateCertificadosResource(List<Long> alunosIds) {
}
