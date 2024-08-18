package com.geo.geocoding.controller;


import com.geo.geocoding.repository.AddressRepository;
import com.geo.geocoding.repository.CoordinateRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GeocodingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CoordinateRepository coordinateRepository;

    @Test
    public void testGetAddress() throws Exception {
        var address = "Mohammed Bin Rashid Boulevard 1";
        mockMvc.perform(get("/api/geocoding/get_coordinates?address=" + address))
                .andExpect(status().isOk());

        assertThat(addressRepository.findByAddress(address)).isNotNull();
    }

    @Test
    public void testGetCoordinate() throws Exception {
        var coordinates = "25.197300,55.274243";
        mockMvc.perform(get("/api/geocoding/get_address?coordinates=" + coordinates))
                .andExpect(status().isOk());

        assertThat(coordinateRepository.findByCoordinate(coordinates)).isNotNull();
    }
}
