package cn.gdou.service;

import cn.gdou.common.PageResult;
import cn.gdou.entity.Good;

import java.util.List;

public interface GoodService {

    PageResult queryByPage(Integer currentPage, Integer pageSize);

    void add(Good good);

    Boolean delete(String id);

    Good findOne(String id);

    Boolean update(Good good);

    List<Good> findByClassify(Integer classify);
}
