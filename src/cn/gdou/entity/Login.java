package cn.gdou.entity;

import lombok.Data;

import java.util.LinkedList;

public class Login {
    String loginName ="";
    String backNews="未登录";
    LinkedList<Good> car = new LinkedList<Good>(); //用户的购物车

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getBackNews() {
        return backNews;
    }

    public void setBackNews(String backNews) {
        this.backNews = backNews;
    }

    public LinkedList<Good> getCar() {
        return car;
    }

    public void setCar(LinkedList<Good> car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Login{" +
                "loginName='" + loginName + '\'' +
                ", backNews='" + backNews + '\'' +
                ", car=" + car +
                '}';
    }
}
