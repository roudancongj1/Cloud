package com.sch.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sch.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Gao
 * @since 2022-01-16
 */
@Repository
@Mapper
public interface CityMapper extends BaseMapper<City> {

}
