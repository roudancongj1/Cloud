package com.sch.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auth: Gao
 * @Date: 2022/1/14 12:24
 */

@TableName("static")
public class Static {
    private static final long serialVersionUID = 1L;

    @TableId(value = "static_id", type = IdType.AUTO)
    private Integer staticId;

    private String staticCode;

    private String staticLabel;

    private String staticValue;

    public Static() {
    }

    public Static(Integer staticId, String staticCode, String staticLabel, String staticValue) {
        this.staticId = staticId;
        this.staticCode = staticCode;
        this.staticLabel = staticLabel;
        this.staticValue = staticValue;
    }

    @Override
    public String toString() {
        return "Static{" +
                "staticId=" + staticId +
                ", staticCode='" + staticCode + '\'' +
                ", staticLabel='" + staticLabel + '\'' +
                ", staticValue='" + staticValue + '\'' +
                '}';
    }

    public Integer getStaticId() {
        return staticId;
    }

    public void setStaticId(Integer staticId) {
        this.staticId = staticId;
    }

    public String getStaticCode() {
        return staticCode;
    }

    public void setStaticCode(String staticCode) {
        this.staticCode = staticCode;
    }

    public String getStaticLabel() {
        return staticLabel;
    }

    public void setStaticLabel(String staticLabel) {
        this.staticLabel = staticLabel;
    }

    public String getStaticValue() {
        return staticValue;
    }

    public void setStaticValue(String staticValue) {
        this.staticValue = staticValue;
    }
}
