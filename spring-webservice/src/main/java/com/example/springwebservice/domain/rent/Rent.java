package com.example.springwebservice.domain.rent;

import com.example.springwebservice.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Rent{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDX;
    
    private Integer ITEM_IDX;
    private Integer CATEGORY_IDX;

    private Integer START_CABINET_IDX;
    private Integer END_CABINET_IDX;

    private String USER_ID;


    private Integer STATE;

    //결제 정보
    private Integer AMOUNT;
    private LocalDateTime APPROVED_AT;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime START_TIME;
    private LocalDateTime END_TIME;


    @Builder
    public Rent(Integer IDX, Integer ITEM_IDX, Integer END_CABINET_IDX,Integer START_CABINET_IDX, String USER_ID,Integer CATEGORY_IDX,
               LocalDateTime START_TIME,LocalDateTime END_TIME,Integer AMOUNT,Integer STATE,LocalDateTime APPROVED_AT) {
        this.IDX=IDX;
        this.ITEM_IDX = ITEM_IDX;
        this.CATEGORY_IDX=CATEGORY_IDX;
        this.START_CABINET_IDX = START_CABINET_IDX;
        this.END_CABINET_IDX=END_CABINET_IDX;
        this.USER_ID = USER_ID;
        this.START_TIME=START_TIME;
        this.END_TIME=END_TIME;
        this.AMOUNT=AMOUNT;
        this.APPROVED_AT=APPROVED_AT;
        this.STATE=STATE;
    }
}
