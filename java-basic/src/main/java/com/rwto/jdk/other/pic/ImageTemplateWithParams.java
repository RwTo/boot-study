package com.rwto.jdk.other.pic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class ImageTemplateWithParams {

    public static void main(String[] args) throws IOException {
        String templateUrl = "https://uplusimg.hundsun.com/img/M00/01/FD/Cm99H2Yg1AGAUp2DAAOspAfQF-k357.jpg"; // 替换为实际图片模板URL
        String param1 = "任明稳1";
        String param2 = "372925199909062514";
        String param3 = "恒生注册（初级）";

        // 定义各参数所在的矩形区域及其居中对齐 x;中线位置
        Rectangle param1Area = new Rectangle(578, 778+54, 0, 0); // 替换为第一个参数区域的坐标和尺寸
        Rectangle param2Area = new Rectangle(1300, 778+54, 0, 0); // 替换为第二个参数区域的坐标和尺寸
        Rectangle param3Area = new Rectangle(1000, 53+915, 0, 0); // 替换为第三个参数区域的坐标和尺寸

        BufferedImage templateImage = readImageFromUrl(templateUrl);
        BufferedImage modifiedImage = drawParametersOnImage(templateImage, param1, param2, param3,
                param1Area, param2Area, param3Area);

        File outputImageFile = new File("output_image.jpg"); // 输出图片文件路径
        ImageIO.write(modifiedImage, "jpg", outputImageFile);
        System.out.println("Generated image saved to: " + outputImageFile.getAbsolutePath());
    }

    private static BufferedImage readImageFromUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        //BufferedImage image = ImageIO.read(url);
        BufferedImage image = ImageIO.read(new FileInputStream(new File("证书.jpg")));
        return image;
    }

    private static BufferedImage drawParametersOnImage(BufferedImage sourceImage,
                                                       String param1, String param2, String param3,
                                                       Rectangle param1Area,
                                                       Rectangle param2Area,
                                                       Rectangle param3Area) {
        System.out.println(sourceImage.getHeight());
        System.out.println(sourceImage.getWidth());
        System.out.println(sourceImage.getMinY());
        System.out.println(sourceImage.getMinX());
        Graphics2D g = sourceImage.createGraphics();
        //g.setFont(new Font("Arial", Font.PLAIN, 24)); // 更改字体、样式和大小
        g.setColor(Color.RED);
        g.setFont(new Font("微软雅黑",Font.PLAIN,58));

        drawTextCentered(g, param1, param1Area);
        drawTextCentered(g, param2, param2Area);
        drawTextCentered(g, param3, param3Area);

        g.dispose();

        return sourceImage;
    }

    private static void drawTextCentered(Graphics g, String text, Rectangle area) {
        FontMetrics metrics = g.getFontMetrics();
        System.out.println(metrics.getHeight());
        System.out.println(metrics.stringWidth(text));
        Rectangle2D bounds = metrics.getStringBounds(text,g);
        System.out.println(bounds.getWidth());
        System.out.println(bounds.getHeight());
        int x = area.x - (int) metrics.stringWidth(text) / 2;
        int y = area.y;

        g.drawString(text, x, y);


    }
}