<%@ page import="com.itdr.pojo.Users" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/8/8
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>主页</title>
    <link rel="stylesheet" href="/css/home.css">
</head>
<body>
<%--
<div>欢迎${user.name}</div>
<ul>
    <li><span class="sp">用户列表</span></li>
    <li><a href="/manage/user/list.do">用户列表</a></li>
    <li></li></ul>
<div id="dd"></div>
&lt;%&ndash;&lt;%&ndash;dash;%&gt;
    &lt;%&ndash;HttpSession hs= request.getSession();&ndash;%&gt;
    &lt;%&ndash;Users u= (Users) hs.getAttribute("user");&ndash;%&gt;
&lt;%&ndash;dash;%&gt;&ndash;%&gt;
--%>
<%--<p><%=u.getName()%></p>--%>
<div>欢迎${user.name}</div>

<div id="continer">
    <div id="con-left">
        <div id="user"><p>用户管理</p>
            <ul>
                <li><a href=/manage/user/list.do">用户列表</a></li>
                <li><a href="">用户禁用</a></li>
                <li><a href="">用户更新</a></li>
            </ul></div>


    </div>
    <div id="con-right"></div>
</div>
</body>

<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script>
    $(".sp").click(function () {
        $.post(
            "/manage/user/list.do",
            function (data) {
                $("#dd").html(data);
            }
        )
    })
</script>
</html>
