package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.ProfessorDTO;
import com.oficina.presence_hub.dtos.ProfessorDTO.ProfessorDTOBuilder;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO.WorkshopDTOBuilder;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Aluno.AlunoBuilder;
import com.oficina.presence_hub.entities.Certificado;
import com.oficina.presence_hub.entities.Participacao;
import com.oficina.presence_hub.entities.Professor;
import com.oficina.presence_hub.entities.Workshop;
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
public class WorkshopMapperImpl implements WorkshopMapper {

    @Override
    public WorkshopDTO toWorkshopDTO(Workshop workshop) {
        if ( workshop == null ) {
            return null;
        }

        WorkshopDTOBuilder workshopDTO = WorkshopDTO.builder();

        workshopDTO.id( workshop.getId() );
        workshopDTO.titulo( workshop.getTitulo() );
        workshopDTO.descricao( workshop.getDescricao() );
        workshopDTO.data( workshop.getData() );
        workshopDTO.professor( professorToProfessorDTO( workshop.getProfessor() ) );

        return workshopDTO.build();
    }

    @Override
    public Workshop toWorkshop(WorkshopDTO workshopDTO) {
        if ( workshopDTO == null ) {
            return null;
        }

        Workshop workshop = new Workshop();

        workshop.setId( workshopDTO.id() );
        workshop.setTitulo( workshopDTO.titulo() );
        workshop.setDescricao( workshopDTO.descricao() );
        workshop.setData( workshopDTO.data() );
        workshop.setProfessor( professorDTOToProfessor( workshopDTO.professor() ) );

        return workshop;
    }

    @Override
    public List<WorkshopDTO> toWorkshopDTO(List<Workshop> workshops) {
        if ( workshops == null ) {
            return null;
        }

        List<WorkshopDTO> list = new ArrayList<WorkshopDTO>( workshops.size() );
        for ( Workshop workshop : workshops ) {
            list.add( toWorkshopDTO( workshop ) );
        }

        return list;
    }

    @Override
    public List<Workshop> toWorkshop(List<WorkshopDTO> workshopDTOs) {
        if ( workshopDTOs == null ) {
            return null;
        }

        List<Workshop> list = new ArrayList<Workshop>( workshopDTOs.size() );
        for ( WorkshopDTO workshopDTO : workshopDTOs ) {
            list.add( toWorkshop( workshopDTO ) );
        }

        return list;
    }

    @Override
    public void updateWorkshopFromDTO(WorkshopDTO workshopDTO, Workshop workshop) {
        if ( workshopDTO == null ) {
            return;
        }

        workshop.setTitulo( workshopDTO.titulo() );
        workshop.setDescricao( workshopDTO.descricao() );
        workshop.setData( workshopDTO.data() );
        if ( workshop.getParticipacoes() != null ) {
            List<Participacao> list = participacaoDTOListToParticipacaoList( workshopDTO.participacoes() );
            if ( list != null ) {
                workshop.getParticipacoes().clear();
                workshop.getParticipacoes().addAll( list );
            }
            else {
                workshop.setParticipacoes( null );
            }
        }
        else {
            List<Participacao> list = participacaoDTOListToParticipacaoList( workshopDTO.participacoes() );
            if ( list != null ) {
                workshop.setParticipacoes( list );
            }
        }
    }

    protected ProfessorDTO professorToProfessorDTO(Professor professor) {
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

    protected Professor professorDTOToProfessor(ProfessorDTO professorDTO) {
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

    protected List<Participacao> participacaoDTOListToParticipacaoList(List<ParticipacaoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Participacao> list1 = new ArrayList<Participacao>( list.size() );
        for ( ParticipacaoDTO participacaoDTO : list ) {
            list1.add( participacaoDTOToParticipacao( participacaoDTO ) );
        }

        return list1;
    }

    protected Certificado certificadoDTOToCertificado(CertificadoDTO certificadoDTO) {
        if ( certificadoDTO == null ) {
            return null;
        }

        Certificado certificado = new Certificado();

        certificado.setId( certificadoDTO.id() );
        certificado.setAluno( alunoDTOToAluno( certificadoDTO.aluno() ) );
        certificado.setWorkshop( toWorkshop( certificadoDTO.workshop() ) );
        certificado.setAssinaturaDigital( certificadoDTO.assinaturaDigital() );
        certificado.setDataEmissao( certificadoDTO.dataEmissao() );

        return certificado;
    }

    protected List<Certificado> certificadoDTOListToCertificadoList(List<CertificadoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Certificado> list1 = new ArrayList<Certificado>( list.size() );
        for ( CertificadoDTO certificadoDTO : list ) {
            list1.add( certificadoDTOToCertificado( certificadoDTO ) );
        }

        return list1;
    }

    protected Aluno alunoDTOToAluno(AlunoDTO alunoDTO) {
        if ( alunoDTO == null ) {
            return null;
        }

        AlunoBuilder aluno = Aluno.builder();

        aluno.id( alunoDTO.id() );
        aluno.nome( alunoDTO.nome() );
        aluno.email( alunoDTO.email() );
        aluno.idade( alunoDTO.idade() );
        aluno.serie( alunoDTO.serie() );
        aluno.instituicaoDeEnsino( alunoDTO.instituicaoDeEnsino() );
        aluno.telefoneContato( alunoDTO.telefoneContato() );
        aluno.cidade( alunoDTO.cidade() );
        aluno.estado( alunoDTO.estado() );
        aluno.nomeResponsavel( alunoDTO.nomeResponsavel() );
        aluno.telefoneResponsavel( alunoDTO.telefoneResponsavel() );
        aluno.matriculaProjeto( alunoDTO.matriculaProjeto() );
        aluno.statusParticipacao( alunoDTO.statusParticipacao() );
        aluno.dataInscricao( alunoDTO.dataInscricao() );
        aluno.observacoes( alunoDTO.observacoes() );
        aluno.participacoes( participacaoDTOListToParticipacaoList( alunoDTO.participacoes() ) );
        aluno.certificados( certificadoDTOListToCertificadoList( alunoDTO.certificados() ) );

        return aluno.build();
    }

    protected Participacao participacaoDTOToParticipacao(ParticipacaoDTO participacaoDTO) {
        if ( participacaoDTO == null ) {
            return null;
        }

        Participacao participacao = new Participacao();

        participacao.setId( participacaoDTO.id() );
        participacao.setAluno( alunoDTOToAluno( participacaoDTO.aluno() ) );
        participacao.setWorkshop( toWorkshop( participacaoDTO.workshop() ) );
        participacao.setPresente( participacaoDTO.presente() );

        return participacao;
    }
}
