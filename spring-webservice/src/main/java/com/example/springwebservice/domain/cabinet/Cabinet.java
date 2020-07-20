package com.example.springwebservice.domain.cabinet;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Cabinet{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CABINET_IDX;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String CABINET_NAME;


    private Double LATITUDE;
    private Double LONGITUDE;

    @Builder
    public Cabinet(Integer CABINET_IDX, String CABINET_NAME, Double LATITUDE, Double LONGITUDE) {
        this.CABINET_IDX=CABINET_IDX;
        this.CABINET_NAME = CABINET_NAME;
        this.LATITUDE = LATITUDE;
        this.LONGITUDE=LONGITUDE;
    }
}
