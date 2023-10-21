package com.meet.partner.repository;

import com.meet.partner.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Serializable> {

    public Country findByCountryCode(String countryCode);

    public Country findByCountryName(String countryName);

    public Country findByCountryNameContaining(String countryName);

    public List<Country> findAllByOrderByCountryNameAsc();

    public Page<Country> findByCountryCodeAndCountryName(String countryCode, String countryName, Pageable pageable);

    public Page<Country> findByCountryName(String countryName, Pageable pageable);

    public Page<Country> findByCountryCode(String countryCode, Pageable pageable);

    public Page<Country> findAll(Pageable pageable);


}
