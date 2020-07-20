package com.example.springwebservice.cache.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MemberController {

    @Resource(name="MemberServiceImpl")
    MemberService memberService;

    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/member/members", method = {RequestMethod.GET})
    public List<Member> retrieveMemberOfMembers() throws Exception {
        return memberService.retrieveMember(Auth.MEMBER);
    }

    @RequestMapping(value = "/menus/admin", method = {RequestMethod.GET})
    public List<Member> retrieveMemberOfAdmin() throws Exception {
        return memberService.retrieveMember(Auth.ADMIN);
    }

    @RequestMapping(value = "/member", method = {RequestMethod.GET})
    public List<Member> retrieveMember() throws Exception {
        return memberService.retrieveMember();
    }

}
