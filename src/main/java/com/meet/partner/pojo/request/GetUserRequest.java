package com.meet.partner.pojo.request;

import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;

@Data
public class GetUserRequest {
    @NotNull
    private int page;
    @NotNull
    private int size;
    private String sort;
    private Sort.Direction order;

}
