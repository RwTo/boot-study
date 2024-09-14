package com.rwto.jdk;

import com.rwto.jdk.domain.College;
import com.rwto.jdk.domain.MyFunc;
import com.rwto.jdk.domain.Student;
import com.rwto.jdk.domain.Teacher;
import io.github.template.engine.TemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author renmw
 * @create 2024/9/14 23:22
 **/
@SpringBootTest
public class TemplateEngineTest {
    public static void main(String[] args) {

    }


    @Test
    public void test(){

        String tmpl = "\n" +
                "当前时间：$Now(yyyy-MM-dd)\n" +
                "<br>\n" +
                "学院信息：${college.name}, 年级: ${college.grade}\n" +
                "\n" +
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"8\">\n" +
                "        <tr>\n" +
                "            <th colspan=\"4\">学生信息表</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <th>姓名</th>\n" +
                "            <th>年龄</th>\n" +
                "            <th>专业</th>\n" +
                "\t\t\t<th>信息</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>${student.name}</td>\n" +
                "            <td>${student.age}</td>\n" +
                "            <td>${student.subject}</td>\n" +
                "\t\t\t<td>$Hyperlink(http://xxx?id=${student.id},  ${student.name}的信息)</td>\n" +
                "        </tr>\n" +
                "</table>\n" +
                "\t\n" +
                "学院名称：${college.name}\n" +
                "\t\n" +
                "<table border=\"1\" cellspacing=\"0\" cellpadding=\"8\">\n" +
                "        <tr>\n" +
                "            <th colspan=\"3\">讲师信息表</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <th>姓名</th>\n" +
                "            <th>年龄</th>\n" +
                "\t\t\t<th>入职日期</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>${teacher.name}</td>\n" +
                "            <td>${teacher.age}</td>\n" +
                "\t\t\t<td>$DateTimeFormat(${teacher.entryDate},yy-MM-dd)</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>李四</td>\n" +
                "            <td>21</td>\n" +
                "\t\t\t<td>111</td>\n" +
                "        </tr>\n" +
                "    </table>" +
                "\t\n" +
                "学院名称：${college.name}\n" +
                "\t\n" ;

        List<Student> students = Student.getData();
        List<Teacher> teachers = Teacher.getData();
        College college = new College("计算机学院", "21级");

        TemplateEngine templateEngine = TemplateEngine.builder().build();
        String content = templateEngine.parseTemplate(tmpl)
                .convert(Arrays.asList(college, students, teachers));
        System.out.println(content);
    }


    @Test
    public void test01(){
        String tmpl = "asdf $MyFunc(1,2,3,4,asd,22)";
        TemplateEngine templateEngine = TemplateEngine.builder()
                .addFunction("MyFunc",new MyFunc()).build();
        String content = templateEngine.parseTemplate(tmpl)
                .convert(new ArrayList<>());
        System.out.println(content);
    }
}
