package com.meet.partner.service;

import com.meet.partner.model.PartnerUserMap;
import com.meet.partner.pojo.request.CreatePartnerUserMapRequest;
import com.meet.partner.pojo.request.GetPartnerUserMapRequest;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;

public interface PartnerUserMapService {
    PartnerUserMap createPartnerUserMap (CreatePartnerUserMapRequest createPartnerUserMapRequest, HttpServletRequest httpServletRequest);
    PartnerUserMap updatePartnerUserMap (CreatePartnerUserMapRequest createPartnerUserMapRequest, HttpServletRequest httpServletRequest);
    Page<PartnerUserMap> getAllPartnerOfUser (GetPartnerUserMapRequest getPartnerUserMapRequest);
    PartnerUserMap getPartnerUserMapById(Long partnerUserMapId);
}
