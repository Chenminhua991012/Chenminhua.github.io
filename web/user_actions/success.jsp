<%--
  Created by IntelliJ IDEA.
  User: aibizuan
  Date: 2024/4/24
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        html {
            height: 100%;
        }

        body {
            background-image: linear-gradient(to bottom right, rgb(114, 135, 254), rgb(130, 88, 186));
        }

        .login-container {
            width: 600px;
            height: 315px;
            margin: 0 auto;
            margin-top: 10%;
            border-radius: 15px;
            box-shadow: 0 10px 50px 0px rgb(59, 45, 159);
            background-color: rgb(95, 76, 194);
        }

        .left-container {
            display: inline-block;
            width: 330px;
            height: calc(100% - 120px);
            border-top-left-radius: 15px;
            border-bottom-left-radius: 15px;
            padding: 60px;
            background-image: linear-gradient(to bottom right, rgb(118, 76, 163), rgb(92, 103, 211));
        }

        .title {
            color: #fff;
            font-size: 18px;
            font-weight: 200;
        }

        .title span {
            border-bottom: 3px solid rgb(237, 221, 22);
        }

        .input-container {
            padding: 20px 0;
        }

        input {
            border: 0;
            background: none;
            outline: none;
            color: #fff;
            margin: 20px 0;
            display: block;
            width: 100%;
            padding: 5px 0;
            transition: .2s;
            border-bottom: 1px solid rgb(199, 191, 219);
        }

        input:hover {
            border-bottom-color: #fff;
        }

        ::-webkit-input-placeholder {
            color: rgb(199, 191, 219);
        }

        .message-container {
            font-size: 14px;
            transition: .2s;
            color: rgb(199, 191, 219);
            cursor: pointer;
        }

        .message-container:hover {
            color: #fff;
        }

        .right-container {
            width: 145px;
            display: inline-block;
            height: calc(100% - 120px);
            vertical-align: top;
            padding: 60px 0;
        }

        .regist-container {
            text-align: center;
            color: #fff;
            font-size: 18px;
            font-weight: 200;
        }

        .regist-container span {
            border-bottom: 3px solid rgb(237, 221, 22);
        }

        .action-container {
            font-size: 10px;
            color: #fff;
            text-align: center;
            position: relative;
            top: 200px;
        }

        .action-container span {
            border: 1px solid rgb(237, 221, 22);
            padding: 10px;
            display: inline;
            line-height: 20px;
            border-radius: 20px;
            position: absolute;
            bottom: 10px;
            left: calc(72px - 20px);
            transition: .2s;
            cursor: pointer;
        }

        .action-container span:hover {
            background-color: rgb(237, 221, 22);
            color: rgb(95, 76, 194);
        }
    </style>
</head>
<body>
<div class="login-container">
    <div class="left-container">
        <div class="title"><span>登录</span></div>
        <div class="input-container">
            <input type="text" name="username" placeholder="用户名">
            <input type="password" name="password" placeholder="密码">
        </div>
        <div class="message-container">
            <span>忘记密码</span>
        </div>
    </div>
    <div class="right-container">
        <div class="regist-container">
            <span class="regist">注册</span>
        </div>
        <div class="action-container">
            <span>提交</span>
        </div>
    </div>
</div>
</body>

</html>
