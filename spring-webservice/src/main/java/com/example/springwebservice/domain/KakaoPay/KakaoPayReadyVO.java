package com.example.springwebservice.domain.KakaoPay;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class KakaoPayReadyVO {
    //response
    private String tid, next_redirect_mobile_url;
    private LocalDateTime created_at;
}