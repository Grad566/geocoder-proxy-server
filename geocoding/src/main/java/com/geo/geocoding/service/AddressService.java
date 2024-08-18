package com.geo.geocoding.service;

import com.geo.geocoding.dto.AddressDTO;
import com.geo.geocoding.mapper.AddressMapper;
import com.geo.geocoding.model.Address;
import com.geo.geocoding.model.Coordinate;
import com.geo.geocoding.repository.AddressRepository;
import com.geo.geocoding.repository.CoordinateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final CoordinateRepository coordinateRepository;
    private final AddressMapper addressMapper;
    private final YandexGeocodingService yandexGeocodingService;

    public List<AddressDTO> getAll() {
        return addressRepository.findAll().stream().map(addressMapper::map).toList();
    }

    public AddressDTO show(String address) {
        var mayAddress = addressRepository.findByAddress(address);
        if (mayAddress.isPresent()) {
            return addressMapper.map(mayAddress.get());
        } else {
            return save(address);
        }
    }

    @Transactional
    private AddressDTO save(String address) {
        var newAddress = new Address();
        Coordinate coordinate;

        var coordinateAsString = yandexGeocodingService.getCoordinates(address);
        var possibleCoordinate = coordinateRepository.findByCoordinate(coordinateAsString);
        if (possibleCoordinate.isPresent()) {
            coordinate = possibleCoordinate.get();
        } else {
            coordinate = new Coordinate();
            coordinate.setCoordinate(coordinateAsString);
            coordinateRepository.save(coordinate);
        }

        newAddress.setAddress(address);
        newAddress.setCoordinates(new ArrayList<>(List.of(coordinate)));

        addressRepository.save(newAddress);
        coordinateRepository.save(coordinate);

        return addressMapper.map(newAddress);
    }
}
