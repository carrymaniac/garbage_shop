package cn.gdou.servlet;

import cn.gdou.entity.Page;

import javax.servlet.http.HttpSession;

public class ServletUtil {
    static Page getPage(HttpSession session) {
        Page dataBean;
        try{
            dataBean=(Page)session.getAttribute("dataBean");
            if(dataBean==null){
                dataBean=new Page();  //创建Javabean对象
                session.setAttribute("dataBean",dataBean);
            }
        }catch (Exception exp){
            dataBean = new Page();
            session.setAttribute("dataBean",dataBean);
        }
        return dataBean;
    }
}
