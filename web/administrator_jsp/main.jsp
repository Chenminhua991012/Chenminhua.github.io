<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理系统</title>
    <script type="text/javascript" src="../js/administrator_main.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/administrator/main.css">
    <script type="text/javascript" src="../js/register.js"></script>
</head>
<body>
<div class="header">
    <h1>用户管理系统</h1>
</div>

<div class="container">
    <div class="nav-container">
        <ul>
            <li><a href="addUser.jsp">添加用户</a></li>
            <li><a href="listUser.jsp">查看用户</a></li>
            <!-- 其他导航链接可以在这里添加 -->
        </ul>
    </div>
    <div class="main-content">
        <div class="content-header">
            <h2>欢迎来到用户管理系统</h2>
            <p>这里提供了一系列功能，帮助您管理用户信息。</p>
        </div>
        <div class="feature-blocks">
            <div class="block add-student">
                <h3>添加用户</h3>
                <p>点击下方按钮进入添加用户信息页面。</p>
                <button onclick="loadContent('addUser.jsp')" class="btn">添加用户</button>
            </div>
            <div class="block list-students">
                <h3>查看用户列表</h3>
                <p>查看所有已录入的用户信息。</p>
                <button onclick="loadContent('listUser.jsp')" class="btn">查看用户列表</button>
            </div>
            <!-- 更多功能区块可以在这里添加 -->
        </div>
        <div class="stats">
            <h3>用户统计</h3>
            <div class="chart-container">
                <!-- 这里可以嵌入一个图表，例如使用Chart.js或其他图表库 -->
                <canvas id="userChart"></canvas>
            </div>
        </div>
        <div class="notifications">
            <h3>最新通知</h3>
            <ul>
                <li>2024年春季学期注册现已开放。</li>
                <li>请所有用户更新个人信息。</li>
                <!-- 更多通知可以在这里添加 -->
            </ul>
        </div>
        <%--定义用户管理系统的页脚,提供版权信息、联系方式和帮助支持链接--%>
        <footer class="site-footer">
            <%--All rights reserved. 版权声明
            用户管理系统在2024年拥有其所有内容的版权，并且所有权利都被保留--%>
            <p>&copy; 2024 用户管理系统. All rights reserved.</p>
            <%--联系我--%>
            <p>Contact me: <a href="mailto:1744434115@qq.com">1744434115@qq.com</a></p>
            <%--帮助和支持--%>
            <p><a href="#">Help & Support</a></p>
        </footer>
    </div>
</div>

<div class="footer">
    <p>龙岩学院23级软件工程232Z班</p>
</div>
</body>
</html>