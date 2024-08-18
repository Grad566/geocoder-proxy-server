package com.geo.geocoding.service;

import com.geo.geocoding.dto.CoordinateDTO;
import com.geo.geocoding.mapper.CoordinateMapper;
import com.geo.geocoding.model.Address;
import com.geo.geocoding.model.Coordinate;
import com.geo.geocoding.repository.AddressRepository;
import com.geo.geocoding.repository.CoordinateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CoordinateService {
    private final CoordinateRepository coordinateRepository;
    private final AddressRepository addressRepository;
    private final CoordinateMapper coordinateMapper;
    private final YandexGeocodingService yandexGeocodingService;

    public List<CoordinateDTO> getAll() {
        return coordinateRepository.findAll().stream().map(coordinateMapper::map).toList();
    }

    public CoordinateDTO show(String coordinate) {
        var possibleCoordinate = coordinateRepository.findByCoordinate(coordinate);
        if (possibleCoordinate.isPresent()) {
            return coordinateMapper.map(possibleCoordinate.get());
        } else {
            return save(coordinate);
        }
    }

    private CoordinateDTO save(String coordinate) {
        var newCoordinate = new Coordinate();
        Address address;

        var addressAsString = yandexGeocodingService.getAddress(coordinate);
        var possibleAddress = addressRepository.findByAddress(addressAsString);
        if (possibleAddress.isPresent()) {
            address = possibleAddress.get();
        } else {
            address = new Address();
            address.setAddress(addressAsString);
            addressRepository.save(address);
        }

        newCoordinate.setCoordinate(coordinate);
        newCoordinate.setAddresses(new ArrayList<>(List.of(address)));

        coordinateRepository.save(newCoordinate);
        addressRepository.save(address);

        return coordinateMapper.map(newCoordinate);
    }
}
