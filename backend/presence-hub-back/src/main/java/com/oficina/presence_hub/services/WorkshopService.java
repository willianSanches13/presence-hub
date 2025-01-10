package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.entities.Participacao;
import com.oficina.presence_hub.entities.Professor;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.mappers.ParticipacaoMapper;
import com.oficina.presence_hub.mappers.WorkshopMapper;
import com.oficina.presence_hub.repositories.WorkshopRepository;
import java.util.Optional;
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

    @Autowired
    ParticipacaoService participacaoService;

    @Autowired
    CertificadoService certificadoService;

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

    public void updateParticipacoes(Long workShopId, Long alunoId, boolean presenca) {
        log.info("Updating participacoes for alunoId: {} and workShopId: {}", alunoId, workShopId);

        Workshop workshop = workshopRepository.findById(workShopId)
                .orElseThrow(() -> {
                    log.error("Workshop not found with id: {}", workShopId);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found");
                });

        Optional<Participacao> participacaoOpt = workshop.getParticipacoes().stream()
                .filter(participacao -> participacao.getAluno().getId().equals(alunoId))
                .findFirst();

        if (participacaoOpt.isPresent()) {
            log.info("Participacao found for alunoId: {} in workshopId: {}", alunoId, workShopId);
            participacaoService.updateParticipacao(participacaoOpt.get().getId(), presenca);
        } else {
            log.info("Participacao not found for alunoId: {} in workshopId: {}, creating new participacao", alunoId, workShopId);
            participacaoService.createParticipacao(alunoId, workShopId, presenca);
        }

        log.info("Participacoes updated successfully for alunoId: {} and workShopId: {}", alunoId, workShopId);
    }

    public void createCertificados(Long workshopId, Long alunoId){
        //todo, receber uma lista de alunos talvez
        certificadoService.createCertificado(workshopId, alunoId);
    }
}