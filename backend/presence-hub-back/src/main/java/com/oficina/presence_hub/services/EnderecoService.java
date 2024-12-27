package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.EnderecoDTO;
import com.oficina.presence_hub.entities.Endereco;
import com.oficina.presence_hub.mappers.EnderecoMapper;
import com.oficina.presence_hub.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public Endereco createEndereco(EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoMapper.toEndereco(enderecoDTO);
        return enderecoRepository.save(endereco);
    }

    public List<EnderecoDTO> getAllEnderecos() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        return enderecoMapper.toEnderecoDTO(enderecos);
    }

    public Endereco getEnderecoById(Long id) {
        return enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereco not found"));
    }

    public Endereco updateEndereco(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = getEnderecoById(id);
        enderecoMapper.updateEnderecoFromDTO(enderecoDTO, endereco);
        return enderecoRepository.save(endereco);
    }

    public void deleteEndereco(Long id) {
        Endereco endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereco not found"));
        enderecoRepository.delete(endereco);
    }
}