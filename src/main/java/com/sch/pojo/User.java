package com.sch.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 *
 *
 * @author Gao
 * @since 2022-01-16
 */
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userNumber;

    private String userPass;

    private String userName;

    private Integer userSex;

    private String userPhone;

    private String userTrip;

    private Integer userRole;

    private Integer version;

    private Integer logicDelete;

    private LocalDateTime addDate;

    private LocalDateTime updateDate;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserTrip() {
        return userTrip;
    }

    public void setUserTrip(String userTrip) {
        this.userTrip = userTrip;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getLogicDelete() {
        return logicDelete;
    }

    public void setLogicDelete(Integer logicDelete) {
        this.logicDelete = logicDelete;
    }

    public LocalDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(LocalDateTime addDate) {
        this.addDate = addDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userNumber=" + userNumber +
                ", userPass=" + userPass +
                ", userName=" + userName +
                ", userSex=" + userSex +
                ", userPhone=" + userPhone +
                ", userTrip=" + userTrip +
                ", userRole=" + userRole +
                ", version=" + version +
                ", logicDelete=" + logicDelete +
                ", addDate=" + addDate +
                ", updateDate=" + updateDate +
                "}";
    }

}
