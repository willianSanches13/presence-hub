package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.CertificadoDTO;
import com.oficina.presence_hub.entities.Certificado;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CertificadoMapper {

    CertificadoMapper INSTANCE = Mappers.getMapper(CertificadoMapper.class);

    @Mapping(target = "aluno.certificados", ignore = true)
    @Mapping(target = "aluno.participacoes", ignore = true)
    @Mapping(target = "workshop.participacoes", ignore = true)
    CertificadoDTO toCertificadoDTO(Certificado certificado);

    @Mapping(target = "workshop.participacoes", ignore = true)
    @Mapping(target = "aluno.participacoes", ignore = true)
    @Mapping(target = "aluno.certificados", ignore = true)
    Certificado toCertificado(CertificadoDTO certificadoDTO);

    List<CertificadoDTO> toCertificadoDTO(List<Certificado> certificados);

    List<Certificado> toCertificado(List<CertificadoDTO> certificadoDTOs);

    @Mapping(target= "id", ignore = true)
    void updateCertificadoFromDTO(CertificadoDTO certificadoDTO, @MappingTarget Certificado certificado);
}