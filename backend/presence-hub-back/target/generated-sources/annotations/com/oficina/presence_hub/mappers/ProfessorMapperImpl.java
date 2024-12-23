package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.ProfessorDTO;
import com.oficina.presence_hub.dtos.ProfessorDTO.ProfessorDTOBuilder;
import com.oficina.presence_hub.entities.Professor;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-23T13:45:33-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ProfessorMapperImpl implements ProfessorMapper {

    @Override
    public ProfessorDTO toProfessorDTO(Professor professor) {
        if ( professor == null ) {
            return null;
        }

        ProfessorDTOBuilder professorDTO = ProfessorDTO.builder();

        professorDTO.nome( professor.getNome() );
        professorDTO.email( professor.getEmail() );
        professorDTO.telefoneContato( professor.getTelefoneContato() );
        professorDTO.especializacao( professor.getEspecializacao() );
        professorDTO.instituicaoVinculo( professor.getInstituicaoVinculo() );

        return professorDTO.build();
    }

    @Override
    public Professor toProfessor(ProfessorDTO professorDTO) {
        if ( professorDTO == null ) {
            return null;
        }

        Professor professor = new Professor();

        professor.setNome( professorDTO.nome() );
        professor.setEmail( professorDTO.email() );
        professor.setTelefoneContato( professorDTO.telefoneContato() );
        professor.setEspecializacao( professorDTO.especializacao() );
        professor.setInstituicaoVinculo( professorDTO.instituicaoVinculo() );

        return professor;
    }

    @Override
    public List<ProfessorDTO> toProfessorDTO(List<Professor> professors) {
        if ( professors == null ) {
            return null;
        }

        List<ProfessorDTO> list = new ArrayList<ProfessorDTO>( professors.size() );
        for ( Professor professor : professors ) {
            list.add( toProfessorDTO( professor ) );
        }

        return list;
    }

    @Override
    public List<Professor> toProfessor(List<ProfessorDTO> professorDTOs) {
        if ( professorDTOs == null ) {
            return null;
        }

        List<Professor> list = new ArrayList<Professor>( professorDTOs.size() );
        for ( ProfessorDTO professorDTO : professorDTOs ) {
            list.add( toProfessor( professorDTO ) );
        }

        return list;
    }

    @Override
    public void updateProfessorFromDTO(ProfessorDTO professorDTO, Professor professor) {
        if ( professorDTO == null ) {
            return;
        }

        professor.setNome( professorDTO.nome() );
        professor.setEmail( professorDTO.email() );
        professor.setTelefoneContato( professorDTO.telefoneContato() );
        professor.setEspecializacao( professorDTO.especializacao() );
        professor.setInstituicaoVinculo( professorDTO.instituicaoVinculo() );
    }
}
