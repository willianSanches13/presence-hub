package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.AlunoDTO.AlunoDTOBuilder;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.ProfessorDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO;
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
public class AlunoMapperImpl implements AlunoMapper {

    @Override
    public AlunoDTO toAlunoDTO(Aluno aluno) {
        if ( aluno == null ) {
            return null;
        }

        AlunoDTOBuilder alunoDTO = AlunoDTO.builder();

        alunoDTO.id( aluno.getId() );
        alunoDTO.nome( aluno.getNome() );
        alunoDTO.email( aluno.getEmail() );
        alunoDTO.idade( aluno.getIdade() );
        alunoDTO.serie( aluno.getSerie() );
        alunoDTO.instituicaoDeEnsino( aluno.getInstituicaoDeEnsino() );
        alunoDTO.telefoneContato( aluno.getTelefoneContato() );
        alunoDTO.cidade( aluno.getCidade() );
        alunoDTO.estado( aluno.getEstado() );
        alunoDTO.nomeResponsavel( aluno.getNomeResponsavel() );
        alunoDTO.telefoneResponsavel( aluno.getTelefoneResponsavel() );
        alunoDTO.matriculaProjeto( aluno.getMatriculaProjeto() );
        alunoDTO.statusParticipacao( aluno.getStatusParticipacao() );
        alunoDTO.dataInscricao( aluno.getDataInscricao() );
        alunoDTO.observacoes( aluno.getObservacoes() );

        return alunoDTO.build();
    }

    @Override
    public Aluno toAluno(AlunoDTO alunoDTO) {
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

        return aluno.build();
    }

    @Override
    public List<AlunoDTO> toAlunoDTO(List<Aluno> alunos) {
        if ( alunos == null ) {
            return null;
        }

        List<AlunoDTO> list = new ArrayList<AlunoDTO>( alunos.size() );
        for ( Aluno aluno : alunos ) {
            list.add( toAlunoDTO( aluno ) );
        }

        return list;
    }

    @Override
    public List<Aluno> toAluno(List<AlunoDTO> alunoDTOs) {
        if ( alunoDTOs == null ) {
            return null;
        }

        List<Aluno> list = new ArrayList<Aluno>( alunoDTOs.size() );
        for ( AlunoDTO alunoDTO : alunoDTOs ) {
            list.add( toAluno( alunoDTO ) );
        }

        return list;
    }

    @Override
    public void updateAlunoFromDTO(AlunoDTO alunoDTO, Aluno aluno) {
        if ( alunoDTO == null ) {
            return;
        }

        aluno.setNome( alunoDTO.nome() );
        aluno.setEmail( alunoDTO.email() );
        aluno.setIdade( alunoDTO.idade() );
        aluno.setSerie( alunoDTO.serie() );
        aluno.setInstituicaoDeEnsino( alunoDTO.instituicaoDeEnsino() );
        aluno.setTelefoneContato( alunoDTO.telefoneContato() );
        aluno.setCidade( alunoDTO.cidade() );
        aluno.setEstado( alunoDTO.estado() );
        aluno.setNomeResponsavel( alunoDTO.nomeResponsavel() );
        aluno.setTelefoneResponsavel( alunoDTO.telefoneResponsavel() );
        aluno.setMatriculaProjeto( alunoDTO.matriculaProjeto() );
        aluno.setStatusParticipacao( alunoDTO.statusParticipacao() );
        aluno.setDataInscricao( alunoDTO.dataInscricao() );
        aluno.setObservacoes( alunoDTO.observacoes() );
        if ( aluno.getParticipacoes() != null ) {
            List<Participacao> list = participacaoDTOListToParticipacaoList( alunoDTO.participacoes() );
            if ( list != null ) {
                aluno.getParticipacoes().clear();
                aluno.getParticipacoes().addAll( list );
            }
            else {
                aluno.setParticipacoes( null );
            }
        }
        else {
            List<Participacao> list = participacaoDTOListToParticipacaoList( alunoDTO.participacoes() );
            if ( list != null ) {
                aluno.setParticipacoes( list );
            }
        }
        if ( aluno.getCertificados() != null ) {
            List<Certificado> list1 = certificadoDTOListToCertificadoList( alunoDTO.certificados() );
            if ( list1 != null ) {
                aluno.getCertificados().clear();
                aluno.getCertificados().addAll( list1 );
            }
            else {
                aluno.setCertificados( null );
            }
        }
        else {
            List<Certificado> list1 = certificadoDTOListToCertificadoList( alunoDTO.certificados() );
            if ( list1 != null ) {
                aluno.setCertificados( list1 );
            }
        }
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

    protected Workshop workshopDTOToWorkshop(WorkshopDTO workshopDTO) {
        if ( workshopDTO == null ) {
            return null;
        }

        Workshop workshop = new Workshop();

        workshop.setId( workshopDTO.id() );
        workshop.setTitulo( workshopDTO.titulo() );
        workshop.setDescricao( workshopDTO.descricao() );
        workshop.setData( workshopDTO.data() );
        workshop.setProfessor( professorDTOToProfessor( workshopDTO.professor() ) );
        workshop.setParticipacoes( participacaoDTOListToParticipacaoList( workshopDTO.participacoes() ) );

        return workshop;
    }

    protected Participacao participacaoDTOToParticipacao(ParticipacaoDTO participacaoDTO) {
        if ( participacaoDTO == null ) {
            return null;
        }

        Participacao participacao = new Participacao();

        participacao.setId( participacaoDTO.id() );
        participacao.setAluno( toAluno( participacaoDTO.aluno() ) );
        participacao.setWorkshop( workshopDTOToWorkshop( participacaoDTO.workshop() ) );
        participacao.setPresente( participacaoDTO.presente() );

        return participacao;
    }

    protected Certificado certificadoDTOToCertificado(CertificadoDTO certificadoDTO) {
        if ( certificadoDTO == null ) {
            return null;
        }

        Certificado certificado = new Certificado();

        certificado.setId( certificadoDTO.id() );
        certificado.setAluno( toAluno( certificadoDTO.aluno() ) );
        certificado.setWorkshop( workshopDTOToWorkshop( certificadoDTO.workshop() ) );
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
}
