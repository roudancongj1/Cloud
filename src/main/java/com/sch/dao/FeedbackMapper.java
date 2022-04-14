package com.sch.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sch.pojo.Feedback;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2022/4/13 19:34
 */

@Mapper
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {


}
