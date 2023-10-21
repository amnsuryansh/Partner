package com.meet.partner.pojo.request;

import com.meet.partner.codetype.PartnerActivity;
import com.meet.partner.codetype.PartnerRequestStatus;
import lombok.Data;

@Data
public class CreatePartnerUserMapRequest {
    private Long userId;
    private Long partnerUserId;
    private PartnerRequestStatus partnerRequestStatus;
    private PartnerActivity partnerActivity;
}
