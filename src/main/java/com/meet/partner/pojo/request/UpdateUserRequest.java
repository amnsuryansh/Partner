package com.meet.partner.pojo.request;

import com.meet.partner.codetype.UserStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateUserRequest {
    private Long userId;
    private String email;
    @NotNull
    private String firstName;

    private String middleName;

    private String lastName;
    @NotNull
    private String userName;
    @NotNull
    private String addressLine1;

    private String addressLine2;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String postalCode;

    @NotNull
    private String countryCode;

    private String fbProfileUrl;

    private String twitterProfileUrl;

    @NotNull
    private String phoneNumber;

    private String selfDescription;

    private UserStatus userStatus;
}
