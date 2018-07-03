package me.wind.groove.kafka.spring.wrapper.entity;

public class Message {

    private Long id;
    private String body;
    private Long sendTime;

    @Override
    public String toString(){
        return "{id:" + id + ", body:" + body + ", sendTime:" + sendTime + "}";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }
}
