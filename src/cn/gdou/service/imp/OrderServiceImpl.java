package cn.gdou.service.imp;

import cn.gdou.common.PageResult;
import cn.gdou.dao.OrderDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.Order;
import cn.gdou.service.OrderService;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    /**
     * 增加一个新的订单
     * @param order
     * @return
     */
    @Override
    public Boolean add(Order order) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        OrderDao orderDao = new OrderDao(databaseConnection.getConnection());
        try {
            boolean insert = orderDao.insert(order);
            databaseConnection.close();
            return insert;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 通过ID查询一个订单
     * @param id
     * @return
     */
    @Override
    public Order findOne(String id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        OrderDao orderDao = new OrderDao(databaseConnection.getConnection());
        try {
            Order oneByID = orderDao.findOneByID(id);
            databaseConnection.close();
           return oneByID;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Order update(String id) {
        //TODO 增加一个修改订单业务状态的方法
        return null;
    }

    @Override
    public PageResult queryByPage(Integer currentPage, Integer pageSize) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        OrderDao orderDao = new OrderDao(databaseConnection.getConnection());
        try {
            List<Order> orders = orderDao.findAllByPage((currentPage - 1) * pageSize, pageSize);
            Long conuts = orderDao.conuts();
            return new PageResult(orders, conuts, pageSize, currentPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO 增加一个删除订单业务操作
        return false;
    }
}
