package com.rwto.spring.pojo.req;

import lombok.Data;

/**
 * @author renmw
 * @create 2025/2/12 22:45
 **/
@Data
public class ContextReq {
    private int startIndex;
    private int pageSize;
    private String source;
}
