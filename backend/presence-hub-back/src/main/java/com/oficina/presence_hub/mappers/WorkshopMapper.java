package com.oficina.presence_hub.mappers;

import com.oficina.presence_hub.dtos.WorkshopDTO;
import com.oficina.presence_hub.entities.Workshop;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WorkshopMapper {

    WorkshopMapper INSTANCE = Mappers.getMapper(WorkshopMapper.class);

    WorkshopDTO toWorkshopDTO(Workshop workshop);

    Workshop toWorkshop(WorkshopDTO workshopDTO);

    List<WorkshopDTO> toWorkshopDTO(List<Workshop> workshops);

    List<Workshop> toWorkshop(List<WorkshopDTO> workshopDTOs);

    void updateWorkshopFromDTO(WorkshopDTO workshopDTO, @MappingTarget Workshop workshop);
}