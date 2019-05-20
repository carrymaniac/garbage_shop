<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.gdou.entity.Login" %>
<%@ page import="java.util.*" %>
<%@ page import="cn.gdou.entity.Good" %>
<jsp:useBean id="loginBean" class="cn.gdou.entity.Login" scope="session"/>
<HTML><HEAD><%@ include file="head.jsp" %></HEAD>
<BODY bgcolor=yellow><font size=2>
<div align="center">
<%  if(loginBean==null){
        response.sendRedirect("login.jsp");//重定向到登录页面
    }
    else {
       boolean b =loginBean.getLoginName()==null||
                  loginBean.getLoginName().length()==0;
       if(b)
         response.sendRedirect("login.jsp");//重定向到登录页面
    }
    LinkedList car =loginBean.getCar();
    if(car==null)
      out.print("<h2> 购物车没有物品.</h2>");
    else {
       Iterator<Good> iterator=car.iterator();
       StringBuffer buyGoods = new StringBuffer();
       int n=0;
       double priceSum =0;
       out.print("购物车中的物品：<table border=2>");
       while(iterator.hasNext()) {
           Good good=iterator.next();
           String showGoods="";
           showGoods="("+good.getMobileVersion()+","+good.getMobileName()+","+good.getMobileMade()+","+good.getMobilePic()+")";
           buyGoods.append(good.getMobileVersion()+"#");
           priceSum+=good.getMobilePrice();
           String del="<form  action='/shop?method=delete' method = 'post'>"+
                     "<input type ='hidden' name='delete' value= "+good.getId()+">"+
                     "<input type ='submit'  value='删除' ></form>";
          
           out.print("<tr><td>"+showGoods+"</td>");
           out.print("<td>"+del+"</td></tr>");
       }
       out.print("</table>");
       String orderForm = "<form action='order?method=buy' method='post'>"+
              " <input type ='hidden' name='buy' value= "+buyGoods+" >"+ 
              " <input type ='hidden' name='price' value= "+priceSum+" >"+           
              "<input type ='submit'  value='生成订单'></form>";
       out.print(orderForm); 
    } 
%>
</div></font>
</BODY></HTML>
