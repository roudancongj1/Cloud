package com.sch.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 *
 *
 * @author Gao
 * @since 2022-01-16
 */
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "city_id", type = IdType.AUTO)
    private Integer cityId;

    private Integer cityLv;

    private String cityName;

    private Integer childMark;

    private String cityInfo;

    /**
     * 1=低 2=中 3 =高
     */
    private Integer cityRisk;

    private Integer cityDays;


    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCityLv() {
        return cityLv;
    }

    public void setCityLv(Integer cityLv) {
        this.cityLv = cityLv;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getChildMark() {
        return childMark;
    }

    public void setChildMark(Integer childMark) {
        this.childMark = childMark;
    }

    public String getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(String cityInfo) {
        this.cityInfo = cityInfo;
    }

    public Integer getCityRisk() {
        return cityRisk;
    }

    public void setCityRisk(Integer cityRisk) {
        this.cityRisk = cityRisk;
    }

    public Integer getCityDays() {
        return cityDays;
    }

    public void setCityDays(Integer cityDays) {
        this.cityDays = cityDays;
    }

    @Override
    public String toString() {
        return "City{" +
        "cityId=" + cityId +
        ", cityLv=" + cityLv +
        ", cityName=" + cityName +
        ", childMark=" + childMark +
        ", cityInfo=" + cityInfo +
        ", cityRisk=" + cityRisk +
        ", cityDays=" + cityDays +
        "}";
    }
}
