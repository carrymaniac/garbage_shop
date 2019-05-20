package cn.gdou.service;

import cn.gdou.common.PageResult;
import cn.gdou.entity.Good;
import cn.gdou.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    PageResult queryByPages(Integer currentPage, Integer pageSize, String userName);
    void add(User user) throws SQLException;
    Boolean delete(String id);
    User findOne(String id);
    Boolean update(User user);
    Map<String, Object> Register(User user);
}
