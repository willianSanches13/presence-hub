package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.mappers.AlunoMapper;
import com.oficina.presence_hub.mappers.CertificadoMapper;
import com.oficina.presence_hub.mappers.ParticipacaoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AlunoService {

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AlunoMapper alunoMapper;

    @Autowired
    ParticipacaoMapper participacaoMapper;

    @Autowired
    CertificadoMapper certificadoMapper;

    public AlunoDTO createAluno(AlunoDTO alunoDto) {
        logger.info("Creating Aluno: {}", alunoDto);
        try {
            Aluno aluno = alunoRepository.save(alunoMapper.toAluno(alunoDto));
            logger.info("Aluno created successfully: {}", aluno);
            return alunoMapper.toAlunoDTO(aluno);
        } catch (Exception e) {
            logger.error("Error creating Aluno", e);
            throw new RuntimeException("Error creating Aluno", e);
        }
    }

    public List<AlunoDTO> getAllAlunos() {
        logger.info("Fetching all Alunos");
        List<Aluno> all = alunoRepository.findAll();
        logger.info("Fetched {} Alunos", all.size());
        return alunoMapper.toAlunoDTO(all);
    }

    public AlunoDTO getAlunoById(Long id) {
        logger.info("Fetching Aluno by id: {}", id);
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Aluno not found"));
        AlunoDTO alunoDTO = alunoMapper.toAlunoDTO(aluno);
        List<ParticipacaoDTO> participacoesDto = aluno.getParticipacoes()
                .stream().map(participacaoMapper::toParticipacaoDTO).toList();
        List<CertificadoDTO> certificadosDto = aluno.getCertificados()
                .stream().map(certificadoMapper::toCertificadoDTO).toList();
        alunoDTO.participacoes().addAll(participacoesDto);
        alunoDTO.certificados().addAll(certificadosDto);
        logger.info("Fetched Aluno: {}", alunoDTO);
        return alunoDTO;
    }

    public AlunoDTO updateAluno(Long id, AlunoDTO alunoDTO) {
        logger.info("Updating Aluno with id: {}", id);
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoMapper.updateAlunoFromDTO(alunoDTO, aluno);
        Aluno updatedAluno = alunoRepository.save(aluno);
        logger.info("Aluno updated successfully: {}", updatedAluno);
        return alunoMapper.toAlunoDTO(updatedAluno);
    }

    public void deleteAluno(Long id) {
        logger.info("Deleting Aluno with id: {}", id);
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoRepository.delete(aluno);
        logger.info("Aluno deleted successfully");
    }
}
