package com.meet.partner.service;

import com.meet.partner.codetype.InfoType;
import com.meet.partner.codetype.UserStatus;
import com.meet.partner.exception.service.PartnerExceptionComponent;
import com.meet.partner.model.Country;
import com.meet.partner.model.User;
import com.meet.partner.pojo.request.CreateUserRequest;
import com.meet.partner.pojo.request.GetUserRequest;
import com.meet.partner.pojo.request.UpdateUserRequest;
import com.meet.partner.repository.UserRepository;
import com.meet.partner.utility.PartnerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CountryService countryService;

    @Autowired
    private PartnerUtils partnerUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartnerExceptionComponent partnerExceptionComponent;

    public User createUser(CreateUserRequest createUserRequest, HttpServletRequest servletRequest) {
        User user = new User();
        user.setFirstName(createUserRequest.getFirstName());
        if (createUserRequest.getMiddleName() != null) {
            user.setMiddleName(createUserRequest.getMiddleName());
        }
        if (createUserRequest.getLastName() != null) {
            user.setLastName(createUserRequest.getLastName());
        }
        if (partnerUtils.isValidEmail(createUserRequest.getEmail())) {
            user.setEmail(createUserRequest.getEmail());
        }
        else {
            partnerExceptionComponent.generateException("invalid.email", InfoType.ERROR, HttpStatus.BAD_REQUEST);
        }
        user.setUserName(createUserRequest.getUserName());
        user.setPassword(createUserRequest.getPassword());
        user.setAddressLine1(createUserRequest.getAddressLine1());
        if (createUserRequest.getAddressLine2() != null) {
            user.setAddressLine2(createUserRequest.getAddressLine2());
        }
        user.setCity(createUserRequest.getCity());
        user.setState(createUserRequest.getState());
        user.setPostalCode(createUserRequest.getPostalCode());
        Country country = countryService.getByCountryCode(createUserRequest.getCountryCode());
        if (country != null) {
            user.setCountry(country);
        }
        if (createUserRequest.getFbProfileUrl() != null) {
            user.setFbProfileUrl(createUserRequest.getFbProfileUrl());
        }
        if (createUserRequest.getTwitterProfileUrl() != null) {
            user.setTwitterProfileUrl(createUserRequest.getTwitterProfileUrl());
        }
        if (country != null && country.getCountryName() != null && country.getCountryName().equalsIgnoreCase("India")) {
            if (createUserRequest.getPhoneNumber().length() == 10) {
                user.setPhoneNumber(createUserRequest.getPhoneNumber());
            }
            else {
                partnerExceptionComponent.generateException("invalid.phone.number", InfoType.ERROR, HttpStatus.BAD_REQUEST);
            }
        }
        if (createUserRequest.getSelfDescription() != null) {
            user.setSelfDescription(createUserRequest.getSelfDescription());
        } 
        user.setUserStatus(UserStatus.ACTIVE);
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(new Date());
        String registeredByIp = partnerUtils.getIPForRequest(servletRequest);
        String registeredByUserAgent = servletRequest.getHeader("User-Agent");
        user.setRegisteredByIp(registeredByIp);
        user.setLastModifiedByIp(registeredByIp);
        return userRepository.save(user);
    }

    @Override
    public User updateUserById(UpdateUserRequest updateUserRequest, HttpServletRequest servletRequest) {
        User user = userRepository.findByUserID(updateUserRequest.getUserId());
        return setUserDetails(updateUserRequest, servletRequest, user);
    }

    private User setUserDetails(UpdateUserRequest updateUserRequest, HttpServletRequest servletRequest, User user) {
        if (user == null) {
            partnerExceptionComponent.generateException("user.doesn't.exists", InfoType.ERROR, HttpStatus.BAD_REQUEST);
        }
        else {
            user.setFirstName(updateUserRequest.getFirstName());
            if (updateUserRequest.getMiddleName() != null) {
                user.setMiddleName(updateUserRequest.getMiddleName());
            }
            if (updateUserRequest.getLastName() != null) {
                user.setLastName(updateUserRequest.getLastName());
            }
            if (partnerUtils.isValidEmail(updateUserRequest.getEmail())) {
                user.setEmail(updateUserRequest.getEmail());
            }
            else {
                partnerExceptionComponent.generateException("invalid.email", InfoType.ERROR, HttpStatus.BAD_REQUEST);
            }
            user.setUserName(updateUserRequest.getUserName());
            user.setAddressLine1(updateUserRequest.getAddressLine1());
            if (updateUserRequest.getAddressLine2() != null) {
                user.setAddressLine2(updateUserRequest.getAddressLine2());
            }
            user.setCity(updateUserRequest.getCity());
            user.setState(updateUserRequest.getState());
            user.setPostalCode(updateUserRequest.getPostalCode());
            Country country = countryService.getByCountryCode(updateUserRequest.getCountryCode());
            if (country != null) {
                user.setCountry(country);
            }
            if (updateUserRequest.getFbProfileUrl() != null) {
                user.setFbProfileUrl(updateUserRequest.getFbProfileUrl());
            }
            if (updateUserRequest.getTwitterProfileUrl() != null) {
                user.setTwitterProfileUrl(updateUserRequest.getTwitterProfileUrl());
            }
            if (country != null && country.getCountryName() != null && country.getCountryName().equalsIgnoreCase("India")) {
                if (updateUserRequest.getPhoneNumber().length() == 10) {
                    user.setPhoneNumber(updateUserRequest.getPhoneNumber());
                }
                else {
                    partnerExceptionComponent.generateException("invalid.phone.number", InfoType.ERROR, HttpStatus.BAD_REQUEST);
                }
            }
            if (updateUserRequest.getSelfDescription() != null) {
                user.setSelfDescription(updateUserRequest.getSelfDescription());
            }
            user.setLastModifiedDate(new Date());
            user.setUserStatus(updateUserRequest.getUserStatus());

            String registeredByIp = partnerUtils.getIPForRequest(servletRequest);
            user.setLastModifiedByIp(registeredByIp);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User updateUserByEmail(UpdateUserRequest updateUserRequest, HttpServletRequest servletRequest) {
        User user = userRepository.findByEmail(updateUserRequest.getEmail());
        return setUserDetails(updateUserRequest, servletRequest, user);
    }

    public User getByUserId (Long userId) {
        return userRepository.findByUserID(userId);
    }

    @Override
    public Page<User> getAllUserPagination(GetUserRequest getUserRequest) {
        Pageable pageable = PageRequest.of(getUserRequest.getPage(), getUserRequest.getSize(), getUserRequest.getOrder(), getUserRequest.getSort());
        Page<User> userList = userRepository.findAll(pageable);
        return userList;
    }

}
