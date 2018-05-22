package com.minxing.client.model;

public class TaskBadge {
    private String sign; //标识,展示图标使用,由三方系统传递
    private int badge; //未读数量

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public int getBadge() {
        return badge;
    }

    public void setBadge(int badge) {
        this.badge = badge;
    }
}
