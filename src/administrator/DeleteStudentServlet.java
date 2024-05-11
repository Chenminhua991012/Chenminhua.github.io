package administrator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author aibizuan
 */
@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentId = request.getParameter("studentId");

        // 获取ServletContext和HttpSession
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        // 使用DatabaseManager进行学生信息删除
        DatabaseManager databaseManager = new DatabaseManager(context);
        boolean deleted = databaseManager.deleteStudent(studentId, session);

        if (deleted) {
            // 删除成功，重定向到学生列表页面
            response.sendRedirect("main.jsp?load=listStudent.jsp");
        } else {
            // 删除失败，可以发送错误信息或重定向到错误页面
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "删除学生信息失败");
        }
    }
}