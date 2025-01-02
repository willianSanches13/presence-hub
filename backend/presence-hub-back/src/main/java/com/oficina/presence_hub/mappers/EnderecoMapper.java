package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.EnderecoDTO;
import com.oficina.presence_hub.entities.Endereco;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {

    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);

    EnderecoDTO toEnderecoDTO(Endereco endereco);

    Endereco toEndereco(EnderecoDTO enderecoDTO);

    List<EnderecoDTO> toEnderecoDTO(List<Endereco> enderecos);

    List<Endereco> toEndereco(List<EnderecoDTO> enderecoDTOs);

    @Mapping(target= "id", ignore = true)
    void updateEnderecoFromDTO(EnderecoDTO enderecoDTO, @MappingTarget Endereco endereco);
}
