package cn.eastvalley.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author java_shj
 * @desc 生成登录页面验证码servlet
 * @createTime 2020/1/10 11:24
 **/
public class ValidateCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        图片宽度和高度
        int width = 109;
        int height = 35;
//        画板
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        画笔
        Graphics graphics = image.getGraphics();
        graphics.setColor(getRandomColor());
//        使用画笔填充一个矩形局域
        graphics.fillRect(0,0,width,height);
        int iteratorTime = 100;
//        随机在画板上画100个圆
        for(int i = 0; i < iteratorTime; i++) {
            graphics.setColor(getRandomColor());
            int rx = (int) (Math.random() * width);
            int ry = (int) (Math.random() * height);
            graphics.fillOval(rx,ry,2,2);
        }
//        随机在画板上画100条干扰线

        for(int i = 0; i < iteratorTime; i++) {
            graphics.setColor(getRandomColor());
            int x1 = (int) (Math.random() * width);
            int y1 = (int) (Math.random() * height);
            int x2 = (int) (Math.random() * width);
            int y2 = (int) (Math.random() * height);
            graphics.drawLine(x1,y1,x2,y2);
        }
         //画4个随机字符,为了保持分布均匀，所以有除以4
        int w = (width - 5*5)/4;
        StringBuilder code = new StringBuilder();
        graphics.setFont(new Font("Arial", Font.HANGING_BASELINE, 25));
        for(int i = 0; i < 4; i++) {
            String s = getWord();
            code.append(s);
            graphics.setColor(getRandomColor());
            graphics.drawString(s, 5+i*(w + 5), 25);
        }
        Cookie validateCodeCookie = new Cookie("vcode", code.toString());
        resp.addCookie(validateCodeCookie);
        //ImageIO
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        System.out.println("验证码：" + code);
        //关闭流
        outputStream.close();
    }

    /**
     * 随机获取颜色
     */
    private Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r,g,b);
    }

    /**
    * @desc 随机获取1个字符或者数字，用作验证码的字符
     */
    public  String getWord() {
        int i = 0;
        //只有位于48-57、大写字母65-90 小写字符97-122范围时，才能转换成数字或字母
        Random random = new Random();
        while(!(i >= 48 && i <= 57) || i >= 65 && i <= 90 || i >= 97 && i <= 122){
             i = random.nextInt(128);
        }
        char c = (char) i;
        return Character.toString(c);
    }
}
