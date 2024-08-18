package com.geo.geocoding.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressDTO {
    private String address;
    private List<String> coordinates;
}
