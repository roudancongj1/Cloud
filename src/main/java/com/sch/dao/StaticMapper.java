package com.sch.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface StaticMapper extends BaseMapper<Static> {
    @Select("select * from static")
    List<Static> queryAll();

    List<Static> queryCode(String code);
}
