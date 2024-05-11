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
@WebServlet("/updateStudent")
public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        String studentId = request.getParameter("studentId");
        // 原始学生ID
        String originalStudentId = request.getParameter("originalStudentId");
        // 新学生ID
//        String newStudentId = request.getParameter("newStudentId");
        // 新学生ID，如果为空或者不提供，则使用原始学号
        String newStudentId = request.getParameter("newStudentId") != null ? request.getParameter("newStudentId") : originalStudentId;

        if (newStudentId == null || newStudentId.trim().isEmpty()) {
            newStudentId = originalStudentId;
        }

        String studentName = request.getParameter("studentName");
        int studentAge = Integer.parseInt(request.getParameter("studentAge"));
        float studentScore = Float.parseFloat(request.getParameter("studentScore"));
        String studentClassId = request.getParameter("studentClass");

        // 获取ServletContext和HttpSession
        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        // 使用DatabaseManager进行学生信息更新
        DatabaseManager databaseManager = new DatabaseManager(context);
        try {
//        boolean updated = databaseManager.updateStudent(studentId, studentName, studentAge, studentScore, studentClassId, session);
            boolean updated = databaseManager.updateStudent(originalStudentId, newStudentId, studentName, studentAge, studentScore, studentClassId, session);

            if (updated) {
                // 更新成功，重定向到学生列表页面
                response.sendRedirect("main.jsp?load=listStudent.jsp");
            } else {
                // 更新失败，可以发送错误信息或重定向到错误页面
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "更新学生信息失败");
            }
        } catch (Exception e) {
            // 处理可能出现的异常
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}