package administrator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author aibizuan
 */
@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取学生id作为查询参数
        String studentId = request.getParameter("studentId");

        ServletContext context = getServletContext();

        DatabaseManager databaseManager = new DatabaseManager(context);
        // 获取学生信息
        Student student = databaseManager.getStudentById(studentId);

        if (student != null) {
            // 将学生信息设置到请求属性
            request.setAttribute("student", student);
            // 设置响应内容类型
            response.setContentType("text/html;charset=UTF-8");
            // 调用 editStudentForm.jsp 并将其输出到响应中
            RequestDispatcher dispatcher = request.getRequestDispatcher("editStudentForm.jsp");
            dispatcher.forward(request, response);
        } else {
            // 学生不存在，处理错误情况
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "学生信息未找到");
        }
    }


//        if (student != null) {
//            // 将学生信息设置到请求属性
//            request.setAttribute("student", student);
////            request.getRequestDispatcher 只接受路径，而不接受查询字符串。查询字符串需要通过其他方式添加
////            request.getRequestDispatcher("main.jsp?load=editStudentForm.jsp&studentId=" + studentId).forward(request,response);
//            // 重定向到 main.jsp，并附加 load 参数
//            request.getRequestDispatcher("editStudentForm.jsp").forward(request, response);
//        } else {
//            // 学生不存在，处理错误情况
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "学生信息未找到");
//        }
//    }


    // 从ServletContext获取全局学生列表
//        ServletContext context = getServletContext();
//        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
//
//        // 检查students列表是否初始化
//        if (students == null) {
//            // 如果students为空，可以记录日志或处理异常情况
//            // log.error("students attribute not initialized in ServletContext");
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "学生列表未初始化");
//            return;
//        }
//
//        // 查找要编辑的学生
//        Student student = null;
//        for (Student s : students) {
//            if (s != null && studentId.equals(s.getStudentId())) {
//                student = s;
//                break;
//            }
//        }
//
//        // 如果找到了学生，可以进行编辑操作
//        if (student != null) {
//            // 这里可以添加编辑学生信息的逻辑
//            // 例如，可以转发到一个编辑表单页面
////            RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp?load=editStudentForm.jsp");
////            dispatcher.forward(request, response);
//            response.sendRedirect("main.jsp?load=editStudentForm.jsp");
//        } else {
//            // 如果学生不存在，可以发送错误消息或转到错误页面
//            response.sendError(HttpServletResponse.SC_NOT_FOUND, "学生信息未找到");
//        }
}
