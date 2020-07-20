package com.example.springwebservice.domain.inquiry;

import com.example.springwebservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Inquiry extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IDX;

    private Integer USER_IDX;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String TITLE;

    private String CONTENT;

    private LocalDateTime REQUEST_DATE;

    @Builder
    public Inquiry(Integer IDX, Integer USER_IDX, String TITLE, String CONTENT,LocalDateTime REQUEST_DATE) {
        this.IDX=IDX;
        this.USER_IDX = USER_IDX;
        this.CONTENT=CONTENT;
        this.TITLE = TITLE;
        this.REQUEST_DATE=REQUEST_DATE;
    }

}

