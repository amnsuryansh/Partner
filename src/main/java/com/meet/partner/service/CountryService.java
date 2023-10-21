package com.meet.partner.service;

import com.meet.partner.model.Country;

import java.util.List;

public interface CountryService {
    public Country create(String countryCode, String countryName, String countrySynonyms);

    public Country update(String countryCode, String countryName, String countrySynonyms);

    public Country update(String countryCode);

    public Country getByCountryCode(String countryCode);

    public Country getByCountryName(String countryName);

    public Country getByCountryNameContaining(String countryName);

    public List<Country> getAllCountry();
}
