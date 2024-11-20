package com.oficina.presence_hub.dtos;

import java.util.List;

public record AlunoDTO(Long id, String nome, String email, String senha, List<ParticipacaoDTO> participacoes, List<CertificadoDTO> certificados) {}
