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

    public Professor createProfessor(ProfessorDTO professorDTO) {
        Professor professor = professorMapper.toProfessor(professorDTO);
        return professorRepository.save(professor);
    }

    public List<ProfessorDTO> getAllProfessors() {
        List<Professor> professors = professorRepository.findAll();
        return professorMapper.toProfessorDTO(professors);
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
    }

    public Professor updateProfessor(Long id, ProfessorDTO professorDTO) {
        Professor professor = getProfessorById(id);
        professorMapper.updateProfessorFromDTO(professorDTO, professor);
        return professorRepository.save(professor);
    }

    public void deleteProfessor(Long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor not found"));
        professorRepository.delete(professor);
    }
}