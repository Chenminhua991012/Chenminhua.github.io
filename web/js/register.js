let index = 0;

function ChangeImg() {
    index++;
    var a = document.getElementsByClassName("img-slide");
    if (index >= a.length)
        index = 0;
    for (var i = 0; i < a.length; i++) {
        a[i].style.display = 'none';
    }
    a[index].style.display = 'block';
}

setInterval(ChangeImg, 1000);

const reg_id = /^\d{17}[xX0-9]?$/;

const reg_user = /^[a-zA-Z][0-9a-zA-Z_]*$/;

const reg_pwd = /^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)/;

const reg_email = /[\w]+(\.[\w]+)*@[\w]+(\.[\w])+/;

const reg_phone = /^[1][3,4,5,7,8][0-9]{9}$/;

const reg_birth = /^(19\d{2})|20[0-1]\d{1}-(0?[1-9]|1[0-2])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;

function checkIdNumber() {
    const idNumber = document.getElementById("idNumber");
    const idNumberPrompt = document.getElementById("idNumber_prompt");

    idNumberPrompt.innerText = "";
    if (idNumber.value === "") {
        idNumberPrompt.innerText = "身份证号不能为空";
        idNumberPrompt.style.color = "red";
    } else if (!/^\d{17}[xX0-9]?$/.test(idNumber.value)) {
        idNumberPrompt.innerText = "请输入有效的身份证号";
        idNumberPrompt.style.color = "red";
    }
}

function checkUser() {

    const user = document.getElementById("user");
    const userPrompt = document.getElementById("user_prompt");

    userPrompt.innerText = "";
    if (user.value === "") {
        userPrompt.innerText = "用户名不能为空";
        userPrompt.style.color = "red";
    } else if (user.value.length < 3 || user.value.length > 20) {
        console.log(user.value);
        userPrompt.innerText = "格式错误,长度应为3-20个字符";
        userPrompt.style.color = "red";

    } else if (reg_user.test(user.value) === false) {
        userPrompt.innerText = "格式错误,只能包含英文字母、数字及下划线";
        userPrompt.style.color = "red";
    }
    user.onfocus = function () {
        userPrompt.innerText = "";
    }
    user.onblur = function () {
        if (user.value === "") {
            userPrompt.innerText = "用户名不能为空";
            userPrompt.style.color = "red";

        } else if (user.value.length < 3 || user.value.length > 20) {
            console.log(user.value);
            userPrompt.innerText = "格式错误,长度应为3-20个字符";
            userPrompt.style.color = "red";

        } else if (reg_user.test(user.value) === false) {
            userPrompt.innerText = "格式错误,只能包含英文字母、数字及下划线";
            userPrompt.style.color = "red";
        }
    }
}

function checkPwd() {

    const pwd = document.getElementById("pwd");
    const pwdPrompt = document.getElementById("pwd_prompt");

    pwdPrompt.innerText = "";
    if (pwd.value === "") {
        pwdPrompt.innerText = "密码不能为空";
        pwdPrompt.style.color = "red";
    } else if (pwd.value.length < 8) {
        pwdPrompt.innerText = "格式错误,,密码长度至少为8位";
        pwdPrompt.style.color = "red";

    } else if (reg_pwd.test(pwd.value) === false) {
        pwdPrompt.innerText = "格式错误,由大小写字母，数字和特殊字符中两种以上组合";
        pwdPrompt.style.color = "red";
    }
    pwd.onfocus = function () {
        pwdPrompt.innerText = "";
    }
    pwd.onblur = function () {
        if (pwd.value === "") {
            pwdPrompt.innerText = "密码不能为空";
            pwdPrompt.style.color = "red";

        } else if (pwd.value.length < 8) {
            pwdPrompt.innerText = "格式错误,,密码长度至少为8位";
            pwdPrompt.style.color = "red";

        } else if (reg_pwd.test(pwd.value) === false) {
            pwdPrompt.innerText = "格式错误,由大小写字母，数字和特殊字符中两种以上组合";
            pwdPrompt.style.color = "red";
        }
    }
}

function checkRepwd() {

    const pwd = document.getElementById("pwd");
    const repwd = document.getElementById("repwd");
    const repwdPrompt = document.getElementById("repwd_prompt");

    repwdPrompt.innerText = "";
    if (repwd.value === "") {
        repwdPrompt.innerText = "密码不能为空";
        repwdPrompt.style.color = "red";
    } else if (repwd.value !== pwd.value) {
        repwdPrompt.innerText = "两次输入的密码不一致";
        repwdPrompt.style.color = "red";
    }
    repwd.onfocus = function () {
        repwdPrompt.innerText = "";
    }
    repwd.onblur = function () {
        if (repwd.value === "") {
            repwdPrompt.innerText = "密码不能为空";
            repwdPrompt.style.color = "red";

        } else if (repwd.value !== pwd.value) {
            repwdPrompt.innerText = "两次输入的密码不一致";
            repwdPrompt.style.color = "red";
        }
    }
}

