package administrator;

import javax.servlet.http.HttpSession;

/**
 * @author aibizuan
 */
public class Student {
    // 学号作为学生的唯一标识ID
    private String studentId;
    private String name;
    private int age;
    private float score;
    private String classId;
    private String className;

    public Student(String studentId, String name, int age, float score, String classId, HttpSession session) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.classId = classId;
//        this.className = className;
        this.studentId = studentId;
    }

    public Student() {

    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}