package com.gao.service.myserinpl;

import com.gao.service.myser.myser;
import org.springframework.stereotype.Service;

@Service("myser1")
public class myser1impl implements myser {
    @Override
    public String hello() {
        return "hello service1";
    }
}
