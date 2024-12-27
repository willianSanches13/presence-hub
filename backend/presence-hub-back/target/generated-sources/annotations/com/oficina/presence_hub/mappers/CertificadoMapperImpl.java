package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.AlunoDTO;
import com.oficina.presence_hub.dtos.AlunoDTO.AlunoDTOBuilder;
import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.dtos.CertificadoDTO.CertificadoDTOBuilder;
import com.oficina.presence_hub.dtos.EnderecoDTO;
import com.oficina.presence_hub.dtos.EnderecoDTO.EnderecoDTOBuilder;
import com.oficina.presence_hub.dtos.ParticipacaoDTO;
import com.oficina.presence_hub.dtos.ProfessorDTO;
import com.oficina.presence_hub.dtos.ProfessorDTO.ProfessorDTOBuilder;
import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.dtos.WorkshopDTO.WorkshopDTOBuilder;
import com.oficina.presence_hub.entities.Aluno;
import com.oficina.presence_hub.entities.Aluno.AlunoBuilder;
import com.oficina.presence_hub.entities.Certificado;
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
public class CertificadoMapperImpl implements CertificadoMapper {

    @Override
    public CertificadoDTO toCertificadoDTO(Certificado certificado) {
        if ( certificado == null ) {
            return null;
        }

        CertificadoDTOBuilder certificadoDTO = CertificadoDTO.builder();

        certificadoDTO.id( certificado.getId() );
        certificadoDTO.aluno( alunoToAlunoDTO( certificado.getAluno() ) );
        certificadoDTO.workshop( workshopToWorkshopDTO( certificado.getWorkshop() ) );
        certificadoDTO.assinaturaDigital( certificado.getAssinaturaDigital() );
        certificadoDTO.dataEmissao( certificado.getDataEmissao() );

        return certificadoDTO.build();
    }

    @Override
    public Certificado toCertificado(CertificadoDTO certificadoDTO) {
        if ( certificadoDTO == null ) {
            return null;
        }

        Certificado certificado = new Certificado();

        certificado.setId( certificadoDTO.id() );
        certificado.setAluno( alunoDTOToAluno( certificadoDTO.aluno() ) );
        certificado.setWorkshop( workshopDTOToWorkshop( certificadoDTO.workshop() ) );
        certificado.setAssinaturaDigital( certificadoDTO.assinaturaDigital() );
        certificado.setDataEmissao( certificadoDTO.dataEmissao() );

        return certificado;
    }

    @Override
    public List<CertificadoDTO> toCertificadoDTO(List<Certificado> certificados) {
        if ( certificados == null ) {
            return null;
        }

        List<CertificadoDTO> list = new ArrayList<CertificadoDTO>( certificados.size() );
        for ( Certificado certificado : certificados ) {
            list.add( toCertificadoDTO( certificado ) );
        }

        return list;
    }

    @Override
    public List<Certificado> toCertificado(List<CertificadoDTO> certificadoDTOs) {
        if ( certificadoDTOs == null ) {
            return null;
        }

        List<Certificado> list = new ArrayList<Certificado>( certificadoDTOs.size() );
        for ( CertificadoDTO certificadoDTO : certificadoDTOs ) {
            list.add( toCertificado( certificadoDTO ) );
        }

        return list;
    }

