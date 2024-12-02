package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.entities.Participacao;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipacaoMapper {

    ParticipacaoMapper INSTANCE = Mappers.getMapper(ParticipacaoMapper.class);

    @Mapping(target = "aluno.participacoes", ignore = true)
    @Mapping(target = "aluno.certificados", ignore = true)
    @Mapping(target = "workshop.participacoes", ignore = true)
    ParticipacaoDTO toParticipacaoDTO(Participacao participacao);

    @Mapping(target = "aluno.participacoes", ignore = true)
    @Mapping(target = "aluno.certificados", ignore = true)
    @Mapping(target = "workshop.participacoes", ignore = true)
    Participacao toParticipacao(ParticipacaoDTO participacaoDTO);

    List<ParticipacaoDTO> toParticipacaoDTO(List<Participacao> participacoes);

    List<Participacao> toParticipacao(List<ParticipacaoDTO> participacaoDTOs);

    @Mapping(target= "id", ignore = true)
    void updateParticipacaoFromDTO(ParticipacaoDTO participacaoDTO, @MappingTarget Participacao participacao);
}