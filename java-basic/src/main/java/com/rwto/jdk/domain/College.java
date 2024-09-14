package com.rwto.jdk.domain;

import io.github.template.engine.annotation.TemplateModel;
import io.github.template.engine.annotation.TemplateParam;
import lombok.AllArgsConstructor;

/**
 * @author renmw
 * @create 2024/9/14 22:10
 **/
@TemplateModel("college")
@AllArgsConstructor
public class College {
    @TemplateParam("name")
    private String name;
    @TemplateParam("grade")
    private String grade;
}
