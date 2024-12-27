package com.oficina.presence_hub.dtos;

import com.oficina.presence_hub.enums.SerieEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record AlunoDTO(
        Long id,

        @NotBlank
        @Size(min = 3, max = 255)
        String nome,

        @Email
        @Size(max = 255)
        String email,

        @NotNull
        @PastOrPresent
        LocalDate dataNascimento,

        @NotNull
        SerieEnum serie,

        EnderecoDTO endereco,

        @Size(max = 255)
        String instituicaoDeEnsino,

        @Size(max = 20)
        String telefoneContato,

        @Size(max = 255)
        String nomeResponsavel,

        @Size(max = 20)
        String telefoneResponsavel,

        @Size(max = 50)
        String matriculaProjeto,

        @NotNull
        LocalDate dataInscricao,

        String observacoes,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<ParticipacaoDTO> participacoes,

        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        List<CertificadoDTO> certificados
) {
    public AlunoDTO {
        participacoes = participacoes != null ? participacoes : new ArrayList<>();
        certificados = certificados != null ? certificados : new ArrayList<>();
    }
}