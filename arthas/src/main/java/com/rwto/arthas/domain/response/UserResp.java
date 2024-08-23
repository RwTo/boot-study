package com.rwto.arthas.domain.response;

import lombok.Data;

/**
 * @author renmw
 * @create 2024/8/22 11:24
 **/
@Data
public class UserResp {
    private String id;
    private String userName;
    private String account;
    private String sex;
    private String phone;
}
