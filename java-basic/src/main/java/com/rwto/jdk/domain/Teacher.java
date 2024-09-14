package com.rwto.jdk.domain;

import io.github.template.engine.annotation.TemplateModel;
import io.github.template.engine.annotation.TemplateParam;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renmw
 * @create 2024/9/14 22:16
 **/
@TemplateModel("teacher")
@AllArgsConstructor
public class Teacher {
    @TemplateParam("name")
    private String name;
    @TemplateParam("age")
    private Integer age;
    @TemplateParam("entryDate")
    private String entryTime;

    public static List<Teacher> getData(){
        List<Teacher> list = new ArrayList<>();
        list.add(new Teacher("wzz",44, "2011-09-01 12:00:26"));
        list.add(new Teacher("zgl",45, "2013-02-01 12:30:24"));
        list.add(new Teacher("jjw",37, "2024-04-01 15:10:13"));
        list.add(new Teacher("zxz",34, "2016-09-01 16:43:33"));
        return list;
    }
}
