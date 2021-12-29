package com.gao.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.pojo.data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2021/12/29 23:53
 */
@Mapper
public interface dataDao extends BaseMapper<data> {
     List<data> selectall();
}
