package com.oficina.presence_hub.controllers;

import com.oficina.presence_hub.services.CertificadoService;
import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/certificados")
public class CertificadoController {

    @Autowired
    private CertificadoService certificadoService;

    @RequestMapping("/upload")
    public String showUploadPage() {
        return "upload-pdf";
    }

    @PostMapping("/validate")
    public String validatePdf(@RequestParam("file") MultipartFile file, Model model) {
        try {
            byte[] fileBytes = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(fileBytes);
            boolean isValid = certificadoService.validarAssinatura(base64);
            model.addAttribute("isValid", isValid);
        } catch (IOException e) {
            model.addAttribute("error", "Failed to read the file");
        }
        return "upload-pdf";
    }
}