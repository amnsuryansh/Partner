package com.meet.partner.service;

import com.meet.partner.codetype.InfoType;
import com.meet.partner.exception.service.PartnerExceptionComponent;
import com.meet.partner.model.Country;
import com.meet.partner.repository.CountryRepository;
import com.meet.partner.utility.ConstantUtility;
import com.meet.partner.utility.PartnerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PartnerUtils partnerUtils;

    @Autowired
    private PartnerExceptionComponent partnerExceptionComponent;

    @Override
    @Transactional
    public Country create(String countryCode, String countryName, String countrySynonyms) {
        Country country = new Country();
        country.setCountryCode(countryCode.trim().toUpperCase());
        country.setCountryName(countryName.trim());
        country.setCountrySynonyms(getPipeSeparatedString(countrySynonyms));
        return countryRepository.save(country);
    }

    @Override
    public Country update(String countryCode, String countryName, String countrySynonyms) {
        Country country = getByCountryCode(countryCode);
        country.setCountryName(countryName.trim());
        country.setCountrySynonyms(getPipeSeparatedString(countrySynonyms));
        return countryRepository.save(country);
    }

    @Override
    public Country update(String countryCode) {
        return null;
    }


    @Override
    public Country getByCountryCode(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }

    private boolean isValidPipeSeparatedString(String docs) {
        if (partnerUtils.stringNotEmpty(docs) && docs.contains("|")) {
            return true;
        }else {
            return false;
        }
    }

    private boolean isValidPipeSeparatedSlug(String docs) {
        if (partnerUtils.stringNotEmpty(docs) && docs.contains("|")) {
            String [] validIdentityDocs = docs.split(ConstantUtility.PIPE_SEPARATED_REGEX);
            for (String validIdentity : validIdentityDocs) {
                if (validIdentity.contains(" ")) {
                    return false;
                }
            }
            return true;
        }else {
            return false;
        }
    }

    private String getPipeSeparatedString(String synonyms) {
        if (partnerUtils.stringNotEmpty(synonyms) && synonyms.contains("|")) {
            String [] validIdentityDocs = synonyms.split(ConstantUtility.PIPE_SEPARATED_REGEX);
            StringBuilder stringBuilder = new StringBuilder();
            for (String validIdentity : validIdentityDocs) {
                validIdentity = validIdentity.trim();
                stringBuilder.append(validIdentity + "|");
            }
            return stringBuilder.toString();
        }else {
            partnerExceptionComponent.generateException("invalid.pipeseparateddoc", InfoType.ERROR, HttpStatus.BAD_REQUEST);
            return null;
        }
    }

    private String getPipeSeparatedSlug(String docs) {
        if (partnerUtils.stringNotEmpty(docs) && docs.contains("|")) {
            String [] validIdentityDocs = docs.split(ConstantUtility.PIPE_SEPARATED_REGEX);
            StringBuilder stringBuilder = new StringBuilder();
            for (String validIdentity : validIdentityDocs) {
                validIdentity = validIdentity.trim();
                validIdentity = partnerUtils.toSlug(validIdentity);
                stringBuilder.append(validIdentity + "|");
            }
            return stringBuilder.toString();
        }else {
            partnerExceptionComponent.generateException("invalid.pipeseparateddoc", InfoType.ERROR, HttpStatus.BAD_REQUEST);
            return null;
        }
    }

    @Override
    public Country getByCountryName(String countryName) {
        return countryRepository.findByCountryName(countryName);
    }

    @Override
    public Country getByCountryNameContaining(String countryName) {
        return countryRepository.findByCountryNameContaining(countryName);
    }

    @Override
    public List<Country> getAllCountry(){
        return countryRepository.findAll();
    }

}
