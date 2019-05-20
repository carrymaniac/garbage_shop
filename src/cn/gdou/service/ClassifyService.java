package cn.gdou.service;

import cn.gdou.common.PageResult;
import cn.gdou.entity.Classify;
import cn.gdou.entity.Good;

import java.util.List;

public interface ClassifyService {
    PageResult queryByPage(Integer currentPage, Integer pageSize);

    Boolean add(Classify classify);

    Boolean delete(Integer id);

    Classify findOne(Integer id);

    Boolean update(Classify classify);

    List<Classify>  queryAll();
}
