package com.geo.geocoding.repository;

import com.geo.geocoding.model.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoordinateRepository extends JpaRepository<Coordinate, Long> {
    Optional<Coordinate> findByCoordinate(String coordinate);
}
