package com.example.springwebservice.service.mapper;

import com.example.springwebservice.domain.rent.Rent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RentMapper {

    @Select("SELECT * FROM rent WHERE USER_ID=#{user_id} AND STATE=1")
    List<Rent> findRentList(String user_id);

    @Select("SELECT * FROM rent ORDER BY START_TIME DESC LIMIT 1")
    Rent RecentRent();

    @Delete("DELETE FROM rent WHERE USER_ID=#{user_id}")
    void deleteRent(String user_id);

    //1 - 이용 중, 0 - 이용 종료, -1: 신청 취소
    @Update("UPDATE rent SET STATE=-1 WHERE USER_ID=#{user_id} ORDER BY START_TIME DESC LIMIT 1")
    void cancelRent(String user_id);

    //1 - 이용 중, 0 - 이용 종료
    @Update("UPDATE rent SET STATE=0 WHERE USER_ID=#{user_id} ORDER BY START_TIME DESC LIMIT 1")
    void updateRent(String user_id);



    @Select("SELECT * FROM rent WHERE USER_ID=#{user_id} ORDER BY START_TIME DESC LIMIT 1")
    Rent updateRentInfo(String user_id);
}
