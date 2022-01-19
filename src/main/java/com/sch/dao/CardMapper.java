package com.sch.dao;

import com.sch.pojo.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2022/1/19 15:07
 */

@Mapper
@Repository
public interface CardMapper {
    @Select("select * from card")
    List<Card> qureyAll();
}
