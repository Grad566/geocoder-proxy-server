package com.geo.geocoding.service;

import kong.unirest.core.Unirest;

import com.geo.geocoding.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YandexGeocodingService {
    @Value("${yandex.geocode.api.url}")
    private String yandexGeocoderUrl;

    @Value("${yandex.geocode.api.key}")
    private String apiKey;

    public String getAddress(String coordinates) throws BadRequestException {
        var response = Unirest.get(yandexGeocoderUrl)
                .queryString("apikey", apiKey)
                .queryString("geocode", coordinates)
                .queryString("format", "json")
                .asJson();

        if (response.getStatus() == 200) {
            return response.getBody().getObject()
                    .getJSONObject("response")
                    .getJSONObject("GeoObjectCollection")
                    .getJSONArray("featureMember")
                    .getJSONObject(0)
                    .getJSONObject("GeoObject")
                    .getJSONObject("metaDataProperty")
                    .getJSONObject("GeocoderMetaData")
                    .getString("text");
        } else {
            throw new BadRequestException("Ops yandex geocoder error " + response.getStatus() + " "
                    + response.getStatusText());
        }
    }

    public String getCoordinates(String address) throws BadRequestException {
        var response = Unirest.get(yandexGeocoderUrl)
                .queryString("apikey", apiKey)
                .queryString("geocode", address)
                .queryString("format", "json")
                .asJson();

        if (response.getStatus() == 200) {
            return response.getBody().getObject()
                    .getJSONObject("response")
                    .getJSONObject("GeoObjectCollection")
                    .getJSONArray("featureMember")
                    .getJSONObject(0)
                    .getJSONObject("GeoObject")
                    .getJSONObject("Point")
                    .getString("pos");
        } else {
            throw new BadRequestException("Ops yandex geocoder error " + response.getStatus() + " "
                    + response.getStatusText());
        }
    }
}
