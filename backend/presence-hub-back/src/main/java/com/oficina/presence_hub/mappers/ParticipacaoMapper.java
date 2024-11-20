package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.entities.Participacao;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipacaoMapper {

    ParticipacaoMapper INSTANCE = Mappers.getMapper(ParticipacaoMapper.class);

    ParticipacaoDTO toParticipacaoDTO(Participacao participacao);

    Participacao toParticipacao(ParticipacaoDTO participacaoDTO);

    List<ParticipacaoDTO> toParticipacaoDTO(List<Participacao> participacoes);

    List<Participacao> toParticipacao(List<ParticipacaoDTO> participacaoDTOs);

    void updateParticipacaoFromDTO(ParticipacaoDTO participacaoDTO, @MappingTarget Participacao participacao);
}