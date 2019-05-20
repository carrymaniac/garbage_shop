package cn.gdou.dao;

import cn.gdou.common.PageResult;
import cn.gdou.entity.Page;
import cn.gdou.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {
    private Connection conn;
    private PreparedStatement pstmt;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public Map<String, Object> Login(String loginame, String password) throws SQLException {
        String sql = "SELECT * from user where loginName = ? and password=?";
        Map<String, Object> infoMap = new HashMap<String, Object>();
        if ("".equals(loginame) || "".equals(password)) {
            infoMap.put("result", false);
            infoMap.put("info", "请输入用户名和密码");
            return infoMap;
        }
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, loginame);
        this.pstmt.setString(2, password);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            infoMap.put("result", true);
            infoMap.put("info", "登录成功");
        } else {
            infoMap.put("result", false);
            infoMap.put("info", "您输入的用户名不存在，或密码不般配");
        }
        return infoMap;
    }

    public boolean findUserByLoginName(String loginame) throws SQLException {
        String sql = "SELECT * from user where loginName = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, loginame);
        ResultSet rs = this.pstmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> queryAll() {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT loginName,phone,addess,realname from user ";
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            ResultSet rs = this.pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                String loginName = rs.getString(1);
                String phone = rs.getString(2);
                String address = rs.getString(3);
                String realname = rs.getString(4);
                user.setRealname(realname);
                user.setLoginName(loginName);
                user.setPhone(phone);
                user.setAddress(address);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<User> queryAllByPage(Integer a, Integer b) {
        List<User> users = new ArrayList<User>();
        String sql = "SELECT loginName,phone,addess,realname from user limit ?,?";
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, a);
            pstmt.setInt(2, b);
            ResultSet rs = this.pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                String loginName = rs.getString(1);
                String phone = rs.getString(2);
                String address = rs.getString(3);
                String realname = rs.getString(4);
                user.setRealname(realname);
                user.setLoginName(loginName);
                user.setPhone(phone);
                user.setAddress(address);
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public Long conuts() {
        String sql = "select count(*) from user ";
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

    public User findById(String id) throws SQLException {
        String sql = "SELECT loginName,phone,addess,realname,user_id from user where loginName = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, id);
        ResultSet rs = this.pstmt.executeQuery();
        User user = null;
        while(rs.next()){
            user = new User();
           String loginName = rs.getString(1);
           String phone = rs.getString(2);
           String address = rs.getString(3);
           String realname = rs.getString(4);
           String user_id = rs.getString(5);
           user.setRealname(realname);
           user.setLoginName(loginName);
           user.setPhone(phone);
           user.setAddress(address);
           user.setId(user_id);
       }
       return user;
    }

    public Boolean insert(User user) throws SQLException{
        String sql = "INSERT INTO user(loginName,password,phone,addess,realname) VALUES (?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getLoginName());
        this.pstmt.setString(2, user.getPassword());
        this.pstmt.setString(3, user.getPhone());
        this.pstmt.setString(4, user.getAddress());
        this.pstmt.setString(5, user.getRealname());
        return this.pstmt.executeUpdate() > 0;
    }

    public Boolean delete(String id) throws SQLException{
        String sql = "DELETE FROM user where user_id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,id);
        return pstmt.execute();
    }

    public Boolean update(User user) throws SQLException{
        String sql = "UPDATE user set loginName=?,phone=?,address=?,realname=? where user_id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1, user.getLoginName());
        this.pstmt.setString(2, user.getPhone());
        this.pstmt.setString(3, user.getAddress());
        this.pstmt.setString(4, user.getRealname());
        this.pstmt.setString(5, user.getId());
        return this.pstmt.executeUpdate()>0;
    }

}
