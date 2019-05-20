package cn.gdou.test;

import cn.gdou.common.PageResult;
import cn.gdou.entity.Good;
import cn.gdou.service.imp.GoodServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class GoodServiceImplTest {


    @Test
    public void add() {
        GoodServiceImpl goodService = new GoodServiceImpl();
        Good good = new Good();
        good.setMobilePic("dd.jpg");
        good.setMobilePrice(12);
        good.setMobileMade("中国");
        good.setMobileName("chuizi");
        good.setMobileVersion("10000");
        good.setMobileMess("就是da");
        goodService.add(good);
    }

    @Test
    public void delete(){
        GoodServiceImpl goodService = new GoodServiceImpl();
        goodService.delete("4");
    }

    @Test
    public void update(){
        GoodServiceImpl goodService = new GoodServiceImpl();
        Good good = new Good();
        good.setMobilePic("dd.jpg");
        good.setMobilePrice(1323);
        good.setMobileMade("中国");
        good.setMobileName("chuizi");
        good.setMobileVersion("10000");
        good.setMobileMess("就是da");
        good.setId("5");
        goodService.update(good);
    }

    @Test
    public void findOne(){
        GoodServiceImpl goodService = new GoodServiceImpl();
        Good one = goodService.findOne("5");
        System.out.println(one);
    }

    @Test
    public void findByPage(){
        GoodServiceImpl goodService = new GoodServiceImpl();
        PageResult pageResult = goodService.queryByPage(1, 2);
        List<Good> list = (List<Good>) pageResult.getList();
        list.stream().forEach(System.out::println);
        Assert.assertNotNull(list);
    }



}