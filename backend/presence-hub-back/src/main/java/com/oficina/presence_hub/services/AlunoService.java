package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.mappers.AlunoMapper;
import com.oficina.presence_hub.mappers.CertificadoMapper;
import com.oficina.presence_hub.mappers.ParticipacaoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
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
        Aluno aluno = alunoRepository.save(alunoMapper.toAluno(alunoDto));
        return alunoMapper.toAlunoDTO(aluno);
    }

    public List<AlunoDTO> getAllAlunos() {
        List<Aluno> all = alunoRepository.findAll();
        return alunoMapper.toAlunoDTO(all);
    }

    public AlunoDTO getAlunoById(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Aluno not found"));
        AlunoDTO alunoDTO = alunoMapper.toAlunoDTO(aluno);
        List<ParticipacaoDTO> participacoesDto = aluno.getParticipacoes()
                .stream().map(participacaoMapper::toParticipacaoDTO).toList();
        List<CertificadoDTO> certificadosDto = aluno.getCertificados()
                .stream().map(certificadoMapper::toCertificadoDTO).toList();
        alunoDTO.participacoes().addAll(participacoesDto);
        alunoDTO.certificados().addAll(certificadosDto);
        return alunoDTO;
    }

    public Aluno updateAluno(Long id, AlunoDTO alunoDTO) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoMapper.updateAlunoFromDTO(alunoDTO, aluno);
        return alunoRepository.save(aluno);
    }

    public void deleteAluno(Long id) {
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new RuntimeException("Aluno not found"));
        alunoRepository.delete(aluno);
    }
}