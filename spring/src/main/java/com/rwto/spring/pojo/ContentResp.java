package com.rwto.spring.pojo;

import lombok.Data;

/**
 * @author renmw
 * @create 2025/2/13 10:28
 **/
@Data
public class ContentResp {
    private int startIndex;
    private int pageSize;
    private String source;
    private String datetime;

}
