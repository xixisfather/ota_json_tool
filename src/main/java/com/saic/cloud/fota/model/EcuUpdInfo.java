package com.saic.cloud.fota.model;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author chenchunrong
 */
@Data
@Builder
public class EcuUpdInfo {

    private String ecuName;
    private String phyAddr;
    private List<TargetEndModelInfo> targetEndModelInfo;
}