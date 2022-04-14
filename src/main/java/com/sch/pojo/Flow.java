package com.sch.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 流水表
 * </p>
 *
 * @author Gao
 * @since 2022-04-13
 */
public class Flow implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 流水号
     */
    private String flowNo;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 产品主键ID
     */
    private String productId;

    /**
     * 支付金额
     */
    private String payAmount;

    /**
     * 支付方式	 1：支付宝	 2：微信
     */
    private Integer payType;

    /**
     * 购买个数
     */
    private Integer buyCount;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(String flowNo) {
        this.flowNo = flowNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
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

    @Override
    public String toString() {
        return "Flow{" +
        "id=" + id +
        ", flowNo=" + flowNo +
        ", orderNo=" + orderNo +
        ", productId=" + productId +
        ", payAmount=" + payAmount +
        ", payType=" + payType +
        ", buyCount=" + buyCount +
        ", createTime=" + createTime +
        "}";
    }
}
