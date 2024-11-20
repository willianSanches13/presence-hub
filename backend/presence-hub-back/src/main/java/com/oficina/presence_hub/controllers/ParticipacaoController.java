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

    @PostMapping
    public ResponseEntity<ParticipacaoDTO> createParticipacao(@RequestBody ParticipacaoDTO participacaoDto) {
        ParticipacaoDTO createdParticipacao = participacaoService.createParticipacao(participacaoDto);
        return ResponseEntity.ok(createdParticipacao);
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<Participacao> updateParticipacao(@PathVariable Long id, @RequestBody ParticipacaoDTO participacaoDto) {
        Participacao updatedParticipacao = participacaoService.updateParticipacao(id, participacaoDto);
        return ResponseEntity.ok(updatedParticipacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipacao(@PathVariable Long id) {
        participacaoService.deleteParticipacao(id);
        return ResponseEntity.noContent().build();
    }
}