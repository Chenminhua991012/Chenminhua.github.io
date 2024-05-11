package administrator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author aibizuan
 */
@WebServlet("/addUser")
public class AddUserServlet extends HttpServlet {
    private static final String USER_PATTERN = "^[a-zA-Z0-9_]{6,18}$";
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+])[A-Za-z\\d!@#$%^&*()_+]{6,18}$";
    private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PHONE_PATTERN = "^((13[0-9])|(14[5|7])|(15[0|1|2|3|4|5|6|7|8|9])|(18[0|1|2|3|4|5|6|7|8|9]))\\d{8}$";
    private static final String BIRTH_PATTERN = "^(\\d{4})-(\\d{2})-(\\d{2})$";
    private final UserDatabaseManager userDbManager = new UserDatabaseManager();

    private static List<String> getStrings(String[] userHobbyValues) {
        List<String> hobbyList = new ArrayList<>();
        if (userHobbyValues != null) {
            for (String hobbyValue : userHobbyValues) {
                // 根据value映射到中文描述
                switch (hobbyValue) {
                    case "interest":
                        hobbyList.add("运动");
                        break;
                    case "music":
                        hobbyList.add("音乐");
                        break;
                    case "trave":
                        hobbyList.add("旅行");
                        break;
                    case "code":
                        hobbyList.add("代码");
                        break;
                    case "book":
                        hobbyList.add("书海");
                        break;
                    case "dance":
                        hobbyList.add("舞蹈");
                        break;
                    default:
                        // 如果当前爱好值未被识别，则添加到列表中
                        hobbyList.add("未知的爱好");
                        break;
                }
            }
        } else {
            // 如果没有选中任何爱好，则添加"未知的爱好"
            hobbyList.add("未知的爱好");
        }
        return hobbyList;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idNumber = request.getParameter("idNumber");
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");
        String userBirth = request.getParameter("userBirth");
        String userGender = request.getParameter("userGender");
        String userEmail = request.getParameter("userEmail");
        String userPhone = request.getParameter("userPhone");
        String userHobbyStr = request.getParameter("userHobby");

        // 验证身份证号
        if (idNumber == null || idNumber.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "身份证号不能为空");
            return;
        }

        // 验证用户名
        if (userName == null || userName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "用户名不能为空");
            return;
        }
        if (isValidUserName(userName)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "用户名格式错误，只能包含英文字母、数字及下划线");
            return;
        }
        // 验证密码
        if (userPwd == null || userPwd.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "密码不能为空");
            return;
        }
//        if (isValidPassword(userPwd)) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "密码格式错误，至少包含大小写字母、数字和特殊字符中的两种以上组合");
//            return;
//        }
        // 验证确认密码
//        String repwd = request.getParameter("repwd");
//        if (!userPwd.equals(repwd)) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "两次输入的密码不一致");
//            return;
//        }

        // 验证邮箱
        if (isValidEmail(userEmail)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "邮箱格式输入错误，例如 xxxx@qq.com");
            return;
        }

        // 验证手机号
        if (isValidPhone(userPhone)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "号码格式输入错误");
            return;
        }

        // 验证生日
        if (isValidBirth(userBirth)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "请输入正确的生日格式，例如 1999-1-2 或 1999-01-02");
            return;
        }

        // 解析性别的中文描述
        // 默认值
        String genderDesc = "未知";
        if ("male".equals(userGender)) {
            genderDesc = "男";
        } else if ("female".equals(userGender)) {
            genderDesc = "女";
        }

        // 解析爱好字符串为List<String>，并转换为中文描述
        // 获取爱好值（假设复选框的value分别为"interest", "music", "trave", "code", "book", "dance")
        String[] userHobbyValues = request.getParameterValues("userHobby");
        List<String> hobbyList = getStrings(userHobbyValues);

        // 创建User对象
//        User user = new User(userName, userPwd, userBirth, userGender, userEmail, userPhone, Collections.singletonList(parseHobby(userHobbyStr).toString()));
        User user = new User(idNumber, userName, userPwd, userBirth, genderDesc, userEmail, userPhone, hobbyList);
        // 添加用户到UserDatabaseManager
        boolean success = userDbManager.addUser(user, request.getSession());
        // 根据添加结果进行反馈
        if (success) {
            // 添加成功，重定向到成功页面或其他操作
            response.sendRedirect("administrator_jsp/main.jsp?load=listUser.jsp");
        } else {
            // 添加失败，发送错误信息
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "用户添加失败");
        }
    }

    // 工具方法，用于验证用户名
    private boolean isValidUserName(String userName) {
        return !matchesPattern(userName, USER_PATTERN);
    }

    // 工具方法，用于验证密码
    private boolean isValidPassword(String userPwd) {
        return !matchesPattern(userPwd, PASSWORD_PATTERN);
    }

    // 工具方法，用于验证邮箱
    private boolean isValidEmail(String userEmail) {
        return !matchesPattern(userEmail, EMAIL_PATTERN);
    }

    // 工具方法，用于验证手机号
    private boolean isValidPhone(String userPhone) {
        return !matchesPattern(userPhone, PHONE_PATTERN);
    }

    // 工具方法，用于验证生日
    private boolean isValidBirth(String userBirth) {
        return !matchesPattern(userBirth, BIRTH_PATTERN);
    }

    // 工具方法，用于匹配字符串与正则表达式
    private boolean matchesPattern(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    // 解析爱好字符串为List<String>
//    private List<String> parseHobby(String hobbyStr) {
//        List<String> hobbyList = new ArrayList<>();
//        if (hobbyStr != null && !hobbyStr.isEmpty()) {
//            String[] hobbies = hobbyStr.split(",");
//            for (String hobby : hobbies) {
//                hobbyList.add(hobby.trim());
//            }
//        }
//        return hobbyList;
//    }
}
