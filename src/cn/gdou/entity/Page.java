package cn.gdou.entity;

import java.sql.ResultSet;
import java.util.List;

public class Page {
    List Data=null;         //存储表中全部记录的行集对象
    int pageSize=1;                      //每页显示的记录数
    int totalPages=1;                     //分页后的总页数
    int currentPage =1   ;                 //当前显示页

    public void setPageSize(int size){
        pageSize=size;
    }
    public int getPageSize(){
        return pageSize;
    }
    public int getTotalPages(){
        return totalPages;
    }
    public void setTotalPages(int n){
        totalPages=n;
    }
    public void setCurrentPage(int n){
        currentPage =n;
    }
    public int getCurrentPage(){
        return currentPage ;
    }

    public List getData() {
        return Data;
    }

    public void setData(List data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "Data=" + Data +
                ", pageSize=" + pageSize +
                ", totalPages=" + totalPages +
                ", currentPage=" + currentPage +
                '}';
    }
}
