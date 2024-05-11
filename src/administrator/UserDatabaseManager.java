package administrator;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author aibizuan
 */
public class UserDatabaseManager {
    private List<User> users;

    public UserDatabaseManager() {
        users = new ArrayList<>();
    }

    public boolean addUser(User user, HttpSession session) {
        // 在这里添加用户到users列表，并将其存储到session中
        // 为了简单起见，这里假设用户信息只存储在session中
        // 添加同步锁以确保线程安全
        synchronized (users) {
            users.add(user);
        }
        session.setAttribute("users", users);
        return true;
    }

    public List<User> getAllUsers(HttpSession session) {
        // 如果session中没有用户列表，则创建一个新的列表
        if (session.getAttribute("users") == null) {
            session.setAttribute("users", new ArrayList<User>());
        }
        // 返回用户列表的副本以防止外部修改
        return new ArrayList<>((List<User>) session.getAttribute("users"));
    }

    // 如果需要从数据库获取用户信息，可以添加如下方法
    public User getUserByUsername(String username, HttpSession session) {
        List<User> users = getAllUsers(session);
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // 其他数据库交互方法...
}