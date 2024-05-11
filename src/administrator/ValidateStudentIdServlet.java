package administrator;

///**
// * @author aibizuan
// */
//@WebServlet("/validateStudentId")
//public class ValidateStudentIdServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String studentId = request.getParameter("studentId");
//        ServletContext context = getServletContext();
//        DatabaseManager databaseManager = new DatabaseManager(context);
//        // 调用DatabaseManager或其他业务逻辑来验证学号
//        boolean isValid = databaseManager.isStudentIdUnique(studentId);
//        // 将验证结果返回给前端
//        response.setContentType("text/plain");
//        response.getWriter().write(String.valueOf(isValid));
//    }
//}