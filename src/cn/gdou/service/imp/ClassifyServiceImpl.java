package cn.gdou.service.imp;

import cn.gdou.common.PageResult;
import cn.gdou.dao.ClassifyDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.Classify;
import cn.gdou.service.ClassifyService;

import java.sql.SQLException;
import java.util.List;

public class ClassifyServiceImpl implements ClassifyService {
    @Override
    public PageResult queryByPage(Integer currentPage, Integer pageSize) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ClassifyDao classifyDao = new ClassifyDao(databaseConnection.getConnection());
        try {
            List<Classify> classifies = classifyDao.queryAllByPage((currentPage - 1) * pageSize, pageSize);
            Long conuts = classifyDao.conuts();
            return new PageResult(classifies, conuts, pageSize, currentPage);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean add(Classify classify) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ClassifyDao classifyDao = new ClassifyDao(databaseConnection.getConnection());
        try {
            Boolean insert = classifyDao.insert(classify);
            return insert;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            databaseConnection.close();
        }
        return false;
    }

    @Override
    public Boolean delete(Integer id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ClassifyDao classifyDao = new ClassifyDao(databaseConnection.getConnection());
        try {
            Boolean delete = classifyDao.delete(id);
            return delete;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.close();
        }
        return null;
    }

    @Override
    public Classify findOne(Integer id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ClassifyDao classifyDao = new ClassifyDao(databaseConnection.getConnection());
        try {
            Classify classify = classifyDao.queryOneById(id);
            return classify;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.close();
        }
        return null;
    }

    @Override
    public Boolean update(Classify classify) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ClassifyDao classifyDao = new ClassifyDao(databaseConnection.getConnection());
        try {
            Boolean update = classifyDao.update(classify);
            return update;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.close();
        }
        return null;
    }

    @Override
    public List<Classify> queryAll() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ClassifyDao classifyDao = new ClassifyDao(databaseConnection.getConnection());
        try {
            List<Classify> classifies = classifyDao.queryAll();
            return classifies;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.close();
        }
        return null;
    }
}
