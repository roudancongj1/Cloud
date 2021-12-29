package com.gao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auth: Gao
 * @Date: 2021/12/29 23:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class data {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String city;
    private String eat;
    private Integer Wifi;
}
