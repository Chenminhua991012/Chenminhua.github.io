<%--
  Created by IntelliJ IDEA.
  User: aibizuan
  Date: 2024/4/7
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>重新登陆</title>
</head>
<link rel="stylesheet" type="text/css" href="css/login.css">

<body>
<script type="text/javascript" src="js/register.js"></script>
<center>
    <div id="page">
        <div class="head">
            <div class="imgBox">
                <img class="img-slide img1" src="https://chenminhua.kesug.com/img/register/register1.jpg">
                <img class="img-slide img2" src="https://chenminhua.kesug.com/img/register/register2.jpg">
                <img class="img-slide img3" src="https://chenminhua.kesug.com/img/register/register3.jpg">
                <img class="img-slide img4" src="https://chenminhua.kesug.com/img/register/register4.jpg">
            </div>
        </div>
        <div class="r_page">
            <h5>重新注册</h5>

            <div class="jingjian">

                <div style="text-align: center;line-height: 60px;">
                    <%--三运运算符--%>
                    <%--<%= request.getAttribute("errorMessage")==null?"":"errorMessage" %>--%>
                    <h1>
                        <% if (request.getAttribute("errorMessage") != null) { %>
                        <div style="color:red;"><%= request.getAttribute("errorMessage") %></div>
                        <% } %>
                    </h1>

                </div>

                <table class="main" border="0" cellspacing="0" cellpadding="0">
                <form action="login" method="post" name="myform">
                    <tr>
                        <td class="left"><label for="user">用户名：</label></td>
                        <td class="center"><input id="user" type="text" name="username" placeholder="请输入您的用户名" class="in" onblur="checkUser()"></td>
                        <td><span id="user_prompt">用户名由英文字母和数字组成的6-16位字符，以字母开头</span></td>
                    </tr>

                    <tr>
                        <td class="left"><label for="pwd">密码：</label></td>
                        <td class="center"><input id="pwd" type="password" name="password" placeholder="请输入密码" class="in" onblur="checkPwd()"></td>
                        <td><span id="pwd_prompt">密码由大小写英文字母和数字组成</span></td>
                    </tr>

                    <tr>
                        <td class="left">
                            <label for="validateCode">验证码：</label></td>
                        <td class="center">
                            <div class="validate-code-container">
                                <input type="text" name="validateCode" id="validateCode" placeholder="请输入验证码" class="in">

                                <img src="randomCode" onclick="refreshCode(this)" alt="验证码" title="点击刷新验证码" class="validate-code-img">
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <td class="left"></td>
                        <td class="center"><input id="loginBtn" type="submit" value="登录">
                            <input type="button" name="button" id="button" value="忘记密码" /></td>
                    </tr>

                    <tr>
                        <td class="left"></td>
                        <td class="center">
                            <a href="register.html" class="register-link">没有账号？前往注册</a>
                        </td>
                    </tr>
                </form>
            </table>
        </div>
        </div>

        <div class="l_page">
            <ul>
                <li class="one">
                    <a href="index.html">我的首页</a>
                </li>
                <li>
                    <a href="photo.html">我的相册</a>
                </li>
                <li>
                    <a href="xingzuo.html">我的星座</a>
                </li>
                <li>
                    <a href="mingyan.html">名人名言</a>
                </li>
                <li>
                    <a href="mailto:1744434115@qq.com">联系我们</a>
                </li>
                <li>
                    <a href="register.html">更多请登陆注册</a>
                </li>
                <li>
                    <a href="explain.html">网站设计说明</a>
                </li>
                <li>
                    <a href="wenjuan.html">问卷调查</a>
                </li>
            </ul>
        </div>

        <div class="clearit"></div>
        <p id="p1"></p>
    </div>
    <ul align="right">
        <audio src="https://chenminhua.kesug.com/music/mingyan.mp3" autoplay controls loop></audio>
    </ul>
    <div class="foot">
        <p>版权所有 禁止盗版</p>
        <p>欢迎来到陈民华的博客</p>
    </div>
</center>
</body>

</html>