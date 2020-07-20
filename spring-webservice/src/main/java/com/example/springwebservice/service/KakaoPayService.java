package com.example.springwebservice.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;


import com.example.springwebservice.domain.KakaoPay.*;
import com.example.springwebservice.domain.rent.Rent;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.service.mapper.PaymentMapper;
import com.example.springwebservice.service.mapper.RentMapper;
import com.example.springwebservice.web.PaymentSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.java.Log;

@Service
@Transactional(readOnly = true)
@Log
public class KakaoPayService {

    private static final String HOST = "https://kapi.kakao.com";

    private KakaoPayCancelVO kakaoPayCancelVO1;
    private KakaoPayReadyVO kakaoPayReadyVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;

    @Autowired
    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;
    private RentMapper rentMapper;

    public String kakaoPayReady(RentalRequestInfo info) {
        System.out.println("kakaoPayReady");
        RestTemplate restTemplate = new RestTemplate();
        // 서버로 요청할 Heaader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "***admin key***");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        int tid = (int)paymentRepository.count();

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1004");
        params.add("partner_user_id", info.getUser_id());
        params.add("item_name", String.valueOf(info.getItem_idx()));
        params.add("quantity","1");
        params.add("total_amount", String.valueOf(info.getTotal_amount()));
        params.add("tax_free_amount", "0");

        params.add("approval_url", "http://13.125.236.67:8080/kakaoPaySuccess");
        params.add("cancel_url", "http://13.125.236.67:8080/kakaoCancel");
        params.add("fail_url", "http://13.125.236.67:8080/kakaoFail");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            System.out.println("kakaoPayReadVO");
            kakaoPayReadyVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyVO.class);
            log.info("" + kakaoPayReadyVO);
            return kakaoPayReadyVO.getNext_redirect_mobile_url();
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "/kakaoPaySuccess";
    }

    public KakaoPayApprovalVO kakaoPayInfo(String pg_token,Rent rent) {
        log.info("KakaoPayInfoVO............................................");
        log.info("-----------------------------");

        RestTemplate restTemplate = new RestTemplate();

        System.out.println("1");
        // 서버로 요청할 Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "***admin key***");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();

        params.add("cid", "TC0ONETIME");

        params.add("tid", kakaoPayReadyVO.getTid());
        System.out.println("kakaoPayReadVO.getTid(): "+kakaoPayReadyVO.getTid());

        params.add("partner_order_id", "1004");
        params.add("partner_user_id", rent.getUSER_ID());
        params.add("pg_token", pg_token);
        //params.add("total_amount", kakaoPayApprovalVO.getAmount().getTotal());

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalVO.class);
            log.info("" + kakaoPayApprovalVO);



            return kakaoPayApprovalVO;
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }


    //@Transactional
    public void applyCancelService(String user_id){
        System.out.println("service- user_id: "+user_id);
        //rentMapper.deleteRent(user_id);
        rentMapper.cancelRent(user_id);
        System.out.println("delete rent information");
        paymentMapper.updatePayment(user_id);
        System.out.println("update payment");
    }



    public KakaoPayCancelVO kakaoCancelGO(String user_id,Payment payment) {
        RestTemplate restTemplate = new RestTemplate();

        // 서버로 요청할 Heaader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK " + "admin key");
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        System.out.println("kakaoCancelGO- user_id: "+user_id);
        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", payment.getTID());
        params.add("cancel_amount", String.valueOf(payment.getTOTAL_AMOUNT()));
        params.add("cancel_tax_free_amount", "0");

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayCancelVO1 = restTemplate.postForObject(new URI(HOST + "/v1/payment/cancel"), body, KakaoPayCancelVO.class);

            log.info("" + kakaoPayCancelVO1);

            System.out.println("aid : " + kakaoPayCancelVO1.aid.toString());
            System.out.println("tid : " + kakaoPayCancelVO1.tid.toString());
            System.out.println("status : " + kakaoPayCancelVO1.status.toString());
            System.out.println("payment_method_type : " + kakaoPayCancelVO1.payment_method_type.toString());
            System.out.println("item_name : " + kakaoPayCancelVO1.item_name.toString());
            System.out.println("quantity : " + Integer.toString(kakaoPayCancelVO1.quantity));
            System.out.println("amount's total : " + Integer.toString(kakaoPayCancelVO1.amount.total));
            System.out.println("canceled_amount total : " + Integer.toString(kakaoPayCancelVO1.canceled_amount.total));
            System.out.println("cancel_available_amount : " + Integer.toString(kakaoPayCancelVO1.cancel_available_amount.total));
            return kakaoPayCancelVO1;
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
