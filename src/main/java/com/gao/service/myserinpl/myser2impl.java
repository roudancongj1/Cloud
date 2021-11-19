package com.gao.service.myserinpl;

import com.gao.service.myser.myser;
import org.springframework.stereotype.Service;

@Service("myser2")
public class myser2impl implements myser {
    @Override
    public String hello() {
        return "hello service2";
    }
}
