package com.gao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auth: Gao
 * @Date: 2021/11/26 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class user implements Serializable {
    private Integer id;
    private String password;
    private Integer role;
}