    @Override
    public void updateCertificadoFromDTO(CertificadoDTO certificadoDTO, Certificado certificado) {
        if ( certificadoDTO == null ) {
            return;
        }

        if ( certificadoDTO.aluno() != null ) {
            if ( certificado.getAluno() == null ) {
                certificado.setAluno( new Aluno() );
            }
            alunoDTOToAluno1( certificadoDTO.aluno(), certificado.getAluno() );
        }
        else {
            certificado.setAluno( null );
        }
        if ( certificadoDTO.workshop() != null ) {
            if ( certificado.getWorkshop() == null ) {
                certificado.setWorkshop( new Workshop() );
            }
            workshopDTOToWorkshop2( certificadoDTO.workshop(), certificado.getWorkshop() );
        }
        else {
            certificado.setWorkshop( null );
        }
        certificado.setAssinaturaDigital( certificadoDTO.assinaturaDigital() );
        certificado.setDataEmissao( certificadoDTO.dataEmissao() );
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

    protected void enderecoDTOToEndereco1(EnderecoDTO enderecoDTO, Endereco mappingTarget) {
        if ( enderecoDTO == null ) {
            return;
        }

        mappingTarget.setId( enderecoDTO.id() );
        mappingTarget.setLogradouro( enderecoDTO.logradouro() );
        mappingTarget.setBairro( enderecoDTO.bairro() );
        mappingTarget.setCidade( enderecoDTO.cidade() );
        if ( enderecoDTO.uf() != null ) {
            mappingTarget.setUf( Enum.valueOf( UfEnum.class, enderecoDTO.uf() ) );
        }
        else {
            mappingTarget.setUf( null );
        }
        mappingTarget.setCep( enderecoDTO.cep() );
        mappingTarget.setNumero( enderecoDTO.numero() );
        mappingTarget.setComplemento( enderecoDTO.complemento() );
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

    protected Aluno alunoDTOToAluno1(AlunoDTO alunoDTO) {
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
        aluno.participacoes( participacaoDTOListToParticipacaoList( alunoDTO.participacoes() ) );
        aluno.certificados( toCertificado( alunoDTO.certificados() ) );

        return aluno.build();
    }

    protected Workshop workshopDTOToWorkshop1(WorkshopDTO workshopDTO) {
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
        participacao.setAluno( alunoDTOToAluno1( participacaoDTO.aluno() ) );
        participacao.setWorkshop( workshopDTOToWorkshop1( participacaoDTO.workshop() ) );
        participacao.setPresente( participacaoDTO.presente() );

        return participacao;
    }

    protected void alunoDTOToAluno1(AlunoDTO alunoDTO, Aluno mappingTarget) {
        if ( alunoDTO == null ) {
            return;
        }

        mappingTarget.setId( alunoDTO.id() );
        mappingTarget.setNome( alunoDTO.nome() );
        mappingTarget.setEmail( alunoDTO.email() );
        mappingTarget.setDataNascimento( alunoDTO.dataNascimento() );
        mappingTarget.setSerie( alunoDTO.serie() );
        if ( alunoDTO.endereco() != null ) {
            if ( mappingTarget.getEndereco() == null ) {
                mappingTarget.setEndereco( new Endereco() );
            }
            enderecoDTOToEndereco1( alunoDTO.endereco(), mappingTarget.getEndereco() );
        }
        else {
            mappingTarget.setEndereco( null );
        }
        mappingTarget.setInstituicaoDeEnsino( alunoDTO.instituicaoDeEnsino() );
        mappingTarget.setTelefoneContato( alunoDTO.telefoneContato() );
        mappingTarget.setNomeResponsavel( alunoDTO.nomeResponsavel() );
        mappingTarget.setTelefoneResponsavel( alunoDTO.telefoneResponsavel() );
        mappingTarget.setMatriculaProjeto( alunoDTO.matriculaProjeto() );
        mappingTarget.setDataInscricao( alunoDTO.dataInscricao() );
        mappingTarget.setObservacoes( alunoDTO.observacoes() );
        if ( mappingTarget.getParticipacoes() != null ) {
            List<Participacao> list = participacaoDTOListToParticipacaoList( alunoDTO.participacoes() );
            if ( list != null ) {
                mappingTarget.getParticipacoes().clear();
                mappingTarget.getParticipacoes().addAll( list );
            }
            else {
                mappingTarget.setParticipacoes( null );
            }
        }
        else {
            List<Participacao> list = participacaoDTOListToParticipacaoList( alunoDTO.participacoes() );
            if ( list != null ) {
                mappingTarget.setParticipacoes( list );
            }
        }
        if ( mappingTarget.getCertificados() != null ) {
            List<Certificado> list1 = toCertificado( alunoDTO.certificados() );
            if ( list1 != null ) {
                mappingTarget.getCertificados().clear();
                mappingTarget.getCertificados().addAll( list1 );
            }
            else {
                mappingTarget.setCertificados( null );
            }
        }
        else {
            List<Certificado> list1 = toCertificado( alunoDTO.certificados() );
            if ( list1 != null ) {
                mappingTarget.setCertificados( list1 );
            }
        }
    }

    protected void professorDTOToProfessor1(ProfessorDTO professorDTO, Professor mappingTarget) {
        if ( professorDTO == null ) {
            return;
        }

        mappingTarget.setNome( professorDTO.nome() );
        mappingTarget.setEmail( professorDTO.email() );
        mappingTarget.setTelefoneContato( professorDTO.telefoneContato() );
        mappingTarget.setEspecializacao( professorDTO.especializacao() );
        mappingTarget.setInstituicaoVinculo( professorDTO.instituicaoVinculo() );
    }

    protected void workshopDTOToWorkshop2(WorkshopDTO workshopDTO, Workshop mappingTarget) {
        if ( workshopDTO == null ) {
            return;
        }

        mappingTarget.setId( workshopDTO.id() );
        mappingTarget.setTitulo( workshopDTO.titulo() );
        mappingTarget.setDescricao( workshopDTO.descricao() );
        mappingTarget.setData( workshopDTO.data() );
        if ( workshopDTO.professor() != null ) {
            if ( mappingTarget.getProfessor() == null ) {
                mappingTarget.setProfessor( new Professor() );
            }
            professorDTOToProfessor1( workshopDTO.professor(), mappingTarget.getProfessor() );
        }
        else {
            mappingTarget.setProfessor( null );
        }
        if ( mappingTarget.getParticipacoes() != null ) {
            List<Participacao> list = participacaoDTOListToParticipacaoList( workshopDTO.participacoes() );
            if ( list != null ) {
                mappingTarget.getParticipacoes().clear();
                mappingTarget.getParticipacoes().addAll( list );
            }
            else {
                mappingTarget.setParticipacoes( null );
            }
        }
        else {
            List<Participacao> list = participacaoDTOListToParticipacaoList( workshopDTO.participacoes() );
            if ( list != null ) {
                mappingTarget.setParticipacoes( list );
            }
        }
    }
}
