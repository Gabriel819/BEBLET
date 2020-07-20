package com.example.springwebservice.domain.rent;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class RentResponseVO {
    private Integer RENT_IDX;
    private Integer ITEM_IDX;
    private Integer CATEGORY_IDX;

    private Integer START_CABINET_IDX;
    private Integer END_CABINET_IDX;

    private String USER_ID;

    private Integer STATE;
    private Boolean CANCEL;

    //결제 정보
    private Integer AMOUNT;
    private LocalDateTime APPROVED_AT;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime START_TIME;
    private LocalDateTime END_TIME;
}
