import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author aibizuan
 */
@WebServlet("/randomCode")
public class CaptchaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应内容类型
        response.setContentType("image/png");
        System.out.println("doGet.........");
        // 禁止浏览器缓存图片
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        ServletOutputStream outputStream = response.getOutputStream();
//        在内存中创建一个图片对象
        int width = 80;
        int height = 30;
        //图片对象，画布
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//        画笔
        Graphics graphics = bufferedImage.getGraphics();
        Color backgroundColor = getRandomColor();
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(getOppsiteColor(backgroundColor));
        graphics.setFont(new Font("黑体", Font.BOLD, 26));
        String originString = "23456789qwertyupasdfghjkzxcvbnmQWERTYUOPASDFGHJKLZXCVBNM";
        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            randomCode.append(originString.charAt(random.nextInt(originString.length())));
        }
        //25 18-30
        graphics.drawString(randomCode.toString(), random.nextInt(26), 18 + random.nextInt(13));
        System.out.println("验证码： " + randomCode);
//        ServletContext上下文对象，最大的域对象，作用范围是整个应用，所有的用户所有的请求都可以共享，直到服务器重启
//        ServletContext servletContext1 = request.getServletContext();
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("randomCode", randomCode.toString());

        for (int i = 0; i < 10; i++) {
            graphics.setColor(getRandomColor());
            graphics.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
        }

        for (int i = 0; i < 10; i++) {
            graphics.setColor(getRandomColor());
            graphics.fillRect(random.nextInt(width), random.nextInt(height), 2, 2);
        }

        ImageIO.write(bufferedImage, "png", outputStream);
    }

    private Color getOppsiteColor(Color backgroundColor) {
        int red = 255 - backgroundColor.getRed();
        int green = 255 - backgroundColor.getGreen();
        int blue = 255 - backgroundColor.getBlue();
        return new Color(red, green, blue);
    }

    private Color getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        return new Color(red, green, blue);
    }
}
