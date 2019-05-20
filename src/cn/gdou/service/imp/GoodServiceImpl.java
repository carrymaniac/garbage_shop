package cn.gdou.service.imp;

import cn.gdou.common.PageResult;
import cn.gdou.dao.GoodsDao;
import cn.gdou.dao.UserDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.Good;
import cn.gdou.entity.User;
import cn.gdou.service.GoodService;

import java.sql.SQLException;
import java.util.List;

public class GoodServiceImpl implements GoodService {
    @Override
    public PageResult queryByPage(Integer currentPage, Integer pageSize) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GoodsDao goodsDao = new GoodsDao(databaseConnection.getConnection());
        try {
            List<Good> goods = goodsDao.queryAllByPage((currentPage - 1) * pageSize, pageSize);
            Long conuts = goodsDao.conuts();
            return new PageResult(goods, conuts, pageSize, currentPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Good good) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GoodsDao goodsDao = new GoodsDao(databaseConnection.getConnection());
        try {
            goodsDao.insert(good);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(String id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GoodsDao goodsDao = new GoodsDao(databaseConnection.getConnection());
        try {
            return goodsDao.delete(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Good findOne(String id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GoodsDao goodsDao = new GoodsDao(databaseConnection.getConnection());
        try {
            Good good = goodsDao.queryOneById(id);
            return good;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(Good good) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GoodsDao goodsDao = new GoodsDao(databaseConnection.getConnection());
        try {
            goodsDao.update(good);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Good> findByClassify(Integer classify) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GoodsDao goodsDao = new GoodsDao(databaseConnection.getConnection());
        try {
            List<Good> goodsByClassify = goodsDao.getGoodsByClassify(classify);
            return goodsByClassify;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.close();
        }
        return null;
    }


}
