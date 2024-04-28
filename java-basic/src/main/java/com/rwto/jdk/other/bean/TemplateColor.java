package com.rwto.jdk.other.bean;

import lombok.Data;

import java.awt.*;

/**
 * @author renmw
 * @create 2024/4/22 17:19
 **/
@Data
public class TemplateColor {
    private int rgb;

    private volatile Color color;

    public Color getColor(){
        if(null == color){
            synchronized (this){
                if(null == color){
                    color = new Color(rgb);
                }
            }
        }
        return color;
    }
}
