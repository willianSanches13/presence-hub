package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.ProfessorDTO;
import com.oficina.presence_hub.entities.Professor;
import com.oficina.presence_hub.mappers.ProfessorMapper;
import com.oficina.presence_hub.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorMapper professorMapper;

    public ProfessorDTO createProfessor(ProfessorDTO professorDTO) {
        Professor professor = professorMapper.toProfessor(professorDTO);
        professor = professorRepository.save(professor);
        return professorMapper.toProfessorDTO(professor);
    }

    public List<ProfessorDTO> getAllProfessors() {
        List<Professor> professors = professorRepository.findAll();
        return professorMapper.toProfessorDTO(professors);
    }

    public ProfessorDTO getProfessorById(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        return professorMapper.toProfessorDTO(professor);
    }

    public ProfessorDTO updateProfessor(Long id, ProfessorDTO professorDTO) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        professorMapper.updateProfessorFromDTO(professorDTO, professor);
        professor = professorRepository.save(professor);
        return professorMapper.toProfessorDTO(professor);
    }

    public void deleteProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        professorRepository.delete(professor);
    }
}