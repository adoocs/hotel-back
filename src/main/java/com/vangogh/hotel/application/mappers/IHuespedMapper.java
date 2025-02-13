package com.vangogh.hotel.application.mappers;

import com.vangogh.hotel.domain.dto.HuespedDto;
import com.vangogh.hotel.domain.models.entities.Huesped;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IHuespedMapper {

    HuespedDto toDto(Huesped huesped);

    Huesped toEntity(HuespedDto huespedDto);

    List<HuespedDto> toListDto(List<Huesped> huespedes);
}
