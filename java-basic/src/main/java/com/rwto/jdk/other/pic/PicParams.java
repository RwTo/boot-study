package com.rwto.jdk.other.pic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

/**
 * @author renmw
 * @create 2024/4/22 9:27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PicParams {
    /*字体*/
    private Font font;

    /*画笔颜色*/
    private Color color;

    /*正文*/
    private String content;

    /*x坐标*/
    private Integer xPoint;

    /*y坐标*/
    private Integer yPoint;

    /*位置*/
    private TextAlign textAlign;


}
