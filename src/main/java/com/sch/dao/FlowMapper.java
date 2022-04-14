package com.sch.dao;

import com.sch.pojo.Flow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 流水表 Mapper 接口
 * </p>
 *
 * @author Gao
 * @since 2022-04-13
 */

@Mapper
@Repository
public interface FlowMapper extends BaseMapper<Flow> {

}
