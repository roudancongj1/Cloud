package com.sch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Auth: Gao
 * @Date: 2022/4/11 12:50
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RegFrom implements Serializable {
    private String userNumber;
    private String userPass;
    private String userName;
    private String sex;
    private String userPhone;
    private String captcha;

}
