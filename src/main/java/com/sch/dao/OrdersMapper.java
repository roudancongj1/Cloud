package com.sch.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sch.pojo.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Gao
 * @since 2022-04-13
 */
@Mapper
@Repository
public interface OrdersMapper extends BaseMapper<Orders> {

}
