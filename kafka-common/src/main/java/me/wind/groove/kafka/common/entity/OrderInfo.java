package me.wind.groove.kafka.common.entity;

public class OrderInfo extends BaseEntity<Long> {

    private String orderId;
    private Long sendTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }
}
