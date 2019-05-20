package cn.gdou.entity;


import lombok.Data;

public class Order {
    String orderID;
    String loginName;
    String mess;
    Float sum;

    public Order() {
    }

    public Order(String loginName, String mess, Float sum) {
        this.loginName = loginName;
        this.mess = mess;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", loginName='" + loginName + '\'' +
                ", mess='" + mess + '\'' +
                ", sum=" + sum +
                '}';
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }
}
