package cn.gdou.servlet;

import cn.gdou.dao.GoodsDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.Good;
import cn.gdou.entity.Login;
import cn.gdou.service.GoodService;
import cn.gdou.service.imp.GoodServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.LinkedList;

@WebServlet("/shop")
public class shopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null && method == "") {
            request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
            return;
        }
        switch (method) {
            case "delete":
                delete(request,response);
                break;
            case "exit":
                exit(request,response);
                break;
            case "put":
                put(request,response);
                break;
        }
    }

    private void put(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        String GoodId = request.getParameter("good");

        //一模一样的登录用户获取Session再获取购物车操作
        Login loginBean=null;
        HttpSession session=request.getSession(true);
        loginBean=(Login)session.getAttribute("loginBean");
        boolean b =loginBean.getLoginName()==null||
                loginBean.getLoginName().length()==0;
        System.out.println(b);
        if(b)
            response.sendRedirect("login.jsp");//重定向到登录页面
        LinkedList<Good> car = loginBean.getCar();
        GoodService goodService = new GoodServiceImpl();
        Good good = goodService.findOne(GoodId);
        car.add(good);
        //TODO 这里有可能,购物车不变 原因是不确定对象的集合内容发生改变会不会导致对象中的发生改变
        System.out.println(loginBean);
        speakSomeMess(request,response,good.getMobileName());
    }

    private void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession(true);
        session.invalidate();              //销毁用户的session对象
        response.sendRedirect("index.jsp"); //返回主页
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String delete = request.getParameter("delete");
        GoodService goodService = new GoodServiceImpl();
        Good good = goodService.findOne(delete);
        Login loginBean=null;
        HttpSession session=request.getSession(true);
        try{  loginBean=(Login)session.getAttribute("loginBean");
            boolean b =loginBean.getLoginName()==null||
                    loginBean.getLoginName().length()==0;
            if(b)
                response.sendRedirect("login.jsp");//重定向到登录页面
            LinkedList<Good> car = loginBean.getCar();
            car.remove(good);
        }
        catch(Exception exp){
            response.sendRedirect("login.jsp");//重定向到登录页面
        }
        RequestDispatcher dispatcher=
                request.getRequestDispatcher("lookShoppingCar.jsp");
        dispatcher.forward(request, response);//转发
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    public void speakSomeMess(HttpServletRequest request, HttpServletResponse response,String goods) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out=response.getWriter();
            out.print("<HEAD><%@ include file=\"head.jsp\" %></HEAD>");
            out.println("<html><body>");
            out.println("<h2>"+goods+"放入购物车</h2>") ;
            out.println("查看购物车或返回<br>");
            out.println("<a href =lookShoppingCar.jsp>查看购物车</a>");
            out.println("<br><a href =byPageShow.jsp>主页</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
