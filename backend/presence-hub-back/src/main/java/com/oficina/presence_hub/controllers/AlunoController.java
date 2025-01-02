package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<AlunoDTO> createAluno(@Valid @RequestBody AlunoDTO alunoDto) {
        AlunoDTO aluno = alunoService.createAluno(alunoDto);
        return ResponseEntity.ok(aluno);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAllAlunos() {
        List<AlunoDTO> alunos = alunoService.getAllAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable Long id) {
        AlunoDTO aluno = alunoService.getAlunoById(id);
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> updateAluno(@PathVariable Long id, @Valid @RequestBody AlunoDTO alunoDto) {
        AlunoDTO updatedAluno = alunoService.updateAluno(id, alunoDto);
        return ResponseEntity.ok(updatedAluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable Long id) {
        alunoService.deleteAluno(id);
        return ResponseEntity.noContent().build();
    }
}