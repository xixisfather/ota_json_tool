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
public class InstallCondition {

    private String op;
    private String type;
    private String value;
}