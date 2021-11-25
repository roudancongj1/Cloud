package com.gao.service.myserinpl;

import com.gao.service.myser.myser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("myser1")
public class myser1impl implements myser {
    @Override
    public String hello() {
        return "hello service1";
    }

    @Override
    public List<Map<String,String>> selecttwo() {
        return null;
    }
}
