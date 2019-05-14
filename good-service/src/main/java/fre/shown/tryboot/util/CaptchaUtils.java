package fre.shown.tryboot.util;

import javax.imageio.ImageIO;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author Radon Freedom
 * created at 2019.05.14 19:22
 */

public class CaptchaUtils {

    private static final char[] OPS = new char[]{'+', '-', '*'};
    private static final ScriptEngineManager manager = new ScriptEngineManager();

    public static String createVerifyCode(HttpServletResponse response) throws ScriptException, IOException {
        int width = 80;
        int height = 32;
        //create the image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        // set the background color
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, width, height);
        // draw the border
        g.setColor(Color.black);
        g.drawRect(0, 0, width - 1, height - 1);
        // create a random instance to generate the codes
        Random rdm = new Random();
        // make some confusion
        for (int i = 0; i < 50; i++) {
            int x = rdm.nextInt(width);
            int y = rdm.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }
        // generate a random code
        String verifyCode = generateVerifyCode(rdm);
        g.setColor(new Color(0, 100, 0));
        g.setFont(new Font("Candara", Font.BOLD, 18));
        g.drawString(verifyCode, 8, 24);
        g.dispose();

        //输出图片
        OutputStream out = response.getOutputStream();
        ImageIO.write(image, "JPEG", out);
        out.flush();
        out.close();

        //计算结果并返回
        Integer result = (Integer) manager.getEngineByName("JavaScript").eval(verifyCode);
        return result.toString();
    }

    private static String generateVerifyCode(Random rdm) {
        int num1 = rdm.nextInt(10);
        int num2 = rdm.nextInt(10);
        int num3 = rdm.nextInt(10);
        char op1 = OPS[rdm.nextInt(3)];
        char op2 = OPS[rdm.nextInt(3)];
        return "" + num1 + op1 + num2 + op2 + num3;
    }
}
