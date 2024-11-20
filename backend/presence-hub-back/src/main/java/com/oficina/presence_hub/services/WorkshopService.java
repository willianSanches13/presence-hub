package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.mappers.WorkshopMapper;
import com.oficina.presence_hub.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class WorkshopService {

    @Autowired
    private WorkshopRepository workshopRepository;

    @Autowired
    private WorkshopMapper workshopMapper;

    public Workshop createWorkshop(WorkshopDTO workshopDto) {
        return workshopRepository.save(workshopMapper.toWorkshop(workshopDto));
    }

    public List<WorkshopDTO> getAllWorkshops() {
        return workshopMapper.toWorkshopDTO(workshopRepository.findAll());
    }

    public WorkshopDTO getWorkshopById(Long id) {
        Workshop workshop = workshopRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found"));
        return workshopMapper.toWorkshopDTO(workshop);
    }

    public Workshop updateWorkshop(Long id, WorkshopDTO workshopDTO) {
        Workshop workshop = workshopRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found"));
        workshopMapper.updateWorkshopFromDTO(workshopDTO, workshop);
        return workshopRepository.save(workshop);
    }

    public void deleteWorkshop(Long id) {
        Workshop workshop = workshopRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Workshop not found"));
        workshopRepository.delete(workshop);
    }
}