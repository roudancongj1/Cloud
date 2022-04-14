package com.sch.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Gao
 * @since 2022-04-13
 */
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单状态	 10：待付款	 20：已付款
     */
    private String orderStatus;

    /**
     * 订单金额
     */
    private String orderAmount;

    /**
     * 实际支付金额
     */
    private String payAmount;

    /**
     * 产品表外键ID
     */
    private String productId;

    /**
     * 产品购买的个数
     */
    private Integer buyCount;

    /**
     * 订单创建时间
     */
    private LocalDateTime createTime;

    /**
     * 支付时间
     */
    private LocalDateTime payTime;


    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
        "id=" + id +
        ", orderNo=" + orderNo +
        ", orderStatus=" + orderStatus +
        ", orderAmount=" + orderAmount +
        ", payAmount=" + payAmount +
        ", productId=" + productId +
        ", buyCount=" + buyCount +
        ", createTime=" + createTime +
        ", payTime=" + payTime +
        "}";
    }
}
