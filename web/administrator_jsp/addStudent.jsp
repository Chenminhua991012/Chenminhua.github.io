<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="administrator.Clazz" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息录入</title>
    <style>
        body {
            /*background-color: #f4f4f4;*/
            margin: 0;
            padding: 0;
        }
        .add_container {
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
        /*th {*/
        /*    background-color: #f2f2f2;*/
        /*}*/
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
<%--    <script>--%>
<%--        function validateStudentId(studentId) {--%>
<%--            var isValid = true;--%>
<%--            // 通过AJAX请求后端验证学号是否唯一--%>
<%--            $.ajax({--%>
<%--                url: 'validateStudentId',--%>
<%--                type: 'POST',--%>
<%--                data: { studentId: studentId },--%>
<%--                async: false,--%>
<%--                success: function(response) {--%>
<%--                    isValid = response; // 根据后端返回的结果设置isValid--%>
<%--                }--%>
<%--            });--%>
<%--            return isValid;--%>
<%--        }--%>
<%--    </script>--%>
</head>
<body>
<div class="add_container">
    <h1>学生信息录入</h1>
    <form action="addStudent" method="get">
        <table>
            <%
                // 是否已经存在 'classMap' 属性
//                LinkedHashMap<String, Clazz> classMap = (LinkedHashMap<String, Clazz>) application.getAttribute("classMap");
//                jsp中使用全局域存储通常用application
                if (application.getAttribute("classMap")==null) {
                    // 如果不存在，则创建一个新的映射并填充它
                    LinkedHashMap<String, Clazz> classMap = new LinkedHashMap<>();
//                    第一个字符串是键(key)用来在classMap中检索Clazz对象的标识符,key和Clazz中的第一个参数要相同
                    classMap.put("dd",new Clazz("dd","待定"));
                    classMap.put("Z2",new Clazz("Z2","软工Z2"));
                    classMap.put("Z22",new Clazz("Z22","软工Z22"));
                    classMap.put("Z222",new Clazz("Z222","软工Z222"));
                    classMap.put("Z2222",new Clazz("Z2222","软工Z2222"));
//                    application是全局共享的,所以不能直接使用session来获取,需要使用ServletContext
                    application.setAttribute("classMap",classMap);
//                     或者直接使用session来存储,则可以使用使用session来获取
//                    session.setAttribute("classMap",classMap);
                }
            %>
            <tr>
                <th><label for="studentId">学号:</label></th>
                <td><input type="number" id="studentId" name="studentId"></td>
            </tr>
            <tr>
                <th><label for="studentName">姓名:</label></th>
                <td><input type="text" id="studentName" name="studentName"></td>
            </tr>
            <tr>
                <th><label for="studentAge">年龄:</label></th>
                <td><input type="number" id="studentAge" name="studentAge" min="1" max="120"></td>
            </tr>
            <tr>
                <th><label for="studentScore">成绩:</label></th>
                <td><input type="number" id="studentScore" name="studentScore" min="0" max="100"></td>
            </tr>
            <tr>
                <th>班级:</th>
                <td>
                    <label>
                        <select name="studentClass">
                            <%--用forEach遍历集合或数组,items指定要遍历的集合,var定义在每次迭代中当前元素的变量名--%>
                            <c:forEach items="${classMap}" var="clazz">
                                <%--获取当前clazz对象的classId(className)属性,并显示为option文本内容--%>
                                <%--${clazz.value.classId}设置<option>元素的值,提交的表单数据中studentClass参数的值将是对应班级的ID--%>
                                <option value="${clazz.value.classId}">${clazz.value.className}</option>
<%--                                <c:if test="${student.classId == clazz.value.classId}">--%>
<%--                                    selected--%>
<%--                                </c:if>>${clazz.value.className}--%>
                            </c:forEach>
                        </select>
                    </label>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input type="submit" value="保存"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>