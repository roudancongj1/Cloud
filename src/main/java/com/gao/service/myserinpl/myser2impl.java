package com.gao.service.myserinpl;

import com.gao.dao.usermapper;
import com.gao.service.myser.myser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("myser2")
public class myser2impl implements myser {
    @Autowired
    private usermapper u;
    @Override
    public String hello() {
        return "hello service2";
    }

    @Override
    public List<Map<String,String>> selecttwo() {
        return u.selecttwo();
    }
}
