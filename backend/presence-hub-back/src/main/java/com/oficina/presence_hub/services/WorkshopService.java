package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.entities.Professor;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.mappers.ParticipacaoMapper;
import com.oficina.presence_hub.mappers.WorkshopMapper;
import com.oficina.presence_hub.repositories.WorkshopRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@Slf4j
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private WorkshopMapper workshopMapper;

    @Autowired
    private ParticipacaoMapper participacaoMapper;

    @Autowired
    private ProfessorService professorService;

    public WorkshopDTO createWorkshop(WorkshopDTO workshopDto) {
        log.info("Creating Workshop: {}", workshopDto);
        try {
            Workshop workshop = workshopMapper.toWorkshop(workshopDto);
            Professor professor = professorService.createProfessor(workshopDto.professor());
            workshop.setProfessor(professor);
            workshopRepository.save(workshop);
            log.info("Workshop created successfully: {}", workshop);
            return workshopMapper.toWorkshopDTO(workshop);
        } catch (Exception e) {
            log.error("Error creating Workshop", e);
            throw new RuntimeException("Error creating Workshop", e);
        }
    }

    public List<WorkshopDTO> getAllWorkshops() {
        log.info("Fetching all Workshops");
        List<Workshop> workshops = workshopRepository.findAll();
        log.info("Fetched {} Workshops", workshops.size());
        return workshopMapper.toWorkshopDTO(workshops);
    }

    public WorkshopDTO getWorkshopById(Long id) {
        log.info("Fetching Workshop by id: {}", id);
        Workshop workshop = workshopRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found"));
        WorkshopDTO workshopDTO = workshopMapper.toWorkshopDTO(workshop);
        List<ParticipacaoDTO> participacoes = workshop.getParticipacoes().stream().map(participacaoMapper::toParticipacaoDTO).toList();
        workshopDTO.participacoes().addAll(participacoes);
        log.info("Fetched Workshop: {}", workshopDTO);
        return workshopDTO;
    }

    public WorkshopDTO updateWorkshop(Long id, WorkshopDTO workshopDTO) {
        log.info("Updating Workshop with id: {}", id);
        Workshop workshop = workshopRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found"));
        Professor professor = professorService.updateProfessor(workshop.getProfessor().getId(), workshopDTO.professor());
        workshop.setProfessor(professor);
        workshopMapper.updateWorkshopFromDTO(workshopDTO, workshop);
        WorkshopDTO updatedWorkshopDTO = workshopMapper.toWorkshopDTO(workshopRepository.save(workshop));
        log.info("Workshop updated successfully: {}", updatedWorkshopDTO);
        return updatedWorkshopDTO;
    }

    public void deleteWorkshop(Long id) {
        log.info("Deleting Workshop with id: {}", id);
        Workshop workshop = workshopRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found"));
        workshopRepository.delete(workshop);
        log.info("Workshop deleted successfully");
    }
}