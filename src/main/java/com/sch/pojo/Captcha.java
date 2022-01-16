package com.sch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * @Auth: Gao
 * @Date: 2022/1/16 19:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Captcha implements Serializable {
    private String code;
    private BufferedImage image;
}
