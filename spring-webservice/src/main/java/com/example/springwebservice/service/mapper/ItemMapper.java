package com.example.springwebservice.service.mapper;


import com.example.springwebservice.domain.item.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ItemMapper {
    @Select("SELECT * FROM item WHERE START_CABINET_IDX=#{startCabinetIdx}")
    List<Item> findAll(Integer startCabinetIdx);

    @Select("SELECT * FROM item WHERE CATEGORY_IDX=#{category_idx}")
    List<Item> findItemList(Integer category_idx);

    //1 - 이용 가능, 0 - 이용 불가
    @Update("UPDATE item SET STATE=0 WHERE ITEM_IDX=#{item_idx}")
    void updateUsedItem(Integer item_idx);

    //1 - 이용 가능, 0 - 이용 불가
    @Update("UPDATE item SET STATE=1 WHERE ITEM_IDX=#{item_idx}")
    void updateReturnItem(Integer item_idx);
}
