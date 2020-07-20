package com.example.springwebservice.domain.KakaoPay;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String PARTNER_ORDER_ID;

    private String TID;

    private String PARTNER_USER_ID;

    private String ITEM_NAME;

    private Integer QUANTITY;

    private Integer TOTAL_AMOUNT;

    private Integer TAX_FREE_AMOUNT;

    private Integer IDX;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime START_TIME;

    @Builder
    public Payment(String PARTNER_ORDER_ID,String TID, String PARTNER_USER_ID,String ITEM_NAME,Integer QUANTITY,Integer TOTAL_AMOUNT,Integer TAX_FREE_AMOUNT, LocalDateTime START_TIME,Integer IDX) {
        this.PARTNER_ORDER_ID = PARTNER_ORDER_ID;
        this.TID=TID;
        this.PARTNER_USER_ID = PARTNER_USER_ID;
        this.ITEM_NAME=ITEM_NAME;
        this.QUANTITY=QUANTITY;
        this.TOTAL_AMOUNT = TOTAL_AMOUNT;
        this.TAX_FREE_AMOUNT=TAX_FREE_AMOUNT;
        this.START_TIME=START_TIME;
        this.IDX=IDX;
    }
}
