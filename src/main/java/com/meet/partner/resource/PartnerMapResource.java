package com.meet.partner.resource;

import com.meet.partner.pojo.request.*;
import com.meet.partner.service.PartnerUserMapService;
import com.meet.partner.service.ResponseMessageService;
import com.meet.partner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/partner/map/")
public class PartnerMapResource {

    @Autowired
    private ResponseMessageService responseMessageService;

    @Autowired
    private UserService userService;

    @Autowired
    private PartnerUserMapService partnerUserMapService;

    @RequestMapping(value = "/create-partner", method = RequestMethod.POST)
    public ResponseEntity createUser(@Validated @RequestBody CreatePartnerUserMapRequest createPartnerUserMapRequest, HttpServletRequest httpServletRequest){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(partnerUserMapService.createPartnerUserMap(createPartnerUserMapRequest, httpServletRequest),
                                null, HttpStatus.OK));
    }

    @RequestMapping(value = "/update-partner", method = RequestMethod.POST)
    public ResponseEntity updateUserByEmail(@Validated @RequestBody CreatePartnerUserMapRequest createPartnerUserMapRequest, HttpServletRequest httpServletRequest){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(partnerUserMapService.updatePartnerUserMap(createPartnerUserMapRequest, httpServletRequest),
                                null, HttpStatus.OK));
    }

    @RequestMapping(value = "/get-partner/all", method = RequestMethod.POST)
    public ResponseEntity getAllUsers(@Validated @RequestBody GetPartnerUserMapRequest getPartnerUserMapRequest){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(partnerUserMapService.getAllPartnerOfUser(getPartnerUserMapRequest),
                                null, HttpStatus.OK));
    }

    @RequestMapping(value = "/get-partner/{partnerUserId}", method = RequestMethod.GET)
    public ResponseEntity getPartneruserMapById(@Validated @PathVariable("partnerUserId") Long partnerUserId){
        return ResponseEntity
                .ok(responseMessageService
                        .generateResponseMessage(partnerUserMapService.getPartnerUserMapById(partnerUserId),
                                null, HttpStatus.OK));
    }

}