function checkEmail() {

    const email = document.getElementById("email");
    const emailPrompt = document.getElementById("email_prompt");

    emailPrompt.innerText = "";
    if (email.value === "") {
        emailPrompt.innerText = "邮箱不能为空";
        emailPrompt.style.color = "red";
    } else if (reg_email.test(email.value) === false) {
        emailPrompt.innerText = "邮箱格式输入错误,例如 xxxx@qq.com";
        emailPrompt.style.color = "red";
    }
    email.onfocus = function () {
        emailPrompt.innerText = "";
    }
    email.onblur = function () {
        if (email.value === "") {
            emailPrompt.innerText = "邮箱不能为空";
            emailPrompt.style.color = "red";
        } else if (email.value.length < 15 || email.length > 30) {
            emailPrompt.innerText = "格式错误,长度应为15-30个字符";
            emailPrompt.style.color = "red";
        } else if (reg_email.test(email.value) === false) {
            emailPrompt.innerText = "邮箱格式输入错误,例如 xxxx@qq.com";
            emailPrompt.style.color = "red";
        }
    }
}

function checkPhone() {

    const phone = document.getElementById("phone");
    const phonePrompt = document.getElementById("phone_prompt");

    phonePrompt.innerText = "";

    if (phone.value === "") {
        phonePrompt.innerText = "手机号码不能为空";
        phonePrompt.style.color = "red";
    } else if (reg_phone.test(phone.value) === false) {
        phonePrompt.innerText = "号码格式输入错误";
        phonePrompt.style.color = "red";
    }
    phone.onfocus = function () {
        phonePrompt.innerText = "";
    }
    phone.onblur = function () {
        if (phone.value === "") {
            phonePrompt.innerText = "手机号码不能为空";
            phonePrompt.style.color = "red";

        } else if (reg_phone.test(phone.value) === false) {
            phonePrompt.innerText = "号码格式输入错误";
            phonePrompt.style.color = "red";
        }
    }
}

function checkBirth() {

    const birth = document.getElementById("birth");
    const birthPrompt = document.getElementById("birth_prompt");

    birthPrompt.innerText = "";

    if (birth.value === "") {
        birthPrompt.innerText = "生日不能为空";
        birthPrompt.style.color = "red";
    } else if (reg_birth.test(birth.value) === false) {
        birthPrompt.innerText = "请输入正确日期，例如 1999-1-2或1999-01-02";
        birthPrompt.style.color = "red";
    }
    birth.onfocus = function () {
        birthPrompt.innerText = "";
    }
    birth.onblur = function () {
        if (birth.value === "") {
            birthPrompt.innerText = "生日不能为空";
            birthPrompt.style.color = "red";

        } else if (reg_birth.test(birth.value) === false) {
            birthPrompt.innerText = "请输入正确日期，例如 1999-1-2或1999-01-02";
            birthPrompt.style.color = "red";
        }
    }
}

