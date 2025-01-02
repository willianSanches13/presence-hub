package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.EnderecoDTO;
import com.oficina.presence_hub.dtos.EnderecoDTO.EnderecoDTOBuilder;
import com.oficina.presence_hub.entities.Endereco;
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
public class EnderecoMapperImpl implements EnderecoMapper {

    @Override
    public EnderecoDTO toEnderecoDTO(Endereco endereco) {
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

    @Override
    public Endereco toEndereco(EnderecoDTO enderecoDTO) {
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

    @Override
    public List<EnderecoDTO> toEnderecoDTO(List<Endereco> enderecos) {
        if ( enderecos == null ) {
            return null;
        }

        List<EnderecoDTO> list = new ArrayList<EnderecoDTO>( enderecos.size() );
        for ( Endereco endereco : enderecos ) {
            list.add( toEnderecoDTO( endereco ) );
        }

        return list;
    }

    @Override
    public List<Endereco> toEndereco(List<EnderecoDTO> enderecoDTOs) {
        if ( enderecoDTOs == null ) {
            return null;
        }

        List<Endereco> list = new ArrayList<Endereco>( enderecoDTOs.size() );
        for ( EnderecoDTO enderecoDTO : enderecoDTOs ) {
            list.add( toEndereco( enderecoDTO ) );
        }

        return list;
    }

    @Override
    public void updateEnderecoFromDTO(EnderecoDTO enderecoDTO, Endereco endereco) {
        if ( enderecoDTO == null ) {
            return;
        }

        endereco.setLogradouro( enderecoDTO.logradouro() );
        endereco.setBairro( enderecoDTO.bairro() );
        endereco.setCidade( enderecoDTO.cidade() );
        if ( enderecoDTO.uf() != null ) {
            endereco.setUf( Enum.valueOf( UfEnum.class, enderecoDTO.uf() ) );
        }
        else {
            endereco.setUf( null );
        }
        endereco.setCep( enderecoDTO.cep() );
        endereco.setNumero( enderecoDTO.numero() );
        endereco.setComplemento( enderecoDTO.complemento() );
    }
}
