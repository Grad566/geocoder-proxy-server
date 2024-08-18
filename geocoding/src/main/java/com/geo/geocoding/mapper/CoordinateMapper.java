package com.geo.geocoding.mapper;

import com.geo.geocoding.dto.CoordinateDTO;
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
public abstract class CoordinateMapper {
    public abstract CoordinateDTO map(Coordinate model);

    public List<String> toAddress(List<Address> addresses) {
        return addresses.stream().map(Address::getAddress).toList();
    }
}
