package com.rwto.jdk.domain;

import io.github.template.engine.annotation.TemplateModel;
import io.github.template.engine.annotation.TemplateParam;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author renmw
 * @create 2024/9/14 22:07
 **/
@AllArgsConstructor
@TemplateModel("student")
public class Student {
    @TemplateParam("id")
    private Integer id;
    @TemplateParam("name")
    private String name;
    @TemplateParam("age")
    private Integer age;
    @TemplateParam("subject")
    private String subject;


    public static List<Student> getData(){
        List<Student> list = new ArrayList<>();
        list.add(new Student(1,"rmw",24,"计算机"));
        list.add(new Student(2,"wtt",25,"软件"));
        list.add(new Student(3,"llt",27,"计网"));
        list.add(new Student(4,"zze",34,"管理"));
        return list;
    }
}
