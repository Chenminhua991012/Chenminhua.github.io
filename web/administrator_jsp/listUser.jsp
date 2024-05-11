<%--
  Created by IntelliJ IDEA.
  User: aibizuan
  Date: 2024/4/9
  Time: 9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="administrator.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css" href="../css/administrator/listStudent.css">
</head>
<body>
<div class="list_container">
    <h1>用户列表</h1>
    <table>
        <tr>
            <th>身份证号</th>
            <th>用户名</th>
            <th>密码</th>
            <th>生日</th>
            <th>性别</th>
            <th>邮箱</th>
            <th>电话</th>
            <th>爱好</th>
        </tr>
        <%
            List<User> users = (List<User>) session.getAttribute("users");
            if (users != null) {
                for (User user : users) {
        %>
        <tr>
            <td><%= user.getIdNumber() %></td>
            <td><%= user.getUsername() %></td>
            <td><%= user.getPassword() %></td>
            <td><%= user.getBirth() %></td>
            <td><%= user.getGender() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getPhone() %></td>
            <td>
                <% for (String hobby : user.getHobby()) { %>
                <%= hobby %><% if (!hobby.equals(user.getHobby().get(user.getHobby().size() - 1))) { %>, <% } %>
                <% } %>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
<%--    <button onclick="location.href='addUser.jsp'">返回添加</button>--%>
    <button onclick="loadContent('addUser.jsp')">返回添加</button></div>
</div>
</body>
</html>