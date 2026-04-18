package com.obada.restaurant.mapper;

import com.obada.restaurant.domain.dto.PhotoDto;
import com.obada.restaurant.domain.entity.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {

    PhotoDto toDto(Photo photo);
}
