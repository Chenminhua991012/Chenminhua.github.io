<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: aibizuan
  Date: 2024/4/12
  Time: 8:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>编辑学生信息</title>
    <style>
        body {
            /*background-color: #f4f4f4;*/
            margin: 0;
            padding: 0;
        }
        .edit_container {
            max-width: 400px;
            min-width: 400px;
            position: absolute;
            transform: translate(-50%,-50%);
            top: 40%;
            left: 50%;
            /*margin: 150px 410px;*/
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            /*color: #333;*/
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: right;
            border-bottom: 1px solid #ddd;
        }

        select {
            float: left;
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            box-sizing: border-box;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #5cb85c;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }
        input[type="submit"]:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
<div class="edit_container">
<h2>编辑学生信息</h2>
<form action="updateStudent" method="post">
    <table>
<%--    <label for="studentId">学号:</label>--%>
<%--    <input type="text" id="studentId" name="studentId" value="${student.getStudentId()}" />--%>
    <tr>
    <th><label for="originalStudentId">原始学号:</label></th>
    <td><input type="number" id="originalStudentId" name="originalStudentId" value="${student.getStudentId()}" readonly /></td>
    </tr>
    <tr>
    <th><label for="newStudentId">新学号:</label></th>
    <td><input type="number" id="newStudentId" name="newStudentId" /></td>
    </tr>
    <tr>
    <th><label for="name">姓名:</label></th>
    <td><input type="text" id="name" name="studentName" value="${student.getName()}" required></td>
    </tr>
    <tr>
    <th><label for="age">年龄:</label></th>
    <td><input type="number" id="age" name="studentAge" value="${student.getAge()}" min="1" max="120" required></td>
    </tr>
    <tr>
    <th><label for="score">成绩:</label></th>
    <td><input type="number" id="score" name="studentScore" value="${student.getScore()}" min="0" max="100" required></td>
    </tr>
    <tr>
    <th><label for="classId">班级:</label></th>
    <td>
        <lable>
            <select id="classId" name="studentClass">
                <!-- 这里应该根据班级信息生成选项，示例代码如下 -->
                <c:forEach var="clazz" items="${classMap}">
                    <option value="${clazz.key}" <c:if test="${clazz.key == student.classId}">selected</c:if>>${clazz.value.className}</option>
                </c:forEach>
            </select>
        </lable>
    </td>
    </tr>

    <tr>
        <td colspan="2" style="text-align: center"><input type="submit" value="更新"></td>
    </tr>
    </table>
</form>
</div>
<%--<form action="updateStudent" method="post">--%>
<%--    <label for="studentId">学号:</label>--%>
<%--    <input type="text" id="studentId" name="studentId" value="${student.getStudentId()}" required />--%>
<%--    <label for="name">姓名:</label>--%>
<%--    <input type="text" id="name" name="studentName" value="${student.getName()}" required />--%>
<%--    <label for="age">年龄:</label>--%>
<%--    <input type="number" id="age" name="studentAge" value="${student.getAge()}" required />--%>
<%--    <label for="score">分数:</label>--%>
<%--    <input type="number" id="score" step="0.01" name="studentScore" value="${student.getScore()}" required />--%>
<%--    <label for="classId">班级:</label>--%>
<%--    <select name="studentClass" id="classId">--%>
<%--    <!-- 这里应该填充班级下拉列表 -->--%>
<%--        <c:forEach items="${classMap}" var="clazz">--%>
<%--            <option value="${clazz.value.classId}">${clazz.value.className}</option>--%>
<%--&lt;%&ndash;            <option value="${entry.key}"&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <c:if test="${entry.value.classId == student.classId}">selected</c:if>>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    ${entry.value.className}&ndash;%&gt;--%>
<%--&lt;%&ndash;            </option>&ndash;%&gt;--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
<%--    <button type="submit">更新</button>--%>
<%--</form>--%>
<%--<form action="deleteStudent" method="get">--%>
<%--    <input type="text" name="studentId" value="${student.getstudentId()}" required />--%>
<%--    <button type="submit">删除</button>--%>
<%--</form>--%>
</body>
</html>