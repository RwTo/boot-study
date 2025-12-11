package com.rwto.spring.pojo;

import lombok.Data;

/**
 * @author renmw
 * @create 2025/1/15 15:43
 **/
@Data
public class Result<T> {
    private T data;
    private String msg;
    private String result;

    public static <T> Result<T> success(T data){
        Result<T> res = new Result<>();
        res.setData(data);
        res.setMsg("success");
        res.setResult("ok");
        return res;
    }

    public static <T> Result<T> success(){
        Result<T> res = new Result<>();
        res.setMsg("success");
        res.setResult("ok");
        return res;
    }
}
