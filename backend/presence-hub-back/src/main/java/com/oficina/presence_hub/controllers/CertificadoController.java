package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.entities.Certificado;
import com.oficina.presence_hub.services.CertificadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @PostMapping
    public ResponseEntity<CertificadoDTO> createCertificado(@RequestBody CertificadoDTO certificadoDto) {
        CertificadoDTO createdCertificado = certificadoService.createCertificado(certificadoDto);
        return ResponseEntity.ok(createdCertificado);
    }

    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> getAllCertificados() {
        List<CertificadoDTO> certificados = certificadoService.getAllCertificados();
        return ResponseEntity.ok(certificados);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificadoDTO> getCertificadoById(@PathVariable Long id) {
        CertificadoDTO certificado = certificadoService.getCertificadoById(id);
        return ResponseEntity.ok(certificado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificado> updateCertificado(@PathVariable Long id, @RequestBody CertificadoDTO certificadoDto) {
        Certificado updatedCertificado = certificadoService.updateCertificado(id, certificadoDto);
        return ResponseEntity.ok(updatedCertificado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCertificado(@PathVariable Long id) {
        certificadoService.deleteCertificado(id);
        return ResponseEntity.noContent().build();
    }
}