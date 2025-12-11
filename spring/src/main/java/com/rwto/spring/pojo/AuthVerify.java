package com.rwto.spring.pojo;

import lombok.Data;

/**
 * @author renmw
 * @create 2025/1/15 15:43
 **/
@Data
public class AuthVerify {
    private String uid;
    private String tokenId;
    private String access_key;
    private String validate;

    public AuthVerify(String uid, String tokenId, String access_key, String validate) {
        this.uid = uid;
        this.tokenId = tokenId;
        this.access_key = access_key;
        this.validate = validate;
    }
}
