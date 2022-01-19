package com.sch.dao;

import com.sch.pojo.Static;
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
public interface StaticMapper {
    @Select("select * from static")
    List<Static> qureyAll();
}
