package com.meet.partner.repository;

import com.meet.partner.codetype.PartnerActivity;
import com.meet.partner.codetype.PartnerRequestStatus;
import com.meet.partner.model.PartnerUserMap;
import com.meet.partner.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface PartnerUserMapRepository extends JpaRepository<PartnerUserMap, Serializable> {

    PartnerUserMap findByPartnerUserMapID(Long partnerUserMapId);

    PartnerUserMap findByUserAndPartnerUserAndPartnerActivityAndPartnerStatus(User user, User partnerUser, PartnerActivity partnerActivity, PartnerRequestStatus partnerRequestStatus);

    Page<PartnerUserMap> findByUser(User user, Pageable pageable);

}
