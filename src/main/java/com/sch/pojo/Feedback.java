package com.sch.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auth: Gao
 * @Date: 2022/4/13 19:37
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("feedback")
public class Feedback implements Serializable {
    @TableId(value = "feedback_id", type = IdType.AUTO)
    private Integer feedbackId;
    private String feedbackAuth;
    private String feedbackInfo;
}
