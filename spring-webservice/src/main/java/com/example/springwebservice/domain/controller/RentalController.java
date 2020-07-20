package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.KakaoPay.KakaoPayApprovalVO;
import com.example.springwebservice.domain.KakaoPay.KakaoPayCancelVO;
import com.example.springwebservice.domain.KakaoPay.Payment;
import com.example.springwebservice.domain.KakaoPay.PaymentRepository;
import com.example.springwebservice.domain.cabinet.Cabinet;
import com.example.springwebservice.domain.item.Item;
import com.example.springwebservice.domain.rent.Rent;
import com.example.springwebservice.domain.rent.RentalRequestInfo;
import com.example.springwebservice.domain.rent.RentRepository;
import com.example.springwebservice.service.KakaoPayService;
import com.example.springwebservice.service.RentalService;
import com.example.springwebservice.service.mapper.ItemMapper;
import com.example.springwebservice.service.mapper.MemberMapper;
import com.example.springwebservice.service.mapper.PaymentMapper;
import com.example.springwebservice.service.mapper.RentMapper;
import com.example.springwebservice.web.PaymentSaveRequestDto;
import com.example.springwebservice.web.RentSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.java.Log;

@Log
@Transactional(readOnly = false)
@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class RentalController {

    @Autowired
    private MemberMapper memberMapper;
    private ItemMapper itemMapper;
    private PaymentMapper paymentMapper;
    private RentMapper rentMapper;
    private RentalService rentalService;
    private KakaoPayService kakaoPayService;
    private RentRepository rentRepository;
    private PaymentRepository paymentRepository;

    @RequestMapping(value="/TestCode",method={ RequestMethod.GET, RequestMethod.POST })
    public void TestCode(@RequestBody RentalRequestInfo info){
//        kakaoPayService.applyCancelService(info.getUser_id());
        rentMapper.cancelRent(info.getUser_id());
        System.out.println("delete rent information");
        paymentMapper.updatePayment(info.getUser_id());
        System.out.println("update payment");
    }
    // 사용자가 대여할 물품과 시간을 선택하면 이용 가능 캐비넷과 추천 캐비넷 정보(리스트) 반환
    @PostMapping(path = "/recommendCabinet")
    @GetMapping(path = "/recommendCabinet")
    public ArrayList<Cabinet> recommendCabinet(@RequestBody RentalRequestInfo info){
        System.out.println("Category Idx : " + info.getCategory_idx());
        System.out.println("Start time : " + info.getStart());
        System.out.println("End time : " + info.getEnd());

        return rentalService.findCabinet(info);
    }

    // 카카오페이 결제 요청
    @PostMapping(path = "/kakaoPay")
    @GetMapping(path = "/kakaoPay")
    public String kakaoPayRequest(@RequestBody RentalRequestInfo info){
        log.info("kakaoPay post............................................");

        //KakaoPayApprovalVO approvalVO = new KakaoPayApprovalVO();

        //return "redirect:" + kakaoPayService.kakaoPayReady(info);
        return kakaoPayService.kakaoPayReady(info);
    }
    @PostMapping(path="/kakaoFail")
    @GetMapping(path="/kakaoFail")
    public void kakaoFail(){
        System.out.println("kakaoFail");
    }

    // 카카오페이 성공하면 카카오 서버에서 이 api로 리다이렉트
    @RequestMapping(value="/kakaoPaySuccess",method={ RequestMethod.GET, RequestMethod.POST })
    public KakaoPayApprovalVO kakaoPayRequest(@RequestParam("pg_token") String pg_token, Model model){
        log.info("kakaoPaySuccess get............................................");
        log.info("kakaoPaySuccess pg_token : " + pg_token);

        Rent rent = rentMapper.RecentRent();
        model.addAttribute("info", kakaoPayService.kakaoPayInfo(pg_token,rent));

        KakaoPayApprovalVO kakaoPayApprovalVO = (KakaoPayApprovalVO)model.getAttribute("info");
        System.out.println("결제 정보를 저장하겠습니다.");
        PaymentSaveRequestDto dto = new PaymentSaveRequestDto();
        dto.setTID(kakaoPayApprovalVO.getTid());
        dto.setPARTNER_ORDER_ID(kakaoPayApprovalVO.getPartner_order_id());
        dto.setPARTNER_USER_ID(kakaoPayApprovalVO.getPartner_order_id());
        dto.setITEM_NAME(kakaoPayApprovalVO.getItem_name());
        dto.setQUANTITY(kakaoPayApprovalVO.getQuantity());
        dto.setTOTAL_AMOUNT(kakaoPayApprovalVO.getAmount().getTotal());
        dto.setTAX_FREE_AMOUNT(kakaoPayApprovalVO.getTax_free_amount());

        paymentRepository.save(dto.toEntity());
        //Payment payment = paymentMapper.RecentPayment();

//        KakaoPayApprovalVO kakaoPayApprovalVO=(KakaoPayApprovalVO)model.getAttribute("info");
//        paymentMapper.updateTID(kakaoPayApprovalVO.getTid(),payment.getIDX());

        return kakaoPayApprovalVO;
    }

    // 사용자의 결제 내역 리스트 리턴
    @RequestMapping(value="/returnPaymentList",method={ RequestMethod.GET, RequestMethod.POST })
    public Payment returnPayment(@RequestBody RentalRequestInfo info){  // null 리턴되면 결제 제대로 안된 것
        Payment payment = paymentMapper.findPaymentList(info.getUser_id());

        return payment;
    }

    //
    @RequestMapping(value="/CancleOrReturn",method={ RequestMethod.GET, RequestMethod.POST })
    public String CancleOrReturn(@RequestBody RentalRequestInfo info){
        List<Rent> rentList=rentalService.returnRentList(info.getUser_id());
        for(Rent rent:rentList){
            if(rent.getIDX()==info.getRent_idx()){
                if(rent.getSTART_TIME().isAfter(LocalDateTime.now())) return "cancel";
                else return "return";
            }
        }
        return "cancel";
    }

    // 사용자의 대여 내역 리스트 리턴
    @RequestMapping(value="/returnRentList",method={ RequestMethod.GET, RequestMethod.POST })
    public List<Rent> returnRent(@RequestBody RentalRequestInfo info){  // null 리턴되면 결제 제대로 안된 것
        System.out.println("request info.getUserId"+info.getUser_id());

        List<Rent> rent=rentalService.returnRentList(info.getUser_id());
        System.out.println("서버 이상 없음");
        System.out.println("rent list size: "+ rent.size());
        return rent;
    }

    // 물품 반납 -> rent 정보 업데이트(상태:이용 완료), item 정보 업데이트(상태: 이용 가능)
    @RequestMapping(value="/returnItem",method={ RequestMethod.GET, RequestMethod.POST })
    public void returnItem(@RequestBody RentalRequestInfo info){
        rentMapper.updateRent(info.getUser_id());
        Rent rent = rentMapper.updateRentInfo(info.getUser_id());
        itemMapper.updateReturnItem(rent.getITEM_IDX());
    }

    // 대여 신청 -> item, rent, member 정보 업데이트 및 추가
    @RequestMapping(value="/apply",method={ RequestMethod.GET, RequestMethod.POST })
    public RentSaveRequestDto apply(@RequestBody RentalRequestInfo info){
        System.out.println("User Id : " + info.getUser_id());
        System.out.println("Start Cabinet Idx : " + info.getStart_cabinet_idx());

        RentSaveRequestDto dto= rentalService.applyService(info);
        System.out.println("Start time: "+dto.getSTART_TIME());
        rentRepository.save(dto.toEntity());

        System.out.println("rentRepository save 완료");
        // stamp 추가 -> member 정보 update
        if(info.getRecommend()==1)
            memberMapper.addStamp(info.getUser_id(),2);
        else
            memberMapper.addStamp(info.getUser_id(),1);

        return dto;
    }


    // 신청 취소 -> 결제 취소 및 대여 내역 삭제
    @RequestMapping(value="/kakaoCancel",method={ RequestMethod.GET, RequestMethod.POST })
    public KakaoPayCancelVO kakaoCancel(@RequestBody RentalRequestInfo info){
        log.info("kakaoCancel post............................................");

        System.out.println("controller- user_id: "+info.getUser_id());

        rentMapper.cancelRent(info.getUser_id());
        System.out.println("delete rent information");
        paymentMapper.updatePayment(info.getUser_id());
        System.out.println("update payment");
        //kakaoPayService.applyCancelService(info.getUser_id());

        Payment payment=paymentMapper.findPaymentList(info.getUser_id());
        return kakaoPayService.kakaoCancelGO(info.getUser_id(),payment);
    }

}
