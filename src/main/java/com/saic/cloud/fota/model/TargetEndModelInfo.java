package com.saic.cloud.fota.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author chenchunrong
 */
@Data
@Builder
public class TargetEndModelInfo {

    private String backupType;
    private ModUpdPolicies modUpdPolicies;
    private String moduleId;
}