package administrator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @author aibizuan
 */
public class DatabaseManager {
    private ArrayList<Student> students;
    private ServletContext context;

    // 使用ServletContext中全局管理students列表,所有的添加、检索和更新操作都通过DatabaseManager类完成
    // 不在其他地方(如其他Servlet或JSP页面)直接操作HttpSession中的students属性
    public DatabaseManager(ServletContext context) {
        // 从 ServletContext获取students列表
        this.context = context;
        // 这里出现问题,不应该初始化创建新的数组列表,这会将之前的信息覆盖,而不会直接添加
        students = new ArrayList<>();
        // 在这里先从session中获取现有的students列表
        students = (ArrayList<Student>) context.getAttribute("students");
        if (students == null) {
            students = new ArrayList<>();
            // 存储到 ServletContext
            context.setAttribute("students", students);
        }
    }

    public boolean addStudent(String studentId, String studentName, int studentAge, float studentScore, String studentClassId, HttpSession session) throws ServletException {

        DatabaseManager databaseManager = new DatabaseManager(context);

        try {
            // 检查学号是否唯一
            if (!databaseManager.isStudentIdUnique(studentId)) {
                // 学号重复，抛出异常
                throw new ServletException("学号已存在，请使用其他学号!");
            }

            // 学号不重复，可以添加学生
            // 创建 Student 对象
            Student student = new Student(studentId, studentName, studentAge, studentScore, studentClassId, session);
//        // 从ServletContext(全局上下文)中获取班级映射,addStudent.jsp使用application是全局共享的,所以不能直接使用session来获取,需要使用ServletContext
//        LinkedHashMap<String, Clazz> classMap = (LinkedHashMap<String, Clazz>) context.getAttribute("classMap");
//        if (classMap == null) {
//            //  如果在ServletContext中找不到,则尝试从当前HttpSession(会话上下文)中获取classMap
//            classMap = (LinkedHashMap<String, Clazz>) session.getServletContext().getAttribute("classMap");
//            if (classMap == null) {
//                // 如果应用作用域中也没有，则创建并初始化它
//                classMap = new LinkedHashMap<>();
//                // 初始化班级映射,将班级标识符和班级名称添加到映射中
//                session.getServletContext().setAttribute("classMap", classMap);
//            }
//            // 存储到HttpSession中,存储到会话作用域以便下次使用,缓存classMap,避免在每次请求时重复创建
//            session.setAttribute("classMap", classMap);
//        }

//        这里使用session来获取classMap,因为我将addStudent.jsp使用的application改为session
//        LinkedHashMap<String, Clazz> classMap = (LinkedHashMap<String, Clazz>) session.getAttribute("classMap");
            LinkedHashMap<String, Clazz> classMap = (LinkedHashMap<String, Clazz>) context.getAttribute("classMap");
            if (classMap == null) {
                classMap = new LinkedHashMap<>();
                // 初始化班级映射...
                context.setAttribute("classMap", classMap);
            }

            // 现在classMap已经确保在会话中，可以继续之前的逻辑
            Clazz clazz = classMap.get(studentClassId);
            if (clazz != null) {
                // 设置班级名称
                student.setClassName(clazz.getClassName());
            } else {
                return false;
            }
//           不在addStudent方法中从 HttpSession 中获取students列表,并在不存在时创建一个新的ArrayList,使用ServletContext中全局管理students列表
//        // 在这里先从session中获取现有的students列表
//        students = (ArrayList<Student>) session.getAttribute("students");
//        // 如果不存在,创建一个新的ArrayList.然后将新的学生对象添加到这个列表中,并将更新后的列表重新存储到session中
//        if (students == null) {
//            students = new ArrayList<>();
//        }

            // 将新创建的学生对象添加到列表中
            students.add(student);
            // 将更新后的students列表存回会话
            session.setAttribute("students", students);
            return true;
        } catch (ServletException e) {
            // 记录日志
            log.println("Error adding student: " + e.getMessage());
            // 返回false
            return false;
        }
    }

    public ArrayList<Student> getAllStudents(HttpSession session) {
        // 如果 session 中没有学生列表，则创建一个新的列表
        if (session.getAttribute("students") == null) {
            session.setAttribute("students", new ArrayList<Student>());
        }
        return (ArrayList<Student>) session.getAttribute("students");
    }

    //        添加一个新的方法来根据ID获取学生信息
    public Student getStudentById(String studentId) {
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }


