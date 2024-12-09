package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.ProfessorDTO;
import com.oficina.presence_hub.entities.Professor;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfessorMapper {

    ProfessorMapper INSTANCE = Mappers.getMapper(ProfessorMapper.class);

    ProfessorDTO toProfessorDTO(Professor professor);

    Professor toProfessor(ProfessorDTO professorDTO);

    List<ProfessorDTO> toProfessorDTO(List<Professor> professors);

    List<Professor> toProfessor(List<ProfessorDTO> professorDTOs);

    @Mapping(target = "id", ignore = true)
    void updateProfessorFromDTO(ProfessorDTO professorDTO, @MappingTarget Professor professor);
}