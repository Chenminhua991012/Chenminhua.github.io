import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author aibizuan
 */
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        System.out.println("doPost...login...");

        // 使用通用方式获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String validateCode = request.getParameter("validateCode");
        //             获取PrintWriter对象
        PrintWriter out = response.getWriter();

        ServletContext servletContext = getServletContext();

        boolean lastVisitedTimeFound = false;
        //BGU,重新部署后验页面证码未刷新,使用判断跳转回登录页面刷新
        String randomCode = (String) servletContext.getAttribute("randomCode");

        if (randomCode == null || !randomCode.equalsIgnoreCase(validateCode)) {
            request.setAttribute("errorMessage", "验证码输入错误，请重新输入！！");
            request.getRequestDispatcher("login.jsp").forward(request, response);
//            response.sendRedirect("login.jsp");
        } else {
            // 检查用户名和密码
            if ("admin".equals(username) && "admin".equals(password)) {

                // 创建了一个SimpleDateFormat对象，用于定义日期和时间的显示格式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                // * `new Date()`：创建一个表示当前日期和时间的`Date`对象。
                // * `simpleDateFormat.format(...)`：使用先前定义的`simpleDateFormat`对象来格式化这个`Date`对象。
                // * `String nowTime = ...`：将格式化后的日期和时间字符串赋值给`nowTime`变量。
                String nowTime = simpleDateFormat.format(new Date());

                boolean flag = false;

                // 获取Cookie
                Cookie[] cookies = request.getCookies();

                response.addCookie(new Cookie("username", username));

                // 登录成功
                System.out.println("登录成功！");

                response.setContentType("text/html;charset=UTF-8");

                // 转发success.html
//            request.getRequestDispatcher("./user/success.html").forward(request,response);
                Cookie lastVisitedTimeCookie = null;

                long currentTimeMillis = System.currentTimeMillis();
                // 第一个循环：查找名为"lastVisitedTime"的Cookie
                for (Cookie cookie : cookies) {
                    String name = cookie.getName();
                    if ("lastVisitedTime".equals(name)) {
                        lastVisitedTimeFound = true;
                        lastVisitedTimeCookie = cookie;
                        break;  // 找到后退出循环
                    }
                }

                // 如果没有找到"lastVisitedTime" Cookie，则创建一个新的
                if (!lastVisitedTimeFound) {
                    Cookie newCookie = new Cookie("lastVisitedTime", URLEncoder.encode(simpleDateFormat.format(new Date()), "UTF-8"));
                    // 设置为会话级别
                    newCookie.setMaxAge(-1);
                    response.addCookie(newCookie);
                } else {
                    // 比较当前时间与最后访问时间
                    try {
                        Date lastVisitedDate = simpleDateFormat.parse(URLDecoder.decode(lastVisitedTimeCookie.getValue(), "UTF-8"));
                        long lastVisitedTimeMillis = lastVisitedDate.getTime();
                        long timeDifferenceMillis = currentTimeMillis - lastVisitedTimeMillis;

                        // 如果时间差超过5分钟，则提示用户登录超时
                        if (timeDifferenceMillis > 5 * 60 * 1000) {
                            out.write("登录超时，请重新<a href='index.html'>登录</a>！");
//                            response.sendRedirect(request.getContextPath() + "/index.html");
                        } else {
                            // 如果时间差未超过5分钟，则用户有权限访问首页

//                            response.sendRedirect(request.getContextPath() + "/META-INF/user.html");
                        }
                        return; // 退出方法，因为我们已经进行了重定向
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }

                // 如果没有进行重定向（即时间差未超过5分钟），则继续后续逻辑
                // 这里可以添加更新lastVisitedTime Cookie的逻辑
                Cookie updatedLastVisitedTimeCookie = new Cookie("lastVisitedTime", URLEncoder.encode(nowTime, "UTF-8"));

                updatedLastVisitedTimeCookie.setMaxAge(-1);
                response.addCookie(updatedLastVisitedTimeCookie);

                // 第二个循环：处理找到的"lastVisitedTime" Cookie
                if (lastVisitedTimeCookie != null) {
                    // 设置flag为true，表示找到了"lastVisitedTime" Cookie
                    flag = true;
                    // 输出欢迎信息和其他内容
                    out.write("<html>");
                    out.write("欢迎回来，您上次访问时间为：" + URLDecoder.decode(lastVisitedTimeCookie.getValue(), "UTF-8"));
                    out.write("<br/><a href='logout'>退出登录</a><br/>\n");
                    out.write("</html>");

                    // 更新"lastVisitedTime" Cookie的值并设置过期时间
                    lastVisitedTimeCookie.setValue(URLEncoder.encode(nowTime, "UTF-8"));

                    // 将更新后的Cookie添加到响应中
                    HttpSession session = request.getSession();
                    request.setAttribute("username", username);
                    // 如果想要立即删除Cookie，可以将过期时间设置为0
                    lastVisitedTimeCookie.setMaxAge(0);
                    response.addCookie(lastVisitedTimeCookie);
                }
                if (!flag) {
                    // 首次访问
                    out.write("<html>");
                    out.write("<h1>您好，欢迎您首次访问！</h1>\n");
                    out.write("未登录超时，可直接访问系统首页");
                    out.write("<h1>欢迎，" + username + "！登录成功！</h1><br/>\n");
                    out.write("<button onclick=\"window.location.href='./user/user.html'\">跳转首页</button><br/>\n");
                    // 创建并添加一个新的Cookie来记录首次访问时间
                    Cookie newCookie = new Cookie("lastVisitedTime", URLEncoder.encode(nowTime, "UTF-8"));
                    out.write("<br/><a href='logout'>退出登录</a>\n");
                    out.write("</html>");
                    HttpSession session = request.getSession();
                    request.setAttribute("username", username);
                    // 设置Cookie的过期时间，例如设置为会话级别（浏览器关闭时过期）
                    newCookie.setMaxAge(-1);
                    response.addCookie(newCookie);
                    // 转发到SuccessServlet
//            request.getRequestDispatcher("SuccessServlet").forward(request, response);
                }
            } else {
                response.setContentType("text/html;charset=UTF-8");
                // 登录失败
                System.out.println("输入错误，登录失败！");

                // 登录失败
//                response.sendRedirect("./user/fail.html");

                out.write("<html>");
                out.write("<h1>账号:" + username + "输入错误！</h1>\n");
//                    out.write("<button onclick=\"window.location.href='index.html'\">返回</button>");
                out.write("<button onclick='history.back()'>返回</button>");
                out.write("</html>");
                // 转发到FailServlet
//            request.getRequestDispatcher("FailServlet").forward(request, response);
            }
        }
    }
}
