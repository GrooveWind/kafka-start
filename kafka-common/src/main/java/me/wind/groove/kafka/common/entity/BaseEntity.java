package me.wind.groove.kafka.common.entity;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseEntity<T> implements Serializable {

    protected T id;
    protected Date createDate;
    protected Integer version;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
