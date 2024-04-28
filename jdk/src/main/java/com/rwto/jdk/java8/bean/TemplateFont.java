package com.rwto.jdk.java8.bean;

import lombok.Data;

import java.awt.*;

/**
 * @author renmw
 * @create 2024/4/22 16:50
 **/
@Data
public class TemplateFont {
    private String name;
    private int style;
    private int size;

    private volatile Font font;

    public Font getFont(){
        if(null == font){
            synchronized (this){
                if(null == font){
                    font = new Font(name,style,size);
                }
            }
        }
        return font;
    }
}
