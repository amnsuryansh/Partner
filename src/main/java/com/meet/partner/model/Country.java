package com.meet.partner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "partner_country")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Country {
    @Id
    private String countryCode;

    @Column(unique = true, nullable = false)
    private String countryName;

    @Column
    private String countrySynonyms; // pipe {|} separated value

    @Column
    private String nationality;

    @Column
    private String isoCode;

    @Column
    @JsonIgnore
    @CreatedDate
    private Date createdDate;

    @Column
    @JsonIgnore
    @LastModifiedDate
    private Date lastModifiedDate;
}
