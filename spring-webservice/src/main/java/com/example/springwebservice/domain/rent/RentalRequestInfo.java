package com.example.springwebservice.domain.rent;

import com.example.springwebservice.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RentalRequestInfo {
    private Integer rent_idx;
    private Integer item_idx;
    private Integer category_idx;
    private String user_id;
    private Integer start_cabinet_idx;
    private Integer end_cabinet_idx;
    private Integer recommend; // 추천 사물함 사용했으면 1, 아니면 0

    //결제 정보
    private Integer total_amount;
    private LocalDateTime approved_at;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;
    private LocalDateTime end;
}
