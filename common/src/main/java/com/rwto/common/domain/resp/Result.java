package com.rwto.common.domain.resp;

import com.rwto.common.domain.enums.ErrNo;
import lombok.Data;

/**
 * @author renmw
 * @create 2024/9/6 11:23
 **/
@Data
public class Result<T> {
    private String errorNo;
    private String errorMsg;
    private T data;

    public static <T> Result<T> fail(String errorNo,String errorMsg){
        Result<T> result = new Result<>();
        result.setErrorMsg(errorMsg);
        result.setErrorNo(errorNo);
        return result;
    }

    public static <T> Result<T> fail(ErrNo errNo){
        return fail(errNo.getErrorNo(),errNo.getErrorMsg());
    }

    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setData(data);
        result.setErrorMsg(ErrNo.ERROR_SUCCESS.getErrorNo());
        result.setErrorNo(ErrNo.ERROR_SUCCESS.getErrorMsg());
        return result;
    }
}
