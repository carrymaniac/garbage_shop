package cn.gdou.dao;

import cn.gdou.entity.Good;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {
    private Connection conn;
    private PreparedStatement pstmt;
    public GoodsDao(Connection conn){
        this.conn = conn;
    }
    public boolean insert(Good good) throws SQLException {
        String sql = "INSERT INTO mobileForm(mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify) VALUES (?,?,?,?,?,?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,good.getMobileVersion());
        this.pstmt.setString(2,good.getMobileName());
        this.pstmt.setString(3,good.getMobileMade());
        this.pstmt.setInt(4, good.getMobilePrice());
        this.pstmt.setString(5,good.getMobileMess());
        this.pstmt.setString(6,good.getMobilePic());
        this.pstmt.setInt(7,good.getMobileClassify());
        return pstmt.executeUpdate()>0;
    }
    public boolean delete(String id) throws SQLException{
        String sql = "DELETE FROM mobileForm where id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,id);
        return pstmt.execute();
    }
    public boolean update(Good good) throws SQLException{
        String sql = "UPDATE mobileForm set mobile_version=?,mobile_name=?,mMobile_made=?,mobile_price=?,mobile_mess=?,mobile_pic=?,mobile_classify=? where id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,good.getMobileVersion());
        this.pstmt.setString(2,good.getMobileName());
        this.pstmt.setString(3,good.getMobileMade());
        this.pstmt.setInt(4, good.getMobilePrice());
        this.pstmt.setString(5,good.getMobileMess());
        this.pstmt.setString(6,good.getMobilePic());
        this.pstmt.setInt(7,good.getMobileClassify());
        this.pstmt.setString(8,good.getId());
        int i = pstmt.executeUpdate();
        System.out.println(i);
        return i>0;
    }
    public List<Good> queryAllByPage(Integer a, Integer b) throws SQLException{
        List<Good> goods = new ArrayList<Good>();
        String sql = "SELECT mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify,id FROM mobileForm limit ?,?";
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            pstmt.setInt(1, a);
            pstmt.setInt(2, b);
            ResultSet rs = this.pstmt.executeQuery();
            while(rs.next()){
                Good good = new Good();
                good.setMobileVersion(rs.getString(1));
                good.setMobileName(rs.getString(2));
                good.setMobileMade(rs.getString(3));
                good.setMobilePrice(rs.getInt(4));
                good.setMobileMess(rs.getString(5));
                good.setMobilePic(rs.getString(6));
                good.setMobileClassify(rs.getInt(7));
                good.setId(rs.getString(8));
                goods.add(good);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return goods;
    }
    public Good queryOneById(String id) throws SQLException{
        String sql = "SELECT mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify,id FROM mobileForm where id = ? limit 1";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1,id);
        ResultSet rs = this.pstmt.executeQuery();
        Good good = new Good();
        if (rs.next()){
            good.setMobileVersion(rs.getString(1));
            good.setMobileName(rs.getString(2));
            good.setMobileMade(rs.getString(3));
            good.setMobilePrice(rs.getInt(4));
            good.setMobileMess(rs.getString(5));
            good.setMobilePic(rs.getString(6));
            good.setMobileClassify(rs.getInt(7));
            good.setId(rs.getString(8));
        }
        return good;
    }
    public List<Good> getGoodsByClassify(Integer id) throws SQLException {
        String sql ="SELECT mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify,id FROM mobileForm where mobile_classify = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = this.pstmt.executeQuery();
        List<Good> goods = new ArrayList<Good>();
        while(rs.next()){
            Good good = new Good();
            good.setMobileVersion(rs.getString(1));
            good.setMobileName(rs.getString(2));
            good.setMobileMade(rs.getString(3));
            good.setMobilePrice(rs.getInt(4));
            good.setMobileMess(rs.getString(5));
            good.setMobilePic(rs.getString(6));
            good.setMobileClassify(rs.getInt(7));
            good.setId(rs.getString(8));
            goods.add(good);
        }
        return goods;
    }
    public Good getGoodByVersion(String mobileVersion) throws SQLException{
        String sql = "SELECT mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify,id FROM mobileForm where mobile_version = ? limit 1";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1,mobileVersion);
        ResultSet rs = this.pstmt.executeQuery();
        Good good = new Good();
        if (rs.next()){
            good.setMobileVersion(rs.getString(1));
            good.setMobileName(rs.getString(2));
            good.setMobileMade(rs.getString(3));
            good.setMobilePrice(rs.getInt(4));
            good.setMobileMess(rs.getString(5));
            good.setMobilePic(rs.getString(6));
            good.setMobileClassify(rs.getInt(7));
            good.setId(rs.getString(8));
        }
        return good;
    }
    public List getGoodsByName(String mobileName) throws SQLException {
        String sql ="SELECT mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify,id FROM mobileForm where mobile_name LIKE ?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1,"%"+mobileName+"%");
        ResultSet rs = this.pstmt.executeQuery();
        List<Good> goods = new ArrayList<Good>();
        while(rs.next()){
            Good good = new Good();
            good.setMobileVersion(rs.getString(1));
            good.setMobileName(rs.getString(2));
            good.setMobileMade(rs.getString(3));
            good.setMobilePrice(rs.getInt(4));
            good.setMobileMess(rs.getString(5));
            good.setMobilePic(rs.getString(6));
            good.setMobileClassify(rs.getInt(7));
            good.setId(rs.getString(8));
            goods.add(good);
        }
        return goods;
    }
    public List SearchByPrice(Double a,Double b) throws SQLException {
        String sql ="SELECT mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify,id FROM mobileForm where mobile_price <= ? AND mobile_price>= ?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setDouble(1,a);
        pstmt.setDouble(2,b);
        ResultSet rs = this.pstmt.executeQuery();
        List<Good> goods = new ArrayList<Good>();
        while(rs.next()){
            Good good = new Good();
            good.setMobileVersion(rs.getString(1));
            good.setMobileName(rs.getString(2));
            good.setMobileMade(rs.getString(3));
            good.setMobilePrice(rs.getInt(4));
            good.setMobileMess(rs.getString(5));
            good.setMobilePic(rs.getString(6));
            good.setMobileClassify(rs.getInt(7));
            good.setId(rs.getString(8));
            goods.add(good);
        }
        System.out.println(goods.toString());
        return goods;
    }
    public List SearchByMobileVersion(String mobile_version) throws SQLException {
        String sql = "SELECT mobile_version,mobile_name,mMobile_made,mobile_price,mobile_mess,mobile_pic,mobile_classify,id FROM mobileForm where mobile_version = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setString(1,mobile_version);
        ResultSet rs = this.pstmt.executeQuery();
        List<Good> goods = new ArrayList<Good>();
        while(rs.next()){
            Good good = new Good();
            good.setMobileVersion(rs.getString(1));
            good.setMobileName(rs.getString(2));
            good.setMobileMade(rs.getString(3));
            good.setMobilePrice(rs.getInt(4));
            good.setMobileMess(rs.getString(5));
            good.setMobilePic(rs.getString(6));
            good.setMobileClassify(rs.getInt(7));
            good.setId(rs.getString(8));
            goods.add(good);
        }
        return goods;
    }
    public Long conuts(){
        String sql = "select count(*) from mobileForm";
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
