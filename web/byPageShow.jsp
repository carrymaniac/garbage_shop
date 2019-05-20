<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="cn.gdou.entity.Page" %>
<%@ page import="com.sun.rowset.*" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="cn.gdou.entity.Good" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="dataBean" class="cn.gdou.entity.Page" scope="session"/>
<%@ include file="head.jsp" %></HEAD>
<HTML><Body bgcolor=#66FFAA><center>
<BR>当前显示的内容是：
  <table border=2>
  <tr>
    <th>手机标识号</th>
    <th>手机名称</th>
    <th>手机制造商</th>
    <th>手机价格</th>
    <th>查看详情</th>
    <td><font color=blue>添加到购物车</font></td>
  </tr>
<jsp:setProperty name="dataBean" property="pageSize" param="pageSize"/>
<jsp:setProperty name="dataBean" property="currentPage" param="currentPage"/>
<%
    System.out.println(dataBean);
    List<Good> goods = dataBean.getData();

      if(goods==null) {
         out.print("没有查询到结果，无法浏览");
         return;  
      }
      int totalRecord=goods.size();
      out.println("全部记录数"+totalRecord); //全部记录数
      int pageSize=dataBean.getPageSize();  //每页显示的记录数
      int totalPages = dataBean.getTotalPages();
      if(totalRecord%pageSize==0)
           totalPages = totalRecord/pageSize;//总页数
      else
           totalPages = totalRecord/pageSize+1;
      dataBean.setPageSize(pageSize);
      dataBean.setTotalPages(totalPages);



      if(totalPages>=1) {
         if(dataBean.getCurrentPage()<1)
              dataBean.setCurrentPage(dataBean.getTotalPages());
         if(dataBean.getCurrentPage()>dataBean.getTotalPages())
              dataBean.setCurrentPage(1);
         int index=(dataBean.getCurrentPage()-1)*pageSize;
          System.out.println(index);
         for(int i=0;i<=pageSize-1&&index<goods.size();i++,index++) {
            Good good = goods.get(index);
            String id = good.getId();
            String number=good.getMobileVersion();
            String name=good.getMobileName();
            String maker=good.getMobileMade();
            String price=good.getMobilePrice().toString();
            String goodd =
            "("+number+","+name+","+maker+
             ","+price+")#"+price;//便于购物车计算价格,尾缀上"#价格值"
            goodd = goodd.replaceAll("\\p{Blank}","");
            String button="<form  action='/shop?method=put' method = 'post'>"+
//                    修改了一段ID获取
                     "<input type ='hidden' name='good' value= "+good.getId()+">"+
                     "<input type ='submit'  value='放入购物车' ></form>";
//            TODO 修改为通过ID进行修改
            String detail="<form  action='showDetail.jsp' method = 'post'>"+
                     "<input type ='hidden' name='xijie' value= "+number+">"+
                     "<input type ='submit'  value='查看细节' ></form>";
            out.print("<tr>");
            out.print("<td>"+number+"</td>");
            out.print("<td>"+name+"</td>");
            out.print("<td>"+maker+"</td>");
            out.print("<td>"+price+"</td>");
            out.print("<td>"+detail+"</td>");
            out.print("<td>"+button+"</td>");
            out.print("</tr>");
         }
     }


%>
 </table>
 <BR>每页最多显示<jsp:getProperty name="dataBean" property="pageSize"/>条信息
 <BR>当前显示第<Font color=blue>
     <jsp:getProperty name="dataBean" property="currentPage"/>
   </Font>页,共有
   <Font color=blue><jsp:getProperty name="dataBean" property="totalPages"/>
   </Font>页。
<Table>
  <tr><td><FORM action="" method=post>
          <Input type=hidden name="currentPage" value=
         "<%=dataBean.getCurrentPage()-1 %>">
           <Input type=submit name="g" value="上一页"></FORM></td>
      <td><FORM action="" method=post>
          <Input type=hidden name="currentPage"
           value="<%=dataBean.getCurrentPage()+1 %>">
          <Input type=submit name="g" value="下一页"></FORM></td></tr>
 <tr><td> <FORM action="" method=post>
          每页显示<Input type=text name="pageSize" value =2 size=3>
          条记录<Input type=submit name="g" value="确定"></FORM></td>
      <td> <FORM action="" method=post>
           输入页码：<Input type=text name="currentPage" size=2 >
           <Input type=submit name="g" value="提交"></FORM></td></tr>
</Table>
</Center>
</BODY></HTML>
