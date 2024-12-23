package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.mappers.AlunoMapper;
import com.oficina.presence_hub.mappers.CertificadoMapper;
import com.oficina.presence_hub.mappers.ParticipacaoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    ParticipacaoMapper participacaoMapper;

    @Autowired
    CertificadoMapper certificadoMapper;

    public AlunoDTO createAluno(AlunoDTO alunoDto) {
        log.info("Creating Aluno: {}", alunoDto);
        try {
            Aluno aluno = alunoRepository.save(alunoMapper.toAluno(alunoDto));
            log.info("Aluno created successfully: {}", aluno);
            return alunoMapper.toAlunoDTO(aluno);
        } catch (Exception e) {
            log.error("Error creating Aluno", e);
            throw new RuntimeException("Error creating Aluno", e);
        }
    }

    public List<AlunoDTO> getAllAlunos() {
        log.info("Fetching all Alunos");
        List<Aluno> all = alunoRepository.findAll();
        log.info("Fetched {} Alunos", all.size());
        return alunoMapper.toAlunoDTO(all);
    }

    public AlunoDTO getAlunoById(Long id) {
        log.info("Fetching Aluno by id: {}", id);
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Aluno not found"));
        AlunoDTO alunoDTO = alunoMapper.toAlunoDTO(aluno);
        List<ParticipacaoDTO> participacoesDto = aluno.getParticipacoes()
                .stream().map(participacaoMapper::toParticipacaoDTO).toList();
        List<CertificadoDTO> certificadosDto = aluno.getCertificados()
                .stream().map(certificadoMapper::toCertificadoDTO).toList();
        alunoDTO.participacoes().addAll(participacoesDto);
        alunoDTO.certificados().addAll(certificadosDto);
        log.info("Fetched Aluno: {}", alunoDTO);
        return alunoDTO;
    }

    public AlunoDTO updateAluno(Long id, AlunoDTO alunoDTO) {
        log.info("Updating Aluno with id: {}", id);
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoMapper.updateAlunoFromDTO(alunoDTO, aluno);
        Aluno updatedAluno = alunoRepository.save(aluno);
        log.info("Aluno updated successfully: {}", updatedAluno);
        return alunoMapper.toAlunoDTO(updatedAluno);
    }

    public void deleteAluno(Long id) {
        log.info("Deleting Aluno with id: {}", id);
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoRepository.delete(aluno);
        log.info("Aluno deleted successfully");
    }
}
