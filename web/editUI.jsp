<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        img {
            border: 0;
        }

        ul, li {
            list-style-type: none;
        }

        a {
            color: #00007F;
            text-decoration: none;
        }

        a:hover {
            color: #bd0a01;
            text-decoration: underline;
        }

        .treebox {
            width: 200px;
            background-color: #1a6cb9;
            height: 95vh;
        }

        .menu {
            overflow: hidden;
            border-color: #ddd;
            border-style: solid;
            border-width: 0 1px 1px;
        }

        .menu li.level1 > a {
            display: block;
            height: 45px;
            line-height: 45px;
            color: #fff;
            padding-left: 50px;
            border-bottom: 1px solid #000;
            font-size: 16px;
            position: relative;
        }

        .menu li.level1 a:hover {
            text-decoration: none;
            background-color: #326ea5;
        }

        .menu li.level1 a.current {
            background: #0f4679;
        }

        .ico {
            width: 20px;
            height: 20px;
            display: block;
            position: absolute;
            left: 20px;
            top: 10px;
            background-repeat: no-repeat;
            background-image: url(${pageContext.request.contextPath }/image/ico1.png);
        }

        .level1 i {
            width: 20px;
            height: 10px;
            background-image: url(${pageContext.request.contextPath }/image/arrow.png);
            background-repeat: no-repeat;
            display: block;
            position: absolute;
            right: 20px;
            top: 20px;
        }

        .level1 i.down {
            background-position: 0 -10px;
        }

        .ico1 {
            background-position: 0 0;
        }

        .ico2 {
            background-position: 0 -20px;
        }

        .ico3 {
            background-position: 0 -40px;
        }

        .ico4 {
            background-position: 0 -60px;
        }

        .ico5 {
            background-position: 0 -80px;
        }

        .menu li ul {
            overflow: hidden;
        }

        .menu li ul.level2 {
            display: none;
            background: #0f4679;
        }

        .menu li ul.level2 li a {
            display: block;
            height: 45px;
            line-height: 45px;
            color: #fff;
            text-indent: 60px;
            /*border-bottom: 1px solid #ddd; */
            font-size: 14px;
        }

    </style>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath }/assets/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath }/assets/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/assets/js/easing.js"></script>
    <script src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div style="height: 5vh">
    <!--路径导航-->
    <ol class="breadcrumb breadcrumb_nav">
        <li>首页</li>
        <li><a href="${pageContext.request.contextPath }/good?method=list">商品管理</a></li>
        <li class="active">商品编辑</li>
    </ol>
</div>
<%--<div class="container">--%>
<div class="treebox col-sm-2 col-md-2 col-lg-2" style="height: 100vh">
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
<div class="col-md-10 column" style="height: 100vh">
    <h3 class="text-center">
        添加商品
    </h3>
    <div class="form" style="padding:40px; ">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label>手机型号</label>
                <input name="lastName" type="text" class="form-control">
            </div>
            <div class="form-group">
                <label>手机名</label>
                <input name="email" type="text" class="form-control">
            </div>

            <div class="form-group">
                <label>手机制造商</label><br/>
                <input name="email" type="text" class="form-control">
            </div>

            <div class="form-group">
                <label>手机价格</label>
                <input name="birth" type="text" class="form-control">
            </div>

            <div class="form-group">
                <label>手机类别</label>
                <select class="form-control" name="department.id">
                    <option>1</option>
                </select>
            </div>

            <div class="form-group">
                <label>手机信息</label>
                <input name="birth" type="text" class="form-control" value="">
            </div>

            <div class="form-group">
                <label>手机图片</label>
                <input name="birth" type="text" class="form-control" value="">
            </div>

            <button type="submit" class="btn btn-primary">添加</button>
        </form>
    </div>
</div>
</div>
<%--</div>--%>

<script type="text/javascript">
    //定义js方法格式： function 方法名(){}
    // js 只有一种数据类型 var
    <%--function queryByPages(){--%>
    <%--//设置当前浏览器的地址--%>
    <%--var pageSize = document.getElementById("pageSize").value;--%>
    <%--var userName = document.getElementById("userName").value;--%>
    <%--document.location.href="${pageContext.request.contextPath }/user?method=list&currentPage=1&pageSize="+pageSize+"&userName="+userName;--%>
    <%--}--%>
    //等待dom元素加载完毕.
    $(function () {
        $(".treebox .level1>a").click(function () {
            $(this).addClass('current')   //给当前元素添加"current"样式
                .find('i').addClass('down')   //小箭头向下样式
                .parent().next().slideDown('slow', 'easeOutQuad')  //下一个元素显示
                .parent().siblings().children('a').removeClass('current')//父元素的兄弟元素的子元素去除"current"样式
                .find('i').removeClass('down').parent().next().slideUp('slow', 'easeOutQuad');//隐藏
            return false; //阻止默认时间
        });
    })
</script>
</body>
</html>