package com.example.springwebservice.domain.member;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @After
    public void cleanup() {
        memberRepository.deleteAll();
    }

    @Test
    public void Member_register(){
        //given
        memberRepository.save(Member.builder()
                .USER_ID("1234")
                .STAMP(1)
                .USER_NAME("name1")
                .USER_PHONE("000-0000-0000")
                .USER_PW("1232")
                .USER_EMAIL("aaa@gmail.com")
                .USER_GENDER("female")
                .USER_NICKNAME("nick")
                .build()
        );

        //when
        List<Member> memberList = memberRepository.findAll();

        //then
        Member member = memberList.get(0);
        assertThat(member.getUSER_NAME(), is("name1"));
    }

}
