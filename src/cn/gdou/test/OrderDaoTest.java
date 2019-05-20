package cn.gdou.test;

import cn.gdou.dao.OrderDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.Order;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class OrderDaoTest {

    @Test
    public void findOneOrderTest(){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        OrderDao orderDao = new OrderDao(databaseConnection.getConnection());
        try {
            Order oneByID = orderDao.findOneByID("1");
            Assert.assertNotNull(oneByID);
            System.out.println(oneByID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
