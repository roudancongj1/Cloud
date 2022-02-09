package com.sch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Auth: Gao
 * @Date: 2022/2/9 15:56
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserForm {

    private String userNumber;

    private String userPass;

    private String captcha;

}
