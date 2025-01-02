package com.oficina.presence_hub.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.oficina.presence_hub.enums.UfEnum;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record EnderecoDTO(
        Long id,

        @Size(max = 255)
        String logradouro,

        @Size(max = 255)
        String bairro,

        @NotBlank
        @Size(min = 3, max = 255)
        String cidade,

        @NotNull
        String uf,

        @Size(max = 9)
        String cep,

        Integer numero,

        @Size(max = 255)
        String complemento
) {}