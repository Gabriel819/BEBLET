package com.example.springwebservice.domain.KakaoPay;

import lombok.Data;

@Data
public class AmountVO {
    public Integer total, tax_free, vat, point, discount;
}
