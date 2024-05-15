package com.saic.cloud.fota.service;

import com.saic.cloud.fota.model.EcuUpdInfo;
import com.saic.cloud.fota.model.TaskDesc;

import java.util.List;

public interface TaskDescService {

    public String buildTaskDescJson(TaskDesc taskDesc);

    public List<EcuUpdInfo> buildEcuInfoListFromExcle(String filePath);
}
