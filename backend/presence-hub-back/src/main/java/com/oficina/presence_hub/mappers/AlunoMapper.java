package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.entities.Aluno;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    @Mapping(target = "certificados", ignore = true)
    @Mapping(target = "participacoes", ignore = true)
    AlunoDTO toAlunoDTO(Aluno aluno);

    @Mapping(target = "certificados", ignore = true)
    @Mapping(target = "participacoes", ignore = true)
    Aluno toAluno(AlunoDTO alunoDTO);

    List<AlunoDTO> toAlunoDTO(List<Aluno> alunos);

    List<Aluno> toAluno(List<AlunoDTO> alunoDTOs);

    @Mapping(target= "id", ignore = true)
    @Mapping(target= "endereco.id", ignore = true)
    void updateAlunoFromDTO(AlunoDTO alunoDTO, @MappingTarget Aluno aluno);
}