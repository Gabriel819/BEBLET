package com.example.springwebservice.domain.member;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class KakaoLoginTokenResponse {
//    private long id;
//    private String connected_at;
//    private String properties;
    private String encryptedUserId;
    private String pubKey;
    private boolean isLoggedIn;
}