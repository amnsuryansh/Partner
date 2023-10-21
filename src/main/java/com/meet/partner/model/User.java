package com.meet.partner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.meet.partner.codetype.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity(name = "partner_user")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @NotNull(message = "User first name is mandatory.")
    @Column(nullable = false)
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column
    private Integer age;

    @NotBlank(message = "Email is mandatory.")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Username is mandatory.")
    @Column(nullable = false, unique = true)
    private String userName;

    @NotNull(message = "Password is mandatory.")
    @Column(nullable = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    private String addressLine1;

    @Column
    private String addressLine2;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "countryCode", referencedColumnName = "countryCode")
    private Country country;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String countryCode;

    @Column
    private String fbProfileUrl;

    @Column
    private String twitterProfileUrl;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(length = 500)
    private String selfDescription;

    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @NotBlank(message = "Registered By IP is mandatory.")
    @Column(nullable = false, updatable = false)
    private String registeredByIp;

    @NotBlank(message = "Last Modified By IP is mandatory.")
    @Column(nullable = false)
    private String lastModifiedByIp;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country=" + country +
                ", countryCode='" + countryCode + '\'' +
                ", fbProfileUrl='" + fbProfileUrl + '\'' +
                ", twitterProfileUrl='" + twitterProfileUrl + '\'' +
                ", phoneNUmber='" + phoneNumber + '\'' +
                ", selfDescription='" + selfDescription + '\'' +
                ", registeredByIp='" + registeredByIp + '\'' +
                ", lastModifiedByIp='" + lastModifiedByIp + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.firstName == null)? 0 :this.firstName.hashCode()));
        result = ((result* 31)+((this.middleName == null)? 0 :this.middleName.hashCode()));
        result = ((result* 31)+((this.lastName == null)? 0 :this.lastName.hashCode()));
        result = ((result* 31)+((this.email == null)? 0 :this.email.hashCode()));
        result = ((result* 31)+((this.userName == null)? 0 :this.userName.hashCode()));
        result = ((result* 31)+((this.addressLine1 == null)? 0 :this.addressLine1.hashCode()));
        result = ((result* 31)+((this.addressLine2 == null)? 0 :this.addressLine2.hashCode()));
        result = ((result* 31)+((this.city == null)? 0 :this.city.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.postalCode == null)? 0 :this.postalCode.hashCode()));
        result = ((result* 31)+((this.country == null)? 0 :this.country.hashCode()));
        result = ((result* 31)+((this.fbProfileUrl == null)? 0 :this.fbProfileUrl.hashCode()));
        result = ((result* 31)+((this.twitterProfileUrl == null)? 0 :this.twitterProfileUrl .hashCode()));
        result = ((result* 31)+((this.phoneNumber == null)? 0 :this.phoneNumber .hashCode()));
        result = ((result* 31)+((this.selfDescription == null)? 0 :this.selfDescription .hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User rhs = ((User) other);
        if (userID != rhs.userID) {
            return false;
        }
        return (((((((((((((((((((((((((((((Objects.equals(this.firstName, rhs.firstName))
                 &&  (Objects.equals(this.middleName, rhs.middleName)))
                 &&  (Objects.equals(this.lastName, rhs.lastName)))
                 &&  (Objects.equals(this.email, rhs.email)))
                 &&  (Objects.equals(this.userName, rhs.userName)))
                 &&  (Objects.equals(this.addressLine1, rhs.addressLine1)))
                 &&  (Objects.equals(this.addressLine2, rhs.addressLine2)))
                 &&  (Objects.equals(this.city, rhs.city)))
                 &&  (Objects.equals(this.state, rhs.state)))
                 &&  (Objects.equals(this.postalCode, rhs.postalCode)))
                 &&  (Objects.equals(this.country, rhs.country)))
                 &&  (Objects.equals(this.fbProfileUrl, rhs.fbProfileUrl)))
                 &&  (Objects.equals(this.twitterProfileUrl, rhs.twitterProfileUrl)))
                 &&  (Objects.equals(this.phoneNumber, rhs.phoneNumber)))
                 &&  (Objects.equals(this.selfDescription, rhs.selfDescription)))))))))))))))));
    }
}
