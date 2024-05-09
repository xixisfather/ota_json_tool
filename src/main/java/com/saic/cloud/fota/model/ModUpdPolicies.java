/**
 * Copyright 2024 bejson.com
 */
package com.saic.cloud.fota.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author chenchunrong
 */
@Data
@Builder
public class ModUpdPolicies {

    private Integer resetGroup;
    private Integer updTime;
    private Integer upgSeq;
}