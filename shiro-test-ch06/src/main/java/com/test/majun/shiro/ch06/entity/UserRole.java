package com.test.majun.shiro.ch06.entity;

/**
 * Created by majun on 20/12/2017.
 */
public class UserRole {

    private Long userId;

    private Long rouleId;

    @Override
    public String toString() {
        return "UserRole{" +
                "rouleId=" + rouleId +
                ", userId=" + userId +
                '}';
    }

    public Long getRouleId() {
        return rouleId;
    }

    public void setRouleId(Long rouleId) {
        this.rouleId = rouleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
