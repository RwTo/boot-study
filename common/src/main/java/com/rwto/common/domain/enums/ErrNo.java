package com.rwto.common.domain.enums;

/**
 * @author renmw
 * @create 2024/9/6 11:24
 **/
public enum ErrNo {
    ERROR_SUCCESS("0","success"),
    ERROR_INTERNAL("10001", "内部错误"),
    ;
    private String errorNo;
    private String errorMsg;

    ErrNo(String errorNo,String errorMsg){
        this.errorNo = errorNo;
        this.errorMsg = errorMsg;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
