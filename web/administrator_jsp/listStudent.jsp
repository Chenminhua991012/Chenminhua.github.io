<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="administrator.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生列表</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            /*background-color: #f4f4f4;*/
            /*margin: 0;*/
            /*padding: 0;*/
            /*display: flex;*/
            /*justify-content: center;*/
            /*align-items: center;*/
            /*height: 100vh;*/
            text-align: center;
        }
        .list_container {
            max-width: 600px;
            min-width: 600px;
            /*margin: 150px 320px;*/
            position: absolute;
            transform: translate(-50%,-50%);
            top: 40%;
            left: 50%;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            overflow: hidden;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            text-align: center;
        }
        th, td {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        button {
            background-color: #f44336;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 4px;
            display: inline-block;
            margin-top: 10px;
            cursor: pointer;
        }
        button:hover {
            background-color: #e53935;
        }
    </style>

</head>
<body>
<%
    ArrayList<Student> students;
    Object object = request.getSession().getAttribute("students");
    if (object != null) {
        students = (ArrayList<Student>) object;
    } else {
        students = new ArrayList<>();
    }
%>

<div class="list_container">
    <h1>学生列表</h1>
<%--    <table>--%>
<%--        <tr>--%>
<%--            <th>姓名</th>--%>
<%--            <th>年龄</th>--%>
<%--            <th>成绩</th>--%>
<%--        </tr>--%>
<%--        <%--%>
<%--            ArrayList<Student> students = (ArrayList<Student>) session.getAttribute("students");--%>
<%--            if (students != null) {--%>
<%--                for (Student student : students) {--%>
<%--        %>--%>
<%--        <tr>--%>
<%--            <td><%= student.getName() %></td>--%>
<%--            <td><%= student.getAge() %></td>--%>
<%--            <td><%= student.getScore() %></td>--%>
<%--            <td><%= student.getClassName() %></td>--%>
<%--        </tr>--%>
<%--        <%--%>
<%--                }--%>
<%--            } else {--%>
<%--                    students = new ArrayList<>();--%>
<%--                }--%>
<%--        %>--%>
<%--    </table>--%>
    <table>
        <tr>
        <th>姓名</th>
        <th>年龄</th>
        <th>成绩</th>
        <th>班级</th>
        </tr>
            <% for (Student student : students){%>
        <tr>
            <td><%= student.getName() %></td>
            <td><%= student.getAge() %></td>
            <td><%= student.getScore() %></td>
            <td><%= student.getClassName() %></td>
        </tr>
            <%}%>
    </table>
    <table border="1">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>成绩</th>
            <th>班级</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach items="${students}" var="student" varStatus="s">
            <tr style="background-color: ${s.count%2==0?"aquamarine":"aqua"}">
                <td>${student.getStudentId()}</td>
                <td>${student.getName()}</td>
                <td>${student.getAge()}</td>
                <td>${student.getScore()}</td>

<%--                <td>${student.getClassId()}</td>--%>
                <td>${classMap[student.classId].className}</td>

<%--                <a> 标签默认使用 GET 方法来发起请求--%>
<%--                编辑按钮，传递学生姓名作为查询参数,当用户点击时,将请求名为editStudent的Servlet,并附带一个查询参数studentName--%>
<%--                <td><a href="editStudent?studentId=${student.getStudentId()}">编辑</a></td>--%>
                <td><a href="#" onclick="editStudent('${student.studentId}')">编辑</a></td>
<%--                删除按钮--%>
                <td><a href="deleteStudent?studentId=${student.getStudentId()}" onclick="return confirm('确定删除吗？')">删除</a></td>
            </tr>
        </c:forEach>
    </table>
    <button onclick="location.href='main.jsp?load=addStudent.jsp'">返回添加</button></div>
<%--    <button onclick="loadContent('addStudent.jsp')">返回添加</button></div>--%>
</body>
</html>
