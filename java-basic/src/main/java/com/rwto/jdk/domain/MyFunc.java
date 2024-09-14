package com.rwto.jdk.domain;

import io.github.template.engine.func.AbstractTemplateFunc;
import io.github.template.engine.func.TemplateFunc;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author renmw
 * @create 2024/9/15 0:23
 **/
public class MyFunc implements TemplateFunc {

    @Override
    public String execute(Object... args) {

        return String.join("|", (String[]) args);
    }
}
