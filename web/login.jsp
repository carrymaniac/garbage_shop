<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="loginBean" class="cn.gdou.entity.Login" scope="session"/>
<HTML><HEAD><%@ include file="head.jsp" %></HEAD>
<BODY bgcolor=pink><font size=2>
<div align="center">
<FORM action="loginServlet" Method="post">
<table border=2>
<tr> <th>登录</th></tr>
<tr><td>登录名称:<Input type=text name="loginName"></td></tr>
<tr><td>输入密码:<Input type=password name="password"></td></tr>
</table>
<Input type=submit name="g" value="提交">
</form>
</div >
<div align="center" >
登录反馈信息:<br>
<jsp:getProperty name="loginBean" property="backNews"/>
<br>登录名称:<br><jsp:getProperty name="loginBean" property="loginName"/>
<div >
</font>
</BODY></HTML>
