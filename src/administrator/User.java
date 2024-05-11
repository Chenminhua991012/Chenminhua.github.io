package administrator;

import java.util.List;

public class User {
    private String idNumber;
    private String username;
    private String password;
    private String birth;
    private String gender;
    private String email;
    private String phone;
    // 使用List来存储多选的爱好
    private List<String> hobby;

    // 构造函数
    public User(String idNumber, String username, String password, String birth, String gender, String email, String phone, List<String> hobby) {
        this.idNumber = idNumber;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.hobby = hobby;
    }

    // Getter和Setter方法
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    // 您也可以根据需要添加其他方法，例如验证方法、转换方法等
}