package com.rwto.spring.handler;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rwto.common.domain.req.BaseReq;
import com.rwto.common.domain.utils.FieldConvertUtils;
import com.rwto.common.domain.utils.StringUtils;
import com.rwto.spring.annotation.RequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author renmw
 * @create 2024/8/12 9:38
 **/
@Slf4j
public class RequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestModel.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        Class<?> parameterType = methodParameter.getParameterType();

        Map<String, Object> map = getAllParameter(methodParameter,nativeWebRequest);

        /*父.子*/
        if(BaseReq.class.isAssignableFrom(parameterType)){
            //fillBaseOpenInfo(map,nativeWebRequest);
        }

        if(!map.isEmpty()){
            ObjectMapper mapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            String json = mapper.writeValueAsString(map);
            return mapper.readValue(json, parameterType);
        }

        return parameterType.newInstance();
    }


    /**
     * 获取所有参数 request 或者body
     * @param methodParameter
     * @param nativeWebRequest
     * @return
     * @throws IOException
     */
    private Map<String, Object> getAllParameter(MethodParameter methodParameter, NativeWebRequest nativeWebRequest) throws IOException {
        Map<String, Object> res = new HashMap<>();
        Class<?> parameterType = methodParameter.getParameterType();
        Map<String,String[]> parameterMap = nativeWebRequest.getParameterMap();
        for (Map.Entry<String,String[]> entry : parameterMap.entrySet()){
            String[] arr = entry.getValue();
            if (arr != null && arr.length > 0){
                String fieldName = FieldConvertUtils.snake2Camel(entry.getKey());
                Field field = null;
                try {
                    field = parameterType.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e) {
                }
                if(null != field && field.getType() == List.class){
                    res.put(fieldName, Arrays.asList(arr[0].split(",")));
                }else{
                    res.put(fieldName,arr[0]);
                }
            }
        }

        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int len;
        while ((len = reader.read(buf)) != -1){
            sb.append(buf,0,len);
        }
        String str = sb.toString();
        if(!StringUtils.isEmpty(str)){
            try {
                String json = FieldConvertUtils.snake2Camel(str);
                Map<String,Object> map = JSONObject.parseObject(json, Map.class);
                res.putAll(map);
            } catch (Exception e) {
            }
        }

        return res;
    }
}
