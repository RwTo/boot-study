package com.rwto.spring.pojo.req;

import lombok.Data;

/**
 * @author renmw
 * @create 2025/2/12 22:44
 **/
@Data
public class PageReq {
    private int startIndex;
    private int pageSize;
    private String source;
    private ContextReq context;
}
