package com.rwto.jdk.java8.pic;


import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @author renmw
 * @create 2024/4/22 9:32
 **/
@Service
public class ProduceImageService {

    private static String FONT_NAME = "微软雅黑";

    public void drawImage(PicWithParams picWithParams) {
        BufferedImage templateImage = readImageFromPath(picWithParams.getPicTemplate());
        BufferedImage newImage = drawImageWithParams(templateImage, picWithParams.getParamsList());
        storeImage(newImage);
    }

    private void storeImage(BufferedImage newImage) {
        File outputImageFile = new File("output.jpg"); // 输出图片文件路径
        try {
            ImageIO.write(newImage, "jpg", outputImageFile);
        } catch (IOException e) {
        }
        System.out.println("Generated image saved to: " + outputImageFile.getAbsolutePath());
    }

    private BufferedImage drawImageWithParams(BufferedImage sourceImage, List<PicParams> paramsList) {
        if(null == sourceImage){
            throw new IllegalArgumentException("参数异常");
        }
        Graphics2D g = sourceImage.createGraphics();
        try {
            for (PicParams param : paramsList) {
                g.setColor(param.getColor());
                g.setFont(param.getFont());
                FontMetrics metrics = g.getFontMetrics();
                switch (param.getTextAlign()){
                    case LEFT:
                        g.drawString(param.getContent(), param.getXPoint(), param.getYPoint());
                        break;
                    case RIGHT:
                        g.drawString(param.getContent(), param.getXPoint() - metrics.stringWidth(param.getContent()), param.getYPoint());
                        break;
                    case CENTER:
                    default:
                        g.drawString(param.getContent(), param.getXPoint() - metrics.stringWidth(param.getContent()) / 2, param.getYPoint());

                }
            }
        } finally {
            g.dispose();
        }
        return sourceImage;
    }

    private BufferedImage readImageFromUrl(String template) {
        try {
            URL url = new URL(template);
            return ImageIO.read(url);
        } catch (IOException e) {
        }
        return null;
    }

    private BufferedImage readImageFromPath(String template) {
        try(FileInputStream input = new FileInputStream(new File(template))) {
            return ImageIO.read(input);
        } catch (IOException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        ProduceImageService ImageService = new ProduceImageService();
        PicWithParams picWithParams = ImageService.getParams();
        ImageService.drawImage(picWithParams);
    }



    private PicWithParams getParams() {
        List<PicParams> picParams = new ArrayList<>();
        picParams.add(PicParams.builder()
                .content("张三")
                .xPoint(578)
                .yPoint(832)
                .color(Color.black)
                .textAlign(TextAlign.CENTER)
                .font(new Font("WenQuanYi Micro Hei",Font.PLAIN,58)).build());
        picParams.add(PicParams.builder()
                .content("353638199905022815")
                .xPoint(1300)
                .yPoint(832)
                .color(Color.black)
                .textAlign(TextAlign.CENTER)
                .font(new Font(FONT_NAME,Font.PLAIN,54)).build());
        picParams.add(PicParams.builder()
                .content("架构师认证")
                .xPoint(1000)
                .yPoint(900+53)
                .color(Color.black)
                .textAlign(TextAlign.CENTER)
                .font(new Font(FONT_NAME,Font.PLAIN,54)).build());
        picParams.add(PicParams.builder()
                .content("RJ12345678900001")
                .xPoint(410)
                .yPoint(1164+25)
                .color(Color.black)
                .textAlign(TextAlign.LEFT)
                .font(new Font(FONT_NAME,Font.PLAIN,33)).build());
        picParams.add(PicParams.builder()
                .content("2024年2月11日")
                .xPoint(1385)
                .yPoint(1164+30)
                .color(Color.black)
                .textAlign(TextAlign.LEFT)
                .font(new Font(FONT_NAME,Font.PLAIN,33)).build());

        return PicWithParams.builder().picTemplate("证书.jpg").paramsList(picParams).build();
    }
}
