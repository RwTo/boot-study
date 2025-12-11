package com.rwto.spring.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author renmw
 * @create 2025/2/12 22:46
 **/
@Data
public class PageResp {
    List<ContentResp> results;
}
