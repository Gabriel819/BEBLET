package com.example.springwebservice.cache.member;

public class Member {
    private final long user_idx;
    private final String user_name;
    private final String user_phone;
    private final String user_email;
    private final String user_id;
    private final String user_pw;
    private final String user_gender;
    private final String user_nickname;
    private final int stamp;

    public Member(long user_idx, String user_name, String user_phone, String user_email, String user_id,
                  String user_pw, String user_gender, String user_nickname, int stamp) {
        this.user_idx = user_idx;
        this.user_name = user_name;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_id = user_id;
        this.user_pw = user_pw;
        this.user_gender = user_gender;
        this.user_nickname = user_nickname;
        this.stamp = stamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member mem = (Member) o;

        return user_idx == mem.user_idx;

    }

    @Override
    public int hashCode() {
        return (int) (user_idx ^ (user_idx >>> 32));
    }
}
