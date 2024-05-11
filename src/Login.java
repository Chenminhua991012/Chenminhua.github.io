import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * @author aibizuan
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final String SUCCESS_PAGE = "/user_actions/success.jsp";
    private static final String FAILURE_PAGE = "/user_actions/fail.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String validateCode = request.getParameter("validateCode");

        ServletContext servletContext = getServletContext();
        String randomCode = (String) servletContext.getAttribute("randomCode");

        if (randomCode == null || !randomCode.equalsIgnoreCase(validateCode)) {
            request.setAttribute("errorMessage", "验证码输入错误，请重新输入！！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            try {
                Connection conn = getConnection();
                PreparedStatement pstmt = null;
                ResultSet resultSet = null;

                String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
//                实际应用中应该使用加密的密码进行比较
                pstmt.setString(2, password);

                resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    // 登录成功
                    System.out.println("登录成功！");
                    request.getSession().setAttribute("currentUser", username);
                    response.sendRedirect(request.getContextPath() + SUCCESS_PAGE);
                } else {
                    // 登录失败
                    request.setAttribute("errorMessage", "用户名或密码错误！");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

                pstmt.close();
                resultSet.close();
                conn.close();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
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
}