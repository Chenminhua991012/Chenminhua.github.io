<%--
  Created by IntelliJ IDEA.
  User: aibizuan
  Date: 2024/4/24
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        body {
            background: url('../img/photo/bj.jpg') no-repeat;
            background-size: 100% 140%;
        }

        #login_box {
            width: 20%;
            height: 400px;
            background: rgba(0, 0, 0, 0.6);
            /*rgba设置透明层*/
            background-color: #00000060;
            /*八位颜色位设置透明层*/
            margin: auto;
            margin-top: 10%;
            text-align: center;
            border-radius: 10px;
            padding: 50px 50px;
        }

        h2 {
            color: #ffffff90;
            margin-top: 5%;
        }

        #input-box {
            margin-top: 5%;
        }

        span {
            color: #fff;
        }

        input {
            border: 0;
            width: 60%;
            font-size: 15px;
            color: #fff;
            background: transparent;
            border-bottom: 2px solid #fff;
            padding: 5px 10px;
            outline: none;
            margin-top: 10px;
        }

        button {
            margin-top: 50px;
            width: 60%;
            height: 30px;
            border-radius: 10px;
            border: 0;
            color: #fff;
            text-align: center;
            line-height: 30px;
            font-size: 15px;
            background-image: linear-gradient(to right, #30cfd0, #330867);
        }

        #sign_up {
            margin-top: 45%;
            margin-left: 60%;
        }

        a {
            color: #b94648;
        }
    </style>
</head>
<body>
<div id="login_box">
    <h2>LOGIN</h2>
    <div id="input_box">
        <input type="text" placeholder="请输入用户名">
    </div>
    <div class="input_box">
        <input type="password" placeholder="请输入密码">
    </div>
    <button>登录</button><br>
</div>

</body>
</html>
