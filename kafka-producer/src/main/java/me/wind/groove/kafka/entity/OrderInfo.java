package me.wind.groove.kafka.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderInfo implements Serializable {

    private Long id;
    private String orderId;
    private Date createTime;
    private Date modifyTime;
    private Long SendTime;
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getSendTime() {
        return SendTime;
    }

    public void setSendTime(Long sendTime) {
        SendTime = sendTime;
    }
}
