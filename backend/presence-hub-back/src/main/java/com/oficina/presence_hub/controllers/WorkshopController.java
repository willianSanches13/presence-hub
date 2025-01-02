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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long id) {
        workshopService.deleteWorkshop(id);
        return ResponseEntity.noContent().build();
    }
}