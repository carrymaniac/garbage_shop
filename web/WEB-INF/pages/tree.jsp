<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<div class="treebox col-sm-2 col-md-2 col-lg-2" >
    <ul class="menu">
        <li class="level1">
            <a href="#none"><em class="ico ico1"></em>用户管理<i class="down"></i></a>
            <ul class="level2">
                <li><a href="${pageContext.request.contextPath }/user?method=list">用户列表</a></li>
                <li><a href="javascript:;">用户增加</a></li>
            </ul>
        </li>
        <li class="level1">
            <a href="#none"><em class="ico ico2"></em>商品管理<i></i></a>
            <ul class="level2">
                <li><a href="${pageContext.request.contextPath }/good?method=list">商品列表</a></li>
                <li><a href="javascript:;">商品类型</a></li>
                <li><a href="javascript:;">商品库存</a></li>
            </ul>
        </li>
        <li class="level1">
            <a href="#none"><em class="ico ico3"></em>订单管理<i></i></a>
            <ul class="level2">
                <li><a href="${pageContext.request.contextPath }/order?method=list">订单一览</a></li>
                <li><a href="javascript:;">订单处理</a></li>
            </ul>
        </li>
    </ul>
</div>
