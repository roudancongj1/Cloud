package com.gao.dao;

import com.gao.pojo.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface usermapper {
    List<user> selectaa();
    List<Map<String,String>> selecttwo();
}
