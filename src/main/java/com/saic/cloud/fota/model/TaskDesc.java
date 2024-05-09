package com.saic.cloud.fota.model;


import lombok.Builder;
import lombok.Data;

/**
 * @author chenchunrong
 */
@Data
@Builder
public class TaskDesc {

    private String descriptorVersion;
    private InstallationDescriptor installationDescriptor;
}