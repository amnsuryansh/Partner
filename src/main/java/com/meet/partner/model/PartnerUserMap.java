package com.meet.partner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.meet.partner.codetype.PartnerActivity;
import com.meet.partner.codetype.PartnerRequestStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity(name = "partner_user_map")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class PartnerUserMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerUserMapID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "userID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partnerUser", referencedColumnName = "userID", nullable = false)
    private User partnerUser;

    @Column
    @Enumerated(EnumType.STRING)
    private PartnerRequestStatus partnerRequestStatus;

    @Column
    @Enumerated(EnumType.STRING)
    private PartnerActivity partnerActivity;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    @LastModifiedDate
    private Date lastModifiedDate;

    @NotBlank(message = "Registered By IP is mandatory.")
    @Column(nullable = false, updatable = false)
    private String registeredByIp;

    @NotBlank(message = "Last Modified By IP is mandatory.")
    @Column(nullable = false)
    private String lastModifiedByIp;

}
