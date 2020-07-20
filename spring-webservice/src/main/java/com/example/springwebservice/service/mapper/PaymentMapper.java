package com.example.springwebservice.service.mapper;

import com.example.springwebservice.domain.KakaoPay.Payment;
import com.example.springwebservice.domain.rent.Rent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PaymentMapper {
//    @Update("UPDATE member SET STAMP=STAMP+#{num} WHERE USER_ID=#{userID}")
//    void addStamp(String userID,Integer num);


    @Select("SELECT * FROM payment ORDER BY START_TIME DESC LIMIT 1")
    Payment RecentPayment();

    @Select("SELECT * FROM payment WHERE PARTNER_USER_ID=#{user_id} ORDER BY START_TIME DESC LIMIT 1")
    Payment findPaymentList(String user_id);

    @Update("UPDATE payment SET TID=#{tid} WHERE IDX=#{IDX}")
    void updateTID(String tid, Integer IDX);

    //1 - 정상 결제, 0 - 결제 취소
    @Update("UPDATE payment SET STATE=0 WHERE PARTNER_USER_ID=#{user_id} ORDER BY START_TIME DESC LIMIT 1")
    void updatePayment(String user_id);


}
