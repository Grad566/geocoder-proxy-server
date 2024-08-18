package com.geo.geocoding.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CoordinateDTO {
    private String coordinate;
    private List<String> addresses;
}
