package com.example.springwebservice.domain.controller;

import com.example.springwebservice.domain.member.KakaoLoginTokenResponse;
import com.example.springwebservice.domain.member.LoginAuthInfo;
import com.example.springwebservice.domain.member.MemberRepository;
import com.example.springwebservice.service.KakaoLoginService;
import com.example.springwebservice.web.MemberSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // @ResponseBody를 모든 메소드에 적용
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class LoginController {

    private KakaoLoginService kakaoLoginService;
    private MemberRepository memberRepository;

    @PostMapping(path = "/keyRequest")
    public KakaoLoginTokenResponse transferPublicKey() {
        KakaoLoginTokenResponse res = new KakaoLoginTokenResponse();

        res.setPubKey(kakaoLoginService.getPublicKey());

        return res;
    }

//	@PostMapping(path = "/encode")
//	public String encodePlainData(@RequestBody KakaoLoginTokenResponse res) {
//		return kakaoLoginService.encode(res.getId());
//	}

    @PostMapping(path = "/decode")
    public String decodeEncryptedData(@RequestBody KakaoLoginTokenResponse res) {

        return kakaoLoginService.decode(res.getEncryptedUserId());
    }

    @PostMapping(path = "/joinAndLoginRequest")
    public KakaoLoginTokenResponse joinAndLogin(@RequestBody LoginAuthInfo info) {
        KakaoLoginTokenResponse res = new KakaoLoginTokenResponse();
        res.setLoggedIn(false);
        if(kakaoLoginService.join(info)) {
            if(!kakaoLoginService.isMember(info.getUserId())){
                MemberSaveRequestDto memberSaveRequestDto = new MemberSaveRequestDto();
                memberSaveRequestDto.setUSER_ID(info.getUserId());
                memberSaveRequestDto.setUSER_NICKNAME(info.getNickname());
//                memberSaveRequestDto.setUSER_NAME("name");
//                memberSaveRequestDto.setUSER_PHONE("000-0000-0000");
//                memberSaveRequestDto.setUSER_PW("1232");
                memberSaveRequestDto.setSTAMP(0);

                memberRepository.save(memberSaveRequestDto.toEntity());
            }
            if (kakaoLoginService.login(info)) {
                res.setLoggedIn(true);
            }
        }
        return res;
    }

}
