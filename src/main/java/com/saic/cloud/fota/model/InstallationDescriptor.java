package com.saic.cloud.fota.model;
import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @author chenchunrong
 */
@Data
@Builder
public class InstallationDescriptor {

    private List<InstallCondition> installPopupPolices;
    private List<InstallCondition> installPreCheck;
    private List<InstallCondition> installReentryPreCheck;
    private List<EcuUpdInfo> ecuUpdInfo;
}