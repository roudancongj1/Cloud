package com.sch.dao;

import com.sch.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Gao
 * @since 2022-01-16
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}
