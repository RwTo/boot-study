package com.rwto.jdk.other.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author renmw
 * @create 2024/4/22 14:45
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificateTemplate {
    private String template;
    private List<ParamTemplate> params;
    private String source;
}
