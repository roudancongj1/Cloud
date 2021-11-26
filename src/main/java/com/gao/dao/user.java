package com.gao.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface user{
    List<Map<String,String>> selecttwo();
}
