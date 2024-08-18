package com.geo.geocoding.mapper;

import com.geo.geocoding.dto.AddressDTO;
import com.geo.geocoding.model.Address;
import com.geo.geocoding.model.Coordinate;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        uses = {JsonNullableMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class AddressMapper {

    public abstract AddressDTO map(Address model);

    public List<String> toCoordinates(List<Coordinate> coordinates) {
        return coordinates.stream().map(Coordinate::getCoordinate).toList();
    }
}
