package cn.gdou.dao;

import cn.gdou.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection conn;
    private PreparedStatement pstmt;
    //TODO 把DAO的操作补充完
    public OrderDao(Connection conn) {
        this.conn = conn;
    }
    public boolean insert(Order order) throws SQLException {
        String strName = order.getLoginName();
        String goodMess = order.getMess();
        Float num = order.getSum();
        String sql = "INSERT INTO orderform(loginName,mess,sum) VALUES (?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,strName);
        this.pstmt.setString(2,goodMess);
        this.pstmt.setFloat(3, num);
        return pstmt.executeUpdate()>0;
    };
    public Order findOneByID(String id) throws SQLException {
        String sql = "select loginName,mess,sum,id from orderform where id = ? limit 1";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,id);
        ResultSet resultSet = pstmt.executeQuery();
        if(resultSet.next()){
            String loginName = resultSet.getString(1);
            String mess = resultSet.getString(2);
            Float sum = resultSet.getFloat(3);
            String idFromDataBase = resultSet.getString(4);
            Order order = new Order(loginName,mess,sum);
            order.setOrderID(idFromDataBase);
            return order;
        }else {
            return null;
        }
    }
    //TODO 日后进行修改
    public boolean delete(String id) throws SQLException{
        boolean b = false;
        return b;
    }
    //TODO 日后加上订单审核再加上这个功能 需要结合订单审核,订单修改等需求进行开发
    public boolean update(Order order) throws SQLException{

        return false;
    }
    public List<Order> findAllByPage(Integer a, Integer b) throws SQLException {
        List<Order> orders = new ArrayList<Order>();
        String sql = "select loginName,mess,sum,id from orderform limit ?,?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, a);
        pstmt.setInt(2, b);
        ResultSet resultSet = pstmt.executeQuery();
        while (resultSet.next()) {
            String loginName = resultSet.getString(1);
            String mess = resultSet.getString(2);
            Float sum = resultSet.getFloat(3);
            String idFromDataBase = resultSet.getString(4);
            Order order = new Order(loginName, mess, sum);
            order.setOrderID(idFromDataBase);
            orders.add(order);
        }
        return orders;
    }
    public Long conuts(){
        String sql = "select count(*) from orderform";
        Long totalRecord = 0L;
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = this.pstmt.executeQuery();
            if (rs.next()) {
                totalRecord = rs.getLong(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalRecord;
    }
}
