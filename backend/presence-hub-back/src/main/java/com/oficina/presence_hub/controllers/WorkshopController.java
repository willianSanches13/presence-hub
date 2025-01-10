package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {

    @Autowired
    private WorkshopService workshopService;

    @PostMapping
    public ResponseEntity<WorkshopDTO> createWorkshop(@RequestBody WorkshopDTO workshopDto) {
        WorkshopDTO createdWorkshop = workshopService.createWorkshop(workshopDto);
        return ResponseEntity.ok(createdWorkshop);
    }

    @GetMapping
    public ResponseEntity<List<WorkshopDTO>> getAllWorkshops() {
        List<WorkshopDTO> workshops = workshopService.getAllWorkshops();
        return ResponseEntity.ok(workshops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkshopDTO> getWorkshopById(@PathVariable Long id) {
        WorkshopDTO workshop = workshopService.getWorkshopById(id);
        return ResponseEntity.ok(workshop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkshopDTO> updateWorkshop(@PathVariable Long id, @RequestBody WorkshopDTO workshopDto) {
        WorkshopDTO updatedWorkshop = workshopService.updateWorkshop(id, workshopDto);
        return ResponseEntity.ok(updatedWorkshop);
    }

    @PutMapping("/{workShopId}/participacoes/alunos/{alunoId}")
    public ResponseEntity<WorkshopDTO> updateParticipacoes(
            @PathVariable Long workShopId,
            @PathVariable Long alunoId,
            @RequestParam boolean presenca) {
        workshopService.updateParticipacoes(workShopId, alunoId, presenca);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long id) {
        workshopService.deleteWorkshop(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{workShopId}/certificados/alunos/{alunoId}")
    public ResponseEntity<WorkshopDTO> createCertificados(
            @PathVariable Long workShopId,
            @PathVariable Long alunoId) {
        workshopService.createCertificados(workShopId, alunoId);
        return ResponseEntity.noContent().build();
    }
}