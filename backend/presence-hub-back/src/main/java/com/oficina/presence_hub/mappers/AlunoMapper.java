package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.entities.Aluno;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlunoMapper {

    AlunoMapper INSTANCE = Mappers.getMapper(AlunoMapper.class);

    AlunoDTO toAlunoDTO(Aluno aluno);

    Aluno toAluno(AlunoDTO alunoDTO);

    List<AlunoDTO> toAlunoDTO(List<Aluno> alunos);

    List<Aluno> toAluno(List<AlunoDTO> alunoDTOs);

    void updateAlunoFromDTO(AlunoDTO alunoDTO, @MappingTarget Aluno aluno);
}