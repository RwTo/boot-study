package com.rwto.jdk.other.pic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author renmw
 * @create 2024/4/22 9:26
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PicWithParams {
    private String picTemplate;
    private List<PicParams> paramsList;
}
