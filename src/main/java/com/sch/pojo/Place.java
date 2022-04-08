package com.sch.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Gao
 * @since 2022-01-16
 */
@TableName("place")
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "place_id", type = IdType.AUTO)
    private Integer placeId;

    private Integer parentId;

    private String placeName;

    private Integer placeMark;

    private String placeAddress;

    private String placeEat;

    private Integer placeWifi;

    private BigDecimal placeMoney;

    private String placeImg;

    private String placeInfo;

    private String chargePhone;

    private LocalDate addDate;

    private LocalDate updateDate;


    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Integer getPlaceMark() {
        return placeMark;
    }

    public void setPlaceMark(Integer palceMark) {
        this.placeMark = palceMark;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getPlaceEat() {
        return placeEat;
    }

    public void setPlaceEat(String placeEat) {
        this.placeEat = placeEat;
    }

    public Integer getPlaceWifi() {
        return placeWifi;
    }

    public void setPlaceWifi(Integer placeWifi) {
        this.placeWifi = placeWifi;
    }

    public BigDecimal getPlaceMoney() {
        return placeMoney;
    }

    public void setPlaceMoney(BigDecimal placeMoney) {
        this.placeMoney = placeMoney;
    }

    public String getPlaceImg() {
        return placeImg;
    }

    public void setPlaceImg(String placeImg) {
        this.placeImg = placeImg;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }

    public String getChargePhone() {
        return chargePhone;
    }

    public void setChargePhone(String chargePhone) {
        this.chargePhone = chargePhone;
    }

    public LocalDate getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDate addDate) {
        this.addDate = addDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeId=" + placeId +
                ", parentId=" + parentId +
                ", placeName='" + placeName + '\'' +
                ", placeMark=" + placeMark +
                ", placeAddress='" + placeAddress + '\'' +
                ", placeEat='" + placeEat + '\'' +
                ", placeWifi=" + placeWifi +
                ", placeMoney=" + placeMoney +
                ", placeImg='" + placeImg + '\'' +
                ", placeInfo='" + placeInfo + '\'' +
                ", chargePhone='" + chargePhone + '\'' +
                ", addDate=" + addDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
