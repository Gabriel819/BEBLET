package com.example.springwebservice.domain.member;

import lombok.*;


import javax.persistence.*;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Member{
    @Id
    @Column(columnDefinition = "TEXT", nullable = false)
    private String USER_ID;

    private String USER_NAME;

    private String USER_PHONE;

    private String USER_EMAIL;

    private String USER_PW;

    private String USER_GENDER;

    private String USER_NICKNAME;

    private Integer STAMP;

    @Builder
    public Member(Integer STAMP, String USER_ID, String USER_NAME, String USER_EMAIL,
                   String USER_PW, String USER_PHONE, String USER_GENDER, String USER_NICKNAME) {
        this.STAMP = STAMP;
        this.USER_ID=USER_ID;
        this.USER_NAME = USER_NAME;
        this.USER_PHONE=USER_PHONE;
        this.USER_PW=USER_PW;
        this.USER_EMAIL = USER_EMAIL;
        this.USER_GENDER = USER_GENDER;
        this.USER_NICKNAME = USER_NICKNAME;
    }

}