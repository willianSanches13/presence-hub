package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.mappers.AlunoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    public AlunoDTO createAluno(AlunoDTO alunoDto) {
        Aluno aluno = alunoRepository.save(alunoMapper.toAluno(alunoDto));
        return alunoMapper.toAlunoDTO(aluno);
    }

    public List<AlunoDTO> getAllAlunos() {
        List<Aluno> all = alunoRepository.findAll();
        return alunoMapper.toAlunoDTO(all);
    }

    public AlunoDTO getAlunoById(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        return alunoMapper.toAlunoDTO(aluno);
    }

    public Aluno updateAluno(Long id, AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoMapper.updateAlunoFromDTO(alunoDTO, aluno);
        return alunoRepository.save(aluno);
    }

    public void deleteAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoRepository.delete(aluno);
    }
}