package cn.gdou.dao;

import cn.gdou.entity.Classify;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ClassifyDao {
    private Connection conn;
    private PreparedStatement pstmt;

    public ClassifyDao(Connection conn) {
        this.conn = conn;
    }
    public Boolean insert(Classify classify) throws SQLException {
        String sql = "INSERT INTO mobileclassify(id,name) VALUES (?,?)";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1, classify.getClassifyId());
        this.pstmt.setString(2,classify.getName());
        return pstmt.executeUpdate()>0;
    }
    public Boolean delete(Integer id) throws SQLException {
        String sql = "DELETE FROM mobileclassify where id = ?";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setInt(1,id);
        return pstmt.execute();
    }
    public Classify queryOneById(Integer id) throws SQLException {
        String sql = "SELECT id,name from mobileclassify where id = ? limit 1";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        ResultSet rs = this.pstmt.executeQuery();
        Classify classify = new Classify();
        while (rs.next()){
            classify.setClassifyId(rs.getInt(1));
            classify.setName(rs.getString(2));
        }
        return classify;
    }
    public Boolean update(Classify classify)throws SQLException {
        String sql = "UPDATE  mobileclassify SET name= ? where id = ? ";
        this.pstmt = this.conn.prepareStatement(sql);
        this.pstmt.setString(1,classify.getName());
        this.pstmt.setInt(2,classify.getClassifyId());
        int i = pstmt.executeUpdate();
        return i>0;
    };
    public List<Classify> queryAllByPage(Integer a, Integer b) throws SQLException{
        String sql = "SELECT id,name from mobileclassify limit ?,?";
        this.pstmt = this.conn.prepareStatement(sql);
        pstmt.setInt(1, a);
        pstmt.setInt(2, b);
        ResultSet rs = this.pstmt.executeQuery();
        List<Classify> classifies = new LinkedList<>();
        while(rs.next()){
            Classify classify = new Classify();
            classify.setClassifyId(rs.getInt(1));
            classify.setName(rs.getString(2));
            classifies.add(classify);
        }
        return classifies;
    }
    public Long conuts(){
        String sql = "select count(*) from mobileclassify";
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
    public List<Classify> queryAll() throws SQLException{
        String sql = "SELECT id,name from mobileclassify ";
        this.pstmt = this.conn.prepareStatement(sql);
        ResultSet rs = this.pstmt.executeQuery();
        List<Classify> classifies = new LinkedList<>();
        while(rs.next()){
            Classify classify = new Classify();
            classify.setClassifyId(rs.getInt(1));
            classify.setName(rs.getString(2));
            classifies.add(classify);
        }
        return classifies;
    }
}
