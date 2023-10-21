package com.meet.partner.service;

import com.meet.partner.codetype.InfoType;
import com.meet.partner.codetype.PartnerRequestStatus;
import com.meet.partner.exception.service.PartnerExceptionComponent;
import com.meet.partner.model.PartnerUserMap;
import com.meet.partner.model.User;
import com.meet.partner.pojo.request.CreatePartnerUserMapRequest;
import com.meet.partner.pojo.request.GetPartnerUserMapRequest;
import com.meet.partner.repository.PartnerUserMapRepository;
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

@Service
public class PartnerUserMapServiceImpl implements PartnerUserMapService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PartnerUserMapRepository partnerUserMapRepository;

    @Autowired
    private PartnerExceptionComponent partnerExceptionComponent;

    @Autowired
    private PartnerUtils partnerUtils;

    public PartnerUserMap createPartnerUserMap (CreatePartnerUserMapRequest createPartnerUserMapRequest, HttpServletRequest httpServletRequest) {
        String registeredByIp = partnerUtils.getIPForRequest(httpServletRequest);
        String registeredByUserAgent = httpServletRequest.getHeader("User-Agent");
        User user = userRepository.findByUserID(createPartnerUserMapRequest.getUserId());
        User partnerUser = userRepository.findByUserID(createPartnerUserMapRequest.getPartnerUserId());
        PartnerUserMap partnerUserMap = partnerUserMapRepository.findByUserAndPartnerUserAndPartnerActivityAndPartnerStatus(user, partnerUser, createPartnerUserMapRequest.getPartnerActivity(), createPartnerUserMapRequest.getPartnerRequestStatus());
        if (partnerUserMap == null) {
            partnerUserMap = new PartnerUserMap();
            partnerUserMap.setUser(user);
            partnerUserMap.setPartnerUser(partnerUser);
            partnerUserMap.setPartnerRequestStatus(PartnerRequestStatus.REQUEST_SENT);
            partnerUserMap.setPartnerActivity(createPartnerUserMapRequest.getPartnerActivity());
            partnerUserMap.setCreatedDate(new Date());
            partnerUserMap.setLastModifiedDate(new Date());
            partnerUserMap.setRegisteredByIp(registeredByIp);
            partnerUserMap.setLastModifiedByIp(registeredByIp);
            partnerUserMapRepository.save(partnerUserMap);
        }
        else {
            partnerExceptionComponent.generateException("user.already.exists", InfoType.ERROR, HttpStatus.BAD_REQUEST);
        }
        return partnerUserMap;
    }

    public PartnerUserMap updatePartnerUserMap (CreatePartnerUserMapRequest createPartnerUserMapRequest, HttpServletRequest httpServletRequest) {
        String registeredByIp = partnerUtils.getIPForRequest(httpServletRequest);
        String registeredByUserAgent = httpServletRequest.getHeader("User-Agent");
        User user = userRepository.findByUserID(createPartnerUserMapRequest.getUserId());
        User partnerUser = userRepository.findByUserID(createPartnerUserMapRequest.getPartnerUserId());
        PartnerUserMap partnerUserMap = partnerUserMapRepository.findByUserAndPartnerUserAndPartnerActivityAndPartnerStatus(user, partnerUser, createPartnerUserMapRequest.getPartnerActivity(), createPartnerUserMapRequest.getPartnerRequestStatus());
        if (partnerUserMap != null) {
            partnerUserMap.setPartnerRequestStatus(PartnerRequestStatus.REQUEST_ACCEPTED);
            partnerUserMap.setLastModifiedDate(new Date());
            partnerUserMap.setLastModifiedByIp(registeredByIp);
            partnerUserMapRepository.save(partnerUserMap);
            return partnerUserMap;
        }
        else {
            partnerExceptionComponent.generateException("partner.already.exists", InfoType.ERROR, HttpStatus.BAD_REQUEST);
            return null;
        }
    }

    public Page<PartnerUserMap> getAllPartnerOfUser (GetPartnerUserMapRequest getPartnerUserMapRequest) {
        Pageable pageable = PageRequest.of(getPartnerUserMapRequest.getPage(), getPartnerUserMapRequest.getSize(), getPartnerUserMapRequest.getOrder(), getPartnerUserMapRequest.getSort());
        User user = userRepository.findByUserID(getPartnerUserMapRequest.getUserId());
        if (user != null) {
            Page<PartnerUserMap> partnerUserList = partnerUserMapRepository.findByUser(user, pageable);
            return partnerUserList;
        }
        return null;
    }

    @Override
    public PartnerUserMap getPartnerUserMapById(Long partnerUserMapId) {
        return partnerUserMapRepository.findByPartnerUserMapID(partnerUserMapId);
    }

}
