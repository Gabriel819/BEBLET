package com.example.springwebservice.cache.member;
import java.util.List;

public interface MemberService {
    List<Member> retrieveMember() throws Exception;
    List<Member> retrieveMember(Auth auth) throws Exception;
}
