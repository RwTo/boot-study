package com.rwto.jdk.other.bean;

import com.rwto.jdk.other.pic.TextAlign;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author renmw
 * @create 2024/4/22 14:49
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParamTemplate {
    /*字体*/
    private TemplateFont font;

    /*画笔颜色*/
    private TemplateColor color;

    /*x坐标*/
    private Integer xPoint;

    /*y坐标*/
    private Integer yPoint;

    /*位置*/
    private TextAlign textAlign;
}
