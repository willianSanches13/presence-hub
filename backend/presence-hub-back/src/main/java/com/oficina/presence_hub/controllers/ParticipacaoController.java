package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.entities.Participacao;
import com.oficina.presence_hub.services.ParticipacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participacoes")
public class ParticipacaoController {

    @Autowired
    private ParticipacaoService participacaoService;

    @GetMapping
    public ResponseEntity<List<ParticipacaoDTO>> getAllParticipacoes() {
        List<ParticipacaoDTO> participacoes = participacaoService.getAllParticipacoes();
        return ResponseEntity.ok(participacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParticipacaoDTO> getParticipacaoById(@PathVariable Long id) {
        ParticipacaoDTO participacao = participacaoService.getParticipacaoById(id);
        return ResponseEntity.ok(participacao);
    }

}