<%--
  Created by IntelliJ IDEA.
  User: aibizuan
  Date: 2024/4/8
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8" />
    <title>重新注册</title>
</head>
<link rel="stylesheet" type="text/css" href="css/register.css">

<body>
<script type="text/javascript" src="js/register.js"></script>
<center>
    <div id="page">
        <div class="head">
            <div class="imgBox">
                <img class="img-slide img1" src="http://chenminhua.kesug.com/img/register/register1.jpg">
                <img class="img-slide img2" src="http://chenminhua.kesug.com/img/register/register2.jpg">
                <img class="img-slide img3" src="http://chenminhua.kesug.com/img/register/register3.jpg">
                <img class="img-slide img4" src="http://chenminhua.kesug.com/img/register/register4.jpg">
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

                    <form action="register" method="post" name="myform">
                        <br>
                        <tr>
                            <td class="left"><label for="user">用户名：</label></td>
                            <td class="center" style="top: 8px"><input style="height: 20px;" id="user" type="text" name="username" placeholder="请输入您的用户名" class="in" onblur="checkUser()"></td>
                            <td><span id="user_prompt" style="left: 214px;">用户名由英文字母和数字组成的6-16位字符，以字母开头</span></td>
                        </tr>

                        <tr>
                            <td class="left"><label for="pwd">密码：</label></td>
                            <td class="center" style="top: 8px"><input style="height: 20px;" id="pwd" type="password" name="password" placeholder="请输入密码" class="in" onblur="checkPwd()"></td>
                            <td><span id="pwd_prompt" style="left: 214px;">密码由大小写英文字母和数字组成</span></td>
                        </tr>

                        <tr>
                            <td class="left">
                                <label for="repwd">确认密码：</label>
                            </td>
                            <td class="center" style="top: 8px">
                                <input style="height: 20px;" id="repwd" type="password" placeholder="请确认密码" class="in" onblur="checkRepwd()">
                            </td>
                            <td><span id="repwd_prompt" style="left: 214px;"></span></td>
                        </tr>

                        <tr>
                            <td class="left"><label for="birth">生日：</label></td>
                            <td class="center" style="top: 8px"><input style="height: 20px;" id="birth" type="text" class="in" onblur="checkBirth()"></td>
                            <td><span id="birth_prompt" style="left: 214px;"></span></td>
                        </tr>

                        <tr>
                            <td class="left" style="top: 8px;">性别：</td>
                            <td class="center">
                                <label style="line-height: 1.2">
                                    <input type="radio" name="gender" value="男" onblur="gender()" checked="checked" /> 男
                                </label>
                                <label style="line-height: 1.2">
                                    <input type="radio" name="gender" value="女" onblur="gender()" /> 女
                                </label>
                            </td>
                            <!-- 其他内容 -->
                        </tr>

                        <tr>
                            <td class="left">兴趣爱好：
                            </td>
                            <td class="center" style="min-width: 190px">
                                <label><input type="checkbox" name="hobby" value="运动"/>运动</label>
                                <label><input type="checkbox" name="hobby" value="音乐"/>音乐</label>
                                <label><input type="checkbox" name="hobby" value="旅行"/>旅行</label>
                                <label><input type="checkbox" name="hobby" value="代码"/>代码</label>
                                <label><input type="checkbox" name="hobby" value="书海"/>书海</label>
                                <label><input type="checkbox" name="hobby" value="舞蹈"/>舞蹈</label>
                            </td>

                        <tr>
                            <td class="left"><label for="email">电子邮箱：</label></td>
                            <td class="center" style="top: 8px;"><input style="height: 20px;" id="email" type="text" placeholder="请输入邮箱" class="in" onblur="checkEmail()"></td>
                            <td><span id="email_prompt" style="left: 214px;"></span></td>
                        </tr>

                        <tr>
                            <td class="left"><label for="phone">手机号：</label></td>
                            <td class="center" style="top: 8px;"><input style="height: 20px;" id="phone" placeholder="请输入联系电话" type="text" class="in" onblur="checkPhone()"></td>
                            <td><span id="phone_prompt" style="left: 214px;"></span></td>
                        </tr>

                        <tr>
                            <td class="left">
                                <label for="validateCode">验证码：</label></td>
                            <td class="center" style="top: 8px;">
                                <div class="validate-code-container">
                                    <input style="height: 20px;" type="text" name="validateCode" id="validateCode" placeholder="请输入验证码" class="in" />
                                    </label>
                                    <img src="randomCode" onclick="refreshCode(this)" alt="验证码" title="点击刷新验证码" class="validate-code-img" />
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td class="left"></td>
                            <td class="center"><input id="registerBtn" type="submit" value="注册">
                                <input type="reset" name="button" id="button" value="重置" /></td>
                        </tr>

                        <tr>
                            <td class="left"></td>
                            <td class="center" style="margin-left: -9px;">
                                <a href="login.html" class="login-link">已有账号？前往登录</a>
                            </td>
                        </tr>
                    </form>
                </table>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
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
        <audio src="http://chenminhua.kesug.com/music/register.mp3" autoplay controls loop></audio>
    </ul>
    <div class="foot">
        <p>版权所有 禁止盗版</p>
        <p>欢迎来到陈民华的博客</p>
    </div>
</center>

</body>

</html>