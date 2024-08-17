package com.geo.geocoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GeocodingApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeocodingApplication.class, args);
	}

}
