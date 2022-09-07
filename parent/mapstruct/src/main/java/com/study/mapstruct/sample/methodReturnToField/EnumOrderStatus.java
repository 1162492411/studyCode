package com.study.mapstruct.sample.methodReturnToField;

public enum EnumOrderStatus {
    UN_PAYED(1, "待支付"),
    PAYED(2, "已支付");

    EnumOrderStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    private int status;
    private String name;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}