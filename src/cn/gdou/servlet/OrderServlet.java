package cn.gdou.servlet;

import cn.gdou.common.PageResult;
import cn.gdou.entity.Good;
import cn.gdou.entity.Login;
import cn.gdou.entity.Order;
import cn.gdou.service.GoodService;
import cn.gdou.service.OrderService;
import cn.gdou.service.imp.GoodServiceImpl;
import cn.gdou.service.imp.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        if (method == null && method == "") {
            request.getRequestDispatcher("/WEB-INF/pages/welcome.jsp").forward(request, response);
            return;
        }
        switch (method) {
            case "buy":
                buyGoods(request, response);
                break;
            case "list":
                list(request, response);
        }
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");
        //默认的页面大小
        Integer pageSizeInt = 5;
        //默认的当前页面
        Integer currentPageInt = 1;
        if(pageSize!=null&&pageSize.length()>0) {
            pageSizeInt = Integer.parseInt(pageSize);
        }
        if(currentPage!=null&&currentPage.length()>0) {
            currentPageInt = Integer.parseInt(currentPage);
        }
        OrderService orderService = new OrderServiceImpl();
        PageResult pageResult = orderService.queryByPage(currentPageInt, pageSizeInt);
        request.setAttribute("pageResult", pageResult);
        request.getRequestDispatcher("/WEB-INF/pages/order/list.jsp").forward(request, response);
    }

    private void buyGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String buyGoodsMess = request.getParameter("buy");
        String price = request.getParameter("price");
        if(buyGoodsMess==null||buyGoodsMess.length()==0) {
            fail(request,response,"购物车没有物品，无法生成订单");
            return;
        }
        if(price==null||price.length()==0) {
            fail(request,response,"没有计算价格和，无法生成订单");
            return;
        }
        float sum = Float.parseFloat(price);

        //检测是否已经登录
        Login loginBean=null;
        HttpSession session=request.getSession(true);
        try{  loginBean=(Login)session.getAttribute("loginBean");
            boolean b =loginBean.getLoginName()==null||
                    loginBean.getLoginName().length()==0;
            if(b)
                response.sendRedirect("login.jsp");//重定向到登录页面
            StringBuffer stringBuffer = new StringBuffer();
            getOrderFromCar(loginBean, stringBuffer);
            Order order = new Order(loginBean.getLoginName(),stringBuffer.toString(),sum);
            OrderService orderService = new OrderServiceImpl();
            if(orderService.add(order)){
                //购买成功了,清空购物车
                loginBean.getCar().clear();
                success(request,response,"生成订单成功");
            }

        } catch(Exception exp){
            exp.printStackTrace();
            response.sendRedirect("login.jsp");//重定向到登录页面
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public void success(HttpServletRequest request,HttpServletResponse response,
                        String backNews) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+backNews+"</h2>") ;
            out.println("返回主页<br>");
            out.println("<br><a href =index.jsp>主页</a>");
            out.println("查看订单<br>");
            out.println("<br><a href =lookOrderForm.jsp>查看订单</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
    public void fail(HttpServletRequest request,HttpServletResponse response,
                     String backNews) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out=response.getWriter();
            out.println("<html><body>");
            out.println("<h2>"+backNews+"</h2>") ;
            out.println("返回主页：");
            out.println("<a href =index.jsp>主页</a>");
            out.println("</body></html>");
        }
        catch(IOException exp){}
    }
    static void getOrderFromCar(Login loginBean, StringBuffer stringBuffer) {
        for (int i = 0;i<loginBean.getCar().size();i++){
            Good good = loginBean.getCar().get(i);
            stringBuffer.append(i+":(");
            stringBuffer.append(good.getMobileVersion()+",");
            stringBuffer.append(good.getMobileName()+",");
            stringBuffer.append(good.getMobileMade()+",");
            stringBuffer.append(good.getMobilePrice()+")");
        }
    }
}
