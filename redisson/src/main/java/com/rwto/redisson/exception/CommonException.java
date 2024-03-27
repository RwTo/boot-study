package com.rwto.redisson.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author renmw
 * @create 2024/3/27 15:28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommonException extends RuntimeException{
    private String errorNo;
    private String errorMsg;
}