// 表单提交前的验证函数
function validateForm() {
    const idNumber = document.getElementById("idNumber");
    const idNumberPrompt = document.getElementById("idNumber_prompt");

    idNumberPrompt.innerText = "";
    if (idNumber.value === "") {
        idNumberPrompt.innerText = "身份证号不能为空";
        idNumberPrompt.style.color = "red";
    }
    if (!reg_id.test(idNumber.value)) {
        idNumberPrompt.innerText = "请输入有效的身份证号";
        idNumberPrompt.style.color = "red";
    }

    const user = document.getElementById("user");
    const userPrompt = document.getElementById("user_prompt");
    // 验证用户名
    if (user.value === "") {
        // alert("用户名不能为空");
        userPrompt.innerText = "用户名不能为空";
        userPrompt.style.color = "red";
        return false;
    }
    if (user.value.length < 6 || user.value.length > 18) {
        // alert("格式错误,长度应为6-18个字符");
        userPrompt.innerText = "格式错误,长度应为6-18个字符";
        userPrompt.style.color = "red";
        return false;
    }
    // test正则表达式对象
    if (!reg_user.test(user.value)) {
        // alert("格式错误,只能包含英文字母、数字及下划线");
        userPrompt.innerText = "格式错误,字母开头且只能包含英文字母、数字及下划线";
        userPrompt.style.color = "red";
        return false;
    }

    const pwd = document.getElementById("pwd");
    const pwdPrompt = document.getElementById("pwd_prompt");
    // 验证密码
    if (pwd.value === "") {
        // alert("密码不能为空");
        pwdPrompt.innerText = "密码不能为空";
        pwdPrompt.style.color = "red";
        return false;
    }
    if (pwd.value.length < 6) {
        // alert("密码长度至少为6位");
        pwdPrompt.innerText = "密码长度至少为6位";
        pwdPrompt.style.color = "red";
        return false;
    }
    if (!reg_pwd.test(pwd.value)) {
        // alert("格式错误,由大小写字母，数字和特殊字符中两种以上组合");
        pwdPrompt.innerText = "格式错误,由大小写字母，数字和特殊字符中两种以上组合";
        pwdPrompt.style.color = "red";
        return false;
    }

    const repwd = document.getElementById("repwd");
    const repwdPrompt = document.getElementById("repwd_prompt");
    // 验证确认密码
    if (repwd.value === "") {
        // alert("密码不能为空");
        repwdPrompt.innerText = "密码不能为空";
        repwdPrompt.style.color = "red";
        return false;
    }
    if (repwd.value !== pwd.value) {
        // alert("两次输入的密码不一致");
        repwdPrompt.innerText = "两次输入的密码不一致";
        repwdPrompt.style.color = "red";
        return false;
    }

    const email = document.getElementById("email");
    const emailPrompt = document.getElementById("email_prompt");
    // 验证邮箱
    if (email.value === "") {
        // alert("邮箱不能为空");
        emailPrompt.innerText = "邮箱不能为空";
        emailPrompt.style.color = "red";
        return false;
    }
    if (email.value.length < 15 || email.length > 30) {
        emailPrompt.innerText = "格式错误,长度应为15-30个字符";
        emailPrompt.style.color = "red";
    }
    if (!reg_email.test(email.value)) {
        // alert("邮箱格式输入错误,例如 xxxx@qq.com");
        emailPrompt.innerText = "邮箱格式输入错误,例如 xxxx@qq.com";
        emailPrompt.style.color = "red";
        return false;
    }

    const phone = document.getElementById("phone");
    const phonePrompt = document.getElementById("phone_prompt");
    // 验证手机号
    if (phone.value === "") {
        // alert("手机号不能为空");
        phonePrompt.innerText = "手机号不能为空";
        phonePrompt.style.color = "red";
        return false;
    }
    if (email.value.length !== 11) {
        emailPrompt.innerText = "格式错误,长度应为11个数字";
        emailPrompt.style.color = "red";

    }
    if (!reg_phone.test(phone.value)) {
        // alert("号码格式输入错误");
        phonePrompt.innerText = "号码格式输入错误";
        phonePrompt.style.color = "red";
        return false;
    }

    const birth = document.getElementById("birth");
    const birthPrompt = document.getElementById("birth_prompt");
    // 验证生日
    if (birth.value === "") {
        // alert("生日不能为空");
        birthPrompt.innerText = "生日不能为空";
        birthPrompt.style.color = "red";
        return false;
    }
    if (!reg_birth.test(birth.value)) {
        // alert("请输入正确日期，例如 1999-1-2或1999-01-02");
        birthPrompt.innerText = "请输入正确日期，例如 1999-1-2或1999-01-02";
        birthPrompt.style.color = "red";
        return false;
    }

    // 所有验证都通过，允许表单提交
    return true;
}

// 重置用户表单和提示信息的函数
function resetForm() {

    // 重置表单
    document.myform.reset();
    // 恢复所有提示信息到初始状态
    document.getElementById("user_prompt").innerText = "用户名由英文字母和数字组成的6-16位字符，以字母开头";
    document.getElementById("pwd_prompt").innerText = "密码由大小写英文字母和数字组成";
    document.getElementById("repwd_prompt").innerText = "";
    document.getElementById("email_prompt").innerText = "";
    document.getElementById("phone_prompt").innerText = "";
    document.getElementById("birth_prompt").innerText = "";
    // 清空错误样式
    document.getElementById("user_prompt").style.color = "";
    document.getElementById("pwd_prompt").style.color = "";
    document.getElementById("repwd_prompt").style.color = "";
    document.getElementById("email_prompt").style.color = "";
    document.getElementById("phone_prompt").style.color = "";
    document.getElementById("birth_prompt").style.color = "";
}

// 重置管理员表单和提示信息的函数
function admin_resetForm() {

    // 重置表单
    document.myform.reset();
    // 恢复所有提示信息到初始状态
    document.getElementById("idNumber_prompt").innerText = "请输入18位身份证号或15位旧身份证号";
    document.getElementById("user_prompt").innerText = "用户名由英文字母和数字组成的6-16位字符，以字母开头";
    document.getElementById("pwd_prompt").innerText = "密码由大小写英文字母和数字组成";
    document.getElementById("email_prompt").innerText = "有效的邮箱地址,长度为15-30个字符";
    document.getElementById("phone_prompt").innerText = "请输入有效的11位手机号码";
    document.getElementById("birth_prompt").innerText = "";
    // 清空错误样式
    document.getElementById("idNumber_prompt").style.color = "";
    document.getElementById("user_prompt").style.color = "";
    document.getElementById("pwd_prompt").style.color = "";
    document.getElementById("email_prompt").style.color = "";
    document.getElementById("phone_prompt").style.color = "";
    document.getElementById("birth_prompt").style.color = "";
}

// 绑定重置按钮的点击事件
// window.onload = function() {
// 	document.getElementById("button").onclick = resetForm;
// };

// document.addEventListener('DOMContentLoaded', (event) => {
// 	var button = document.getElementById("button");
// 	if (button) {
// 		button.onclick = resetForm;
// 	}
// });

// document.addEventListener('DOMContentLoaded', (event) => {
// 	document.getElementById("button").onclick = resetForm;
// });

function refreshCode(img) {
    // console.log(111111)
    img.src = "randomCode?id=" + Date.now();
}