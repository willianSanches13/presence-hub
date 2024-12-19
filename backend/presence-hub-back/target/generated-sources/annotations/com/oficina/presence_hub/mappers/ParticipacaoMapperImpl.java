package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.AlunoDTO.AlunoDTOBuilder;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.ParticipacaoDTO.ParticipacaoDTOBuilder;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO.WorkshopDTOBuilder;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Aluno.AlunoBuilder;
import com.oficina.presence_hub.entities.Participacao;
import com.oficina.presence_hub.entities.Workshop;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-10T17:11:01-0300",
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

    protected AlunoDTO alunoToAlunoDTO(Aluno aluno) {
        if ( aluno == null ) {
            return null;
        }

        AlunoDTOBuilder alunoDTO = AlunoDTO.builder();

        alunoDTO.id( aluno.getId() );
        alunoDTO.nome( aluno.getNome() );
        alunoDTO.email( aluno.getEmail() );
        alunoDTO.senha( aluno.getSenha() );
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

    protected WorkshopDTO workshopToWorkshopDTO(Workshop workshop) {
        if ( workshop == null ) {
            return null;
        }

        WorkshopDTOBuilder workshopDTO = WorkshopDTO.builder();

        workshopDTO.id( workshop.getId() );
        workshopDTO.titulo( workshop.getTitulo() );
        workshopDTO.descricao( workshop.getDescricao() );
        workshopDTO.data( workshop.getData() );

        return workshopDTO.build();
    }

    protected Aluno alunoDTOToAluno(AlunoDTO alunoDTO) {
        if ( alunoDTO == null ) {
            return null;
        }

        AlunoBuilder aluno = Aluno.builder();

        aluno.id( alunoDTO.id() );
        aluno.nome( alunoDTO.nome() );
        aluno.email( alunoDTO.email() );
        aluno.senha( alunoDTO.senha() );
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

    protected Workshop workshopDTOToWorkshop(WorkshopDTO workshopDTO) {
        if ( workshopDTO == null ) {
            return null;
        }

        Workshop workshop = new Workshop();

        workshop.setId( workshopDTO.id() );
        workshop.setTitulo( workshopDTO.titulo() );
        workshop.setDescricao( workshopDTO.descricao() );
        workshop.setData( workshopDTO.data() );

        return workshop;
    }
}
