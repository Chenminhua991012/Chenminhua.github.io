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
    <title>用户信息录入</title>
    <link rel="stylesheet" type="text/css" href="../css/administrator/addUser.css">
</head>
<body>
<div class="add_container">
    <h1>用户信息录入</h1>
    <form action="../addUser" method="post" name="myform" id="myform"  onsubmit="return validateForm()">
        <table>
            <tr>
                <th><label for="idNumber">身份证号:</label></th>
                <td><input type="text" id="idNumber" name="idNumber" pattern="\d{17}[xX0-9]?" title="请输入18位身份证号或15位旧身份证号" required onblur="checkIdNumber()"></td>
                <td><span id="idNumber_prompt">请输入18位身份证号或15位旧身份证号</span></td>
            </tr>
            <tr>
                <th><label for="user">用户名:</label></th>
                <td><input id="user" type="text" name="userName" pattern="[a-zA-Z0-9_]{3,20}" title="用户名由英文字母、数字和下划线组成，长度为3-20个字符" required onblur="checkUser()"></td>
                <td><span id="user_prompt">用户名由英文字母和数字组成的3-20位字符，以字母开头</span></td>
            </tr>
            <tr>
                <th><label for="pwd">密码:</label></th>
                <td><input id="pwd" type="password" name="userPwd" minlength="8" title="密码长度至少为8个字符" required onblur="checkPwd()"></td>
                <td><span id="pwd_prompt">密码由大小写英文字母和数字组成</span></td>
            </tr>
            <tr>
                <th><label for="birth">生日:</label></th>
                <td><input id="birth" type="text" name="userBirth" onblur="checkBirth()"></td>
                <td><span id="birth_prompt"></span></td>
            </tr>
            <tr>
                <th>性别:</th>
                <td class="center">
                    <label>
                        <input type="radio" name="userGender" value="male" onblur="gender()" checked="checked" /> 男
                    </label>
                    <label>
                        <input type="radio" name="userGender" value="female" onblur="gender()" /> 女
                    </label>
                </td>
            </tr>
            <tr>
                <th>爱好:</th>
                <td class="center">
                    <label><input type="checkbox" name="userHobby" value="interest"/>运动</label>
                    <label><input type="checkbox" name="userHobby" value="music"/>音乐</label>
                    <label><input type="checkbox" name="userHobby" value="trave"/>旅行</label>
                    <label><input type="checkbox" name="userHobby" value="code"/>代码</label>
                    <label><input type="checkbox" name="userHobby" value="book"/>书海</label>
                    <label><input type="checkbox" name="userHobby" value="dance"/>舞蹈</label>
                </td>
            </tr>
            <tr>
                <th><label for="email">电子邮箱:</label></th>
                <td><input type="email" id="email" name="userEmail" pattern="[\w.-]+@[\w.-]+\.[a-zA-Z]{2,}" title="请输入有效的邮箱地址，长度为15-30个字符" required onblur="checkEmail(this)"></td>
                <td><span id="email_prompt">有效的邮箱地址,长度为15-30个字符</span></td>
            </tr>
            <tr>
                <th><label for="phone">手机号:</label></th>
                <td><input id="phone" type="text" name="userPhone" pattern="^[1][3,4,5,7,8][0-9]{9}$" title="请输入有效的11位手机号码" required onblur="checkPhone()"></td>
                <td><span id="phone_prompt">请输入有效的11位手机号码</span></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center;"><input type="submit" value="保存">
                    <input type="reset" name="button" id="button" value="重置" onclick="admin_resetForm()" /></td>
            </tr>
        </table>
    </form>
</div>
</body>

</html>