package cn.gdou.servlet;

import cn.gdou.dao.UserDao;
import cn.gdou.dbc.DatabaseConnection;
import cn.gdou.entity.Login;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        UserDao userDao = new UserDao(databaseConnection.getConnection());
        String loginame=request.getParameter("loginName").trim();
        String password=request.getParameter("password").trim();
        Map<String, Object> result = null;
        try {
             result = userDao.Login(loginame, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.close();
        }
        boolean login = (boolean) result.get("result");
        String info = (String) result.get("info");
        if(login){
            success(request,response,loginame,password);
            RequestDispatcher dispatcher=
                    request.getRequestDispatcher("login.jsp");//转发
            dispatcher.forward(request,response);
        }else {
            fail(request,response,loginame,info);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }


    public void success(HttpServletRequest request,HttpServletResponse response
            ,String logname,String password) {
        Login loginBean=null;
        HttpSession session=request.getSession(true);
        try{
            loginBean=(Login)session.getAttribute("loginBean");
            if(loginBean==null){
                loginBean=new Login();  //创建新的数据模型
                session.setAttribute("loginBean",loginBean);
                loginBean=(Login)session.getAttribute("loginBean");
            }
            String name =loginBean.getLoginName();
            System.out.println("原本的"+name);
            if(name.equals(logname)) {
                loginBean.setBackNews(logname+"已经登录了");
                loginBean.setLoginName(logname);
            }
            else {  //数据模型存储新的登录用户
                System.out.println("写入loginname"+logname);
                loginBean.setBackNews(logname+"登录成功");
                loginBean.setLoginName(logname);
                System.out.println(loginBean.toString());
            }
        session.setAttribute("loginBean",loginBean);
        }
        catch(Exception ee){
//            loginBean=new Login();
////            session.setAttribute("loginBean",loginBean);
////            loginBean.setBackNews(logname+"登录成功");
////            loginBean.setLoginName(logname);
            ee.printStackTrace();
        }
    }

    public void fail(HttpServletRequest request,HttpServletResponse response
            ,String logname,String backNews) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+logname+"登录反馈结果<br>"+backNews+"</h2>") ;
            out.println("返回登录页面或主页<br>");
            out.println("<a href =login.jsp>登录页面</a>");
            out.println("<br><a href =index.jsp>主页</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
}
