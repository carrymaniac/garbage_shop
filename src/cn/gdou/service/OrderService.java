package cn.gdou.service;


import cn.gdou.common.PageResult;
import cn.gdou.entity.Order;

public interface OrderService {
    Boolean add(Order order);

    Boolean delete(String id);

    Order findOne(String id);

    Order update(String id);

    PageResult queryByPage(Integer currentPage, Integer pageSize);
}
