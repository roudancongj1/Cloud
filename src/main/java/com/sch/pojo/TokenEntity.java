package com.sch.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/1/25 21:16
 */

@Data
@Component
public class TokenEntity implements Serializable {

    private long userId;

    private String token;

    private LocalDate expireTime;

    private LocalDate updateTime;

    private Map<String,Object> data =new HashMap();

    public TokenEntity set(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}
