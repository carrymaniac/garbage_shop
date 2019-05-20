package cn.gdou.entity;

import lombok.Data;


public class Register {
    String loginName ="" ;
    String phone="";
    String  address="";
    String realname="";
    String backNews="请输入信息";

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getBackNews() {
        return backNews;
    }

    public void setBackNews(String backNews) {
        this.backNews = backNews;
    }

    @Override
    public String toString() {
        return "Register{" +
                "loginName='" + loginName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", realname='" + realname + '\'' +
                ", backNews='" + backNews + '\'' +
                '}';
    }
}
