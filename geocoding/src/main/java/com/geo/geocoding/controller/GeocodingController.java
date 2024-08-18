package com.geo.geocoding.controller;

import com.geo.geocoding.dto.AddressDTO;
import com.geo.geocoding.dto.CoordinateDTO;
import com.geo.geocoding.service.AddressService;
import com.geo.geocoding.service.CoordinateService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/geocoding")
@RestController
@AllArgsConstructor
public class GeocodingController {
    private final CoordinateService coordinateService;
    private final AddressService addressService;

    @GetMapping(path = "/get_address")
    public CoordinateDTO getAddress(@RequestParam String coordinates) {
        return coordinateService.show(coordinates);
    }

    @GetMapping(path = "/get_coordinates")
    public AddressDTO getCoordinates(@RequestParam String address) {
        return addressService.show(address);
    }
}
