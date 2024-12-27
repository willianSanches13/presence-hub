package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.AlunoDTO.AlunoDTOBuilder;
import com.oficina.presence_hub.dtos.EnderecoDTO;
import com.oficina.presence_hub.dtos.EnderecoDTO.EnderecoDTOBuilder;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO.ParticipacaoDTOBuilder;
import com.oficina.presence_hub.dtos.ProfessorDTO;
import com.oficina.presence_hub.dtos.ProfessorDTO.ProfessorDTOBuilder;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO.WorkshopDTOBuilder;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Aluno.AlunoBuilder;
import com.oficina.presence_hub.entities.Endereco;
import com.oficina.presence_hub.entities.Participacao;
import com.oficina.presence_hub.entities.Professor;
import com.oficina.presence_hub.entities.Workshop;
import com.oficina.presence_hub.enums.UfEnum;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-27T13:37:25-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class ParticipacaoMapperImpl implements ParticipacaoMapper {

    @Override
    public ParticipacaoDTO toParticipacaoDTO(Participacao participacao) {
        if ( participacao == null ) {
            return null;
        }

        ParticipacaoDTOBuilder participacaoDTO = ParticipacaoDTO.builder();

        participacaoDTO.id( participacao.getId() );
        participacaoDTO.aluno( alunoToAlunoDTO( participacao.getAluno() ) );
        participacaoDTO.workshop( workshopToWorkshopDTO( participacao.getWorkshop() ) );
        participacaoDTO.presente( participacao.isPresente() );

        return participacaoDTO.build();
    }

    @Override
    public Participacao toParticipacao(ParticipacaoDTO participacaoDTO) {
        if ( participacaoDTO == null ) {
            return null;
        }

        Participacao participacao = new Participacao();

        participacao.setId( participacaoDTO.id() );
        participacao.setAluno( alunoDTOToAluno( participacaoDTO.aluno() ) );
        participacao.setWorkshop( workshopDTOToWorkshop( participacaoDTO.workshop() ) );
        participacao.setPresente( participacaoDTO.presente() );

        return participacao;
    }

    @Override
    public List<ParticipacaoDTO> toParticipacaoDTO(List<Participacao> participacoes) {
        if ( participacoes == null ) {
            return null;
        }

        List<ParticipacaoDTO> list = new ArrayList<ParticipacaoDTO>( participacoes.size() );
        for ( Participacao participacao : participacoes ) {
            list.add( toParticipacaoDTO( participacao ) );
        }

        return list;
    }

    @Override
    public List<Participacao> toParticipacao(List<ParticipacaoDTO> participacaoDTOs) {
        if ( participacaoDTOs == null ) {
            return null;
        }

        List<Participacao> list = new ArrayList<Participacao>( participacaoDTOs.size() );
        for ( ParticipacaoDTO participacaoDTO : participacaoDTOs ) {
            list.add( toParticipacao( participacaoDTO ) );
        }

        return list;
    }

    protected EnderecoDTO enderecoToEnderecoDTO(Endereco endereco) {
        if ( endereco == null ) {
            return null;
        }

        EnderecoDTOBuilder enderecoDTO = EnderecoDTO.builder();

        enderecoDTO.id( endereco.getId() );
        enderecoDTO.logradouro( endereco.getLogradouro() );
        enderecoDTO.bairro( endereco.getBairro() );
        enderecoDTO.cidade( endereco.getCidade() );
        if ( endereco.getUf() != null ) {
            enderecoDTO.uf( endereco.getUf().name() );
        }
        enderecoDTO.cep( endereco.getCep() );
        enderecoDTO.numero( endereco.getNumero() );
        enderecoDTO.complemento( endereco.getComplemento() );

        return enderecoDTO.build();
    }

    protected AlunoDTO alunoToAlunoDTO(Aluno aluno) {
        if ( aluno == null ) {
            return null;
        }

        AlunoDTOBuilder alunoDTO = AlunoDTO.builder();

        alunoDTO.id( aluno.getId() );
        alunoDTO.nome( aluno.getNome() );
        alunoDTO.email( aluno.getEmail() );
        alunoDTO.dataNascimento( aluno.getDataNascimento() );
        alunoDTO.serie( aluno.getSerie() );
        alunoDTO.endereco( enderecoToEnderecoDTO( aluno.getEndereco() ) );
        alunoDTO.instituicaoDeEnsino( aluno.getInstituicaoDeEnsino() );
        alunoDTO.telefoneContato( aluno.getTelefoneContato() );
        alunoDTO.nomeResponsavel( aluno.getNomeResponsavel() );
        alunoDTO.telefoneResponsavel( aluno.getTelefoneResponsavel() );
        alunoDTO.matriculaProjeto( aluno.getMatriculaProjeto() );
        alunoDTO.dataInscricao( aluno.getDataInscricao() );
        alunoDTO.observacoes( aluno.getObservacoes() );

        return alunoDTO.build();
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

    protected WorkshopDTO workshopToWorkshopDTO(Workshop workshop) {
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

    protected Endereco enderecoDTOToEndereco(EnderecoDTO enderecoDTO) {
        if ( enderecoDTO == null ) {
            return null;
        }

        Endereco endereco = new Endereco();

        endereco.setId( enderecoDTO.id() );
        endereco.setLogradouro( enderecoDTO.logradouro() );
        endereco.setBairro( enderecoDTO.bairro() );
        endereco.setCidade( enderecoDTO.cidade() );
        if ( enderecoDTO.uf() != null ) {
            endereco.setUf( Enum.valueOf( UfEnum.class, enderecoDTO.uf() ) );
        }
        endereco.setCep( enderecoDTO.cep() );
        endereco.setNumero( enderecoDTO.numero() );
        endereco.setComplemento( enderecoDTO.complemento() );

        return endereco;
    }

    protected Aluno alunoDTOToAluno(AlunoDTO alunoDTO) {
        if ( alunoDTO == null ) {
            return null;
        }

        AlunoBuilder aluno = Aluno.builder();

        aluno.id( alunoDTO.id() );
        aluno.nome( alunoDTO.nome() );
        aluno.email( alunoDTO.email() );
        aluno.dataNascimento( alunoDTO.dataNascimento() );
        aluno.serie( alunoDTO.serie() );
        aluno.endereco( enderecoDTOToEndereco( alunoDTO.endereco() ) );
        aluno.instituicaoDeEnsino( alunoDTO.instituicaoDeEnsino() );
        aluno.telefoneContato( alunoDTO.telefoneContato() );
        aluno.nomeResponsavel( alunoDTO.nomeResponsavel() );
        aluno.telefoneResponsavel( alunoDTO.telefoneResponsavel() );
        aluno.matriculaProjeto( alunoDTO.matriculaProjeto() );
        aluno.dataInscricao( alunoDTO.dataInscricao() );
        aluno.observacoes( alunoDTO.observacoes() );

        return aluno.build();
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

        return workshop;
    }
}
