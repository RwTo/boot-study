package com.rwto.arthas.domain.request;

import lombok.Data;

/**
 * @author renmw
 * @create 2024/8/22 11:24
 **/
@Data
public class UserReq {
    private String userName;
    private String account;
    private String sex;
    private String phone;
}
