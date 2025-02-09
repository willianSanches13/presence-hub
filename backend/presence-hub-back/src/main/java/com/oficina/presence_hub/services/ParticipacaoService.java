package com.oficina.presence_hub.services;

import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Participacao;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.mappers.ParticipacaoMapper;
import com.oficina.presence_hub.repositories.AlunoRepository;
import com.oficina.presence_hub.repositories.ParticipacaoRepository;
import com.oficina.presence_hub.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ParticipacaoService {

    @Autowired
    private ParticipacaoRepository participacaoRepository;

    @Autowired
    private ParticipacaoMapper participacaoMapper;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    public void createParticipacao(Long alunoId, Long workshopId,  boolean presenca) {
        Participacao participacao = new Participacao();
        participacao.setPresente(presenca);
        Participacao participacaoOrm = participacaoRepository.save(participacao);
        participacaoOrm.setAluno(findAluno(alunoId));
        participacaoOrm.setWorkshop(findWorkshop(workshopId));
        participacaoRepository.save(participacaoOrm);
    }

    public List<ParticipacaoDTO> getAllParticipacoes() {
        return participacaoMapper.toParticipacaoDTO(participacaoRepository.findAll());
    }

    public ParticipacaoDTO getParticipacaoById(Long id) {
        Participacao participacao = participacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participacao not found"));
        return participacaoMapper.toParticipacaoDTO(participacao);
    }

    public void updateParticipacao(Long id, boolean presenca) {
        Participacao participacao = participacaoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Participacao not found"));
        participacao.setPresente(presenca);
        participacaoRepository.save(participacao);
    }

    private Aluno findAluno(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
    }

    private Workshop findWorkshop(Long id) {
        return workshopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workshop não encontrado"));
    }
}