    // 旧方法,当使用重定向到editStudentForm.jsp以局部刷新方式展示到main.jsp后就不适合使用
//        因为在编辑过程中学号被更改,那么在更新操作中使用学号将无法找到对应的学生对象,从而导致更新失败
//    public boolean updateStudent(String studentId, String studentName, int studentAge, float studentScore, String studentClassId, HttpSession session) {
//
//
////        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
//
//        // 查找学生并更新信息
//        for (Student student : students) {
//            if (student.getStudentId().equals(studentId)) {
//                student.setName(studentName);
//                student.setAge(studentAge);
//                student.setScore(studentScore);
//                student.setClassId(studentClassId);
//                // 假设更新班级信息需要更新对应的班级对象
//                LinkedHashMap<String, Clazz> classMap = (LinkedHashMap<String, Clazz>) context.getAttribute("classMap");
//                Clazz clazz = classMap.get(studentClassId);
//                if (clazz != null) {
//                    student.setClassName(clazz.getClassName());
//                }
//                // 将更新后的学生列表重新存储到ServletContext
//                context.setAttribute("students", students);
//                // 更新成功
//                return true;
//            }
//        }
//        // 学生不存在或更新失败
//        return false;
//    }

    public boolean updateStudent(String originalStudentId, String newStudentId, String studentName, int studentAge, float studentScore, String studentClassId, HttpSession session) throws ServletException {
        // 获取ServletContext中的students列表和classMap映射
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
        LinkedHashMap<String, Clazz> classMap = (LinkedHashMap<String, Clazz>) context.getAttribute("classMap");

        // 查找学生并更新信息
        Student studentToBeUpdated = null;
        for (Student student : students) {
            if (student.getStudentId().equals(originalStudentId)) {
                studentToBeUpdated = student;
                break;
            }
        }
        if (studentToBeUpdated != null) {
            // 如果新学号与旧学号相同，则不需要更改学号
            if (newStudentId == null || newStudentId.isEmpty() || newStudentId.equals(originalStudentId)) {
                studentToBeUpdated.setName(studentName);
                studentToBeUpdated.setAge(studentAge);
                studentToBeUpdated.setScore(studentScore);

                // 更新班级信息
                Clazz newClazz = classMap.get(studentClassId);
                if (newClazz != null) {
                    studentToBeUpdated.setClassName(newClazz.getClassName());
                    studentToBeUpdated.setClassId(studentClassId);
                }
                // 保留原始学号,避免新学号为空时,将原始学号覆盖
                studentToBeUpdated.setStudentId(originalStudentId);

            } else {
                // 如果新学号与旧学号不同，则需要验证新学号的唯一性
                if (isStudentIdUnique(newStudentId)) {
                    // 新ID不重复，可以更新学生信息
                    studentToBeUpdated.setStudentId(newStudentId);
                    // 从旧ID的引用中移除学生
                    Iterator<Student> iterator = students.iterator();
                    while (iterator.hasNext()) {
                        Student currentStudent = iterator.next();
                        if (currentStudent.getStudentId().equals(originalStudentId)) {
                            iterator.remove();
                            break;
                        }
                    }

                    // 此时students列表中已经没有studentToBeUpdated了，不需要再次添加

                    // 重新存储更新后的 students 列表到ServletContext
                    context.setAttribute("students", students);

                    // 更新成功
                    return true;
                } else {
                    // 新ID已存在，抛出异常
                    throw new ServletException("学号重复，请为学生选择一个唯一的新学号");
                }
            }
            // 重新存储更新后的 students 列表到ServletContext
            context.setAttribute("students", students);

            // 更新成功
            return true;
        }
        // 学生不存在
        return false;
    }


    public boolean isStudentIdUnique(String studentId) {
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
        // 遍历学生列表，查找是否有重复的学号
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                // 如果找到重复的学号，返回 false
                return false;
            }
        }
        // 如果没有找到重复的学号，返回 true
        return true;
    }


    public boolean deleteStudent(String studentId, HttpSession session) {
        ArrayList<Student> students = (ArrayList<Student>) context.getAttribute("students");
        Iterator<Student> iterator = students.iterator();

        // 查找并删除学生
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getStudentId().equals(studentId)) {
                // 使用Iterator的remove方法安全删除
                iterator.remove();
                // 从HttpSession中删除学生信息
                session.removeAttribute(studentId);
                // 将更新后的学生列表重新存储到ServletContext
                context.setAttribute("students", students);
                // 删除成功
                return true;
            }
        }
        // 学生不存在或删除失败
        return false;
    }
}