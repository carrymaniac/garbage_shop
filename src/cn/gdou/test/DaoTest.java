package cn.gdou.test;

import cn.gdou.dao.UserDao;
import cn.gdou.dbc.DatabaseConnection;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Map;


public class DaoTest {
    @Test
    public void UserDaoTest(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        try {
            Map<String, Object> wzb = userDao.Login("wzb", "123456");
            System.out.println(wzb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
