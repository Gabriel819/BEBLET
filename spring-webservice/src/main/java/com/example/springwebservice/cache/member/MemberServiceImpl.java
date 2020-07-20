package com.example.springwebservice.cache.member;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Member> retrieveMember() throws Exception {

        List<Member> member = retrieveMember(Auth.MEMBER);
        member.addAll(retrieveMember(Auth.ADMIN));
        member = member.stream().distinct().collect(Collectors.toList());
        return member;
    }

    @Override
    @Cacheable(value = "memberCache", key = "#auth")
    public List<Member> retrieveMember(Auth auth) throws Exception {

        logger.info("member of {} is loading", auth);

        List<Member> member = null;

        switch (auth) {
            case ADMIN : member = retrieveMemberOfAdmin(); break;
            case MEMBER : member = retrieveMemberOfMember(); break;
        }

        return member;
    }

    private List<Member> retrieveMemberOfAdmin() throws Exception {

        List<Member> member = new ArrayList<>();
        member.add(new Member(1, "member1","01011111111","11@naver.com", "11","11",
                            "Female","11",1));
        member.add(new Member(2, "member2","01022222222","22@naver.com", "22","22",
                "Female","22",2));
        member.add(new Member(3, "member33","01033333333","33@naver.com", "33","33",
                "Male","33",3));
        member.add(new Member(4, "member44","01044444444","44@naver.com", "44","44",
                "Female","44",4));

        return member;
    }

    private List<Member> retrieveMemberOfMember() throws Exception {

        List<Member> member = new ArrayList<>();
        member.add(new Member(1, "member1","01011111111","11@naver.com", "11","11",
                "Female","11",1));
        member.add(new Member(2, "member2","01022222222","22@naver.com", "22","22",
                "Female","22",2));

        return member;
    }

}
