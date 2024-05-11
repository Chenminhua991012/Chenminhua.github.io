import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author aibizuan
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("UTF-8");

        System.out.println("doPost...regist...");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String birth = request.getParameter("birth");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
//        String hobby = request.getParameter("hobby");
        // 获取多个爱好的值
        String[] hobbies = request.getParameterValues("hobby");
        String validateCode = request.getParameter("validateCode");

        ServletContext servletContext = getServletContext();

        //BGU,重新部署后验页面证码未刷新,使用判断跳转回登录页面刷新
        String randomCode = (String) servletContext.getAttribute("randomCode");

        if (randomCode == null || !randomCode.equalsIgnoreCase(validateCode)) {
            request.setAttribute("errorMessage", "验证码输入错误，请重新输入！！");
            request.getRequestDispatcher("register.jsp").forward(request, response);
//            response.sendRedirect("register.jsp");
        } else {
            try {
                Connection conn = getConnection();
                PreparedStatement pstmt = null;

                String hobbyStr = getHobbyString(hobbies); // 调用方法获取爱好字符串

                // 解析性别的中文描述
                String genderDesc = "未知"; // 默认值
                if ("male".equals(gender)) {
                    genderDesc = "男";
                } else if ("female".equals(gender)) {
                    genderDesc = "女";
                }

                // 在控制台输出注册数据
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
                System.out.println("Gender: " + genderDesc);
                System.out.println("birth: " + birth);
                System.out.println("email: " + email);
                System.out.println("phone: " + phone);
//        System.out.println("Hobby: " + hobby);

                String sql = "INSERT INTO users (name, password, birth, gender, hobby, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                // 实际应用中应该存储加密后的密码
                pstmt.setString(2, password);
                pstmt.setString(3, birth);
                pstmt.setString(4, genderDesc);
                pstmt.setString(5, hobbyStr);
                pstmt.setString(6, email);
                pstmt.setString(7, phone);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    response.sendRedirect(request.getContextPath() + "/login.jsp");
                } else {
                    request.setAttribute("errorMessage", "注册失败，请重试！");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }

                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private Connection getConnection() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        // 添加数据库连接代码
        String url = "jdbc:mysql://localhost:3306/users_database";
        String user = "root";
        String password = "root";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getHobbyString(String[] userHobbyValues) {
        List<String> hobbyList = getStrings(userHobbyValues);
        return hobbyList.stream().collect(Collectors.joining(",")); // 使用Collectors.joining()生成以逗号分隔的字符串
    }

    private static List<String> getStrings(String[] userHobbyValues) {
        List<String> hobbyList = new ArrayList<>();
        if (userHobbyValues != null) {
            for (String hobbyValue : userHobbyValues) {
                switch (hobbyValue) {
                    case "interest":
                        hobbyList.add("运动");
                        break;
                    case "music":
                        hobbyList.add("音乐");
                        break;
                    case "travel":
                        hobbyList.add("旅行");
                        break;
                    case "code":
                        hobbyList.add("编程");
                        break;
                    case "book":
                        hobbyList.add("阅读");
                        break;
                    case "dance":
                        hobbyList.add("舞蹈");
                        break;
                    default:
                        hobbyList.add("未知爱好");
                        break;
                }
            }
        } else {
            hobbyList.add("无");
        }
        return hobbyList;
    }
}
