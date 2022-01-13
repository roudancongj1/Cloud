package com.sch.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auth: Gao
 * @Date: 2022/1/13 15:11
 */


@Data
public class User implements Serializable {
    private Integer id;
    private String name;
}
