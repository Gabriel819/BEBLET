package com.example.springwebservice.domain.KakaoPay;

import java.time.LocalDateTime;

public class KakaoPayCancelVO {
    //response
    public String aid, tid, cid;
    public String status;
    public String partner_order_id, partner_user_id, payment_method_type;
    public AmountVO amount;
    public CanceledAmountVO canceled_amount;
    public CancelAvailableAmountVO cancel_available_amount;
    public String item_name, item_code, payload;
    public int quantity;
    public LocalDateTime created_at, approved_at, canceled_at;
}
