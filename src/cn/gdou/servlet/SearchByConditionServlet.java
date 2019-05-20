package cn.gdou.servlet;

import cn.gdou.dao.GoodsDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.Good;
import cn.gdou.entity.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

/**
 * GoodServlet中
 */
@WebServlet("/searchByConditionServlet")
public class SearchByConditionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        GoodsDao goodsDao = new GoodsDao(databaseConnection.getConnection());
        String searchMess= request.getParameter("searchMess");
        String radioMess= request.getParameter("radio");
        if(searchMess==null||searchMess.length()==0) {
            fail(request,response,"没有查询信息，无法查询");
            return;
        }
        List<Good> resultSet = null;
        String condition="";
        try {
            if (radioMess.equals("mobile_version")) {
                resultSet =   goodsDao.SearchByMobileVersion(searchMess);
            } else if (radioMess.equals("mobile_name")) {
                System.out.println("mobile_name_search");
                resultSet = goodsDao.getGoodsByName(searchMess);
            } else if (radioMess.equals("mobile_price")) {
                double max = 0, min = 0;
                String regex = "[^0123456789.]";
                String[] priceMess = searchMess.split(regex);
                if (priceMess.length == 1) {
                    max = min = Double.parseDouble(priceMess[0]);
                } else if (priceMess.length == 2) {
                    min = Double.parseDouble(priceMess[0]);
                    max = Double.parseDouble(priceMess[1]);
                    if (max < min) {
                        double t = max;
                        max = min;
                        min = t;
                    }
                } else {
                    fail(request, response, "输入的价格格式有错误");
                    return;
                }
                resultSet = goodsDao.SearchByPrice(max,min);
            }
        }catch (Exception e){
                e.printStackTrace();
        }finally {
            databaseConnection.close();
        }
        HttpSession session=request.getSession(true);
        Page dataBean = ServletUtil.getPage(session);
        dataBean.setData(resultSet);      //行集数据存储在dataBean中
        response.sendRedirect("byPageShow.jsp");//重定向到byPageShow.jsp
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void fail(HttpServletRequest request,HttpServletResponse response,
                     String backNews) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+backNews+"</h2>") ;
            out.println("返回：");
            out.println("<a href =searchMobile.jsp>查询手机</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }

}
