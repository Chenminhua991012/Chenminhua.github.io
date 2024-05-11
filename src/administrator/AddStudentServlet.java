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
@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取学号
        String studentId = request.getParameter("studentId");
        String studentName = request.getParameter("studentName");
        String studentAgeStr = request.getParameter("studentAge");
        String studentScoreStr = request.getParameter("studentScore");
        String studentClassId = request.getParameter("studentClass");

// 验证参数是否为空
        if (studentName == null || studentName.isEmpty() ||
                studentAgeStr == null || studentAgeStr.isEmpty() ||
                studentScoreStr == null || studentScoreStr.isEmpty() ||
                studentClassId == null || studentClassId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "您还有未填项");
            return;
        }

// 尝试解析年龄和分数
        try {
            int studentAge = Integer.parseInt(studentAgeStr);
            float studentScore = Float.parseFloat(studentScoreStr);

            Student student = new Student();
            // 将addStudent.jsp中select标签的name(studentClass)的classId传递给Student的setClassId然后返回
            student.setClassId(studentClassId);

            HttpSession session = request.getSession();

            // 创建 DatabaseManager 实例并传递 ServletContext
            ServletContext context = getServletContext();
            DatabaseManager databaseManager = new DatabaseManager(context);
            // 调用 DatabaseManager 的方法
            boolean success = databaseManager.addStudent(studentId, studentName, studentAge, studentScore, studentClassId, session);

//            DatabaseManager databaseManager = new DatabaseManager();
//            boolean success = databaseManager.addStudent(studentName, studentAge, studentScore, studentClassId, session);

            if (success) {
                // 重定向到 main.jsp 并传递一个参数
                response.sendRedirect("main.jsp?load=listStudent.jsp");
            } else {
                // 学号重复，发送具体的错误信息
                response.sendError(HttpServletResponse.SC_CONFLICT, "学号已存在，每个学生都需要一个唯一的学号");
            }
        } catch (NumberFormatException e) {
            // 如果解析年龄或分数时发生NumberFormatException异常
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "您的年龄或分数为无效值,请填写正确的数值");
        } catch (Exception e) {
            // 其他错误
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "添加学生信息失败：" + e.getMessage());
        }
    }
}