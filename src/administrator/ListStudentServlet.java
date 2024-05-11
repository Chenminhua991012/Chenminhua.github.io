package administrator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author aibizuan
 */
@WebServlet("/list")
public class ListStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // 获取当前 Servlet 上下文
        ServletContext servletContext = getServletContext();
        DatabaseManager databaseManager = new DatabaseManager(servletContext);
        ArrayList<Student> students = databaseManager.getAllStudents(session);

        request.setAttribute("students", students);
        request.getRequestDispatcher("listStudent.jsp").forward(request, response);
    }
}