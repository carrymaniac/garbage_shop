package cn.gdou.service.imp;

import cn.gdou.common.PageResult;
import cn.gdou.dao.UserDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.User;
import cn.gdou.service.UserService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    @Override
    public PageResult queryByPages(Integer currentPage, Integer pageSize, String userName) {
        PageResult pageResult = null;
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        List<User> users = userDao.queryAllByPage((currentPage-1)*pageSize,pageSize);
        Long conuts = userDao.conuts();
        pageResult = new PageResult(users, conuts, pageSize, currentPage);
        return pageResult;
    }

    @Override
    public void add(User user){
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        try {
            userDao.insert(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean delete(String id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        try {
            if(userDao.findById(id)!=null) {
                return userDao.delete(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findOne(String id) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        try {
            return userDao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(User user) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        try {
            return userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Map<String, Object> Register(User user) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        Map<String, Object> infoMap = new HashMap<String, Object>();
        System.out.println(user.getLoginName());
        try {
            if (userDao.findUserByLoginName(user.getLoginName())) {
                infoMap.put("result", false);
                infoMap.put("info", "该会员名已被使用，请您更换名字");
                return infoMap;
            }
            Boolean insert = userDao.insert(user);
            if (insert) {
                infoMap.put("result", true);
                infoMap.put("info", "注册成功");

            } else {
                infoMap.put("result", false);
                infoMap.put("info", "注册失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return infoMap;
    }
}
