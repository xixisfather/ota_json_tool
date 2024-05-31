package com.saic.cloud.fota.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson2.JSON;
import com.saic.cloud.fota.model.*;
import com.saic.cloud.fota.service.TaskDescService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenchunrong
 * @Date: 2024/5/8 10:50 AM
 * @description: TaskDescServiceImpl
 */
public class TaskDescServiceImpl implements TaskDescService {
    @Override
    public String buildTaskDescJson(TaskDesc taskDesc) {
        return JSON.toJSONString(taskDesc);
    }

    @Override
    public List<EcuUpdInfo> buildEcuInfoListFromExcle(String filePath) {
        List<EcuExcel> ecus = new ArrayList<>();
        EasyExcel.read(filePath, EcuExcel.class, new AnalysisEventListener<EcuExcel>() {

            @Override
            public void invoke(EcuExcel ecuExcel, AnalysisContext analysisContext) {
                ecus.add(ecuExcel);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        List<EcuUpdInfo> ecuUpdInfos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ecus)) {
            for (EcuExcel ecuExcel : ecus) {
                if (StringUtils.isNotEmpty(ecuExcel.getEcuName())) {
                    ModUpdPolicies modUpdPolicies = ModUpdPolicies
                            .builder()
                            .resetGroup(ecuExcel.getResetGroup())
                            .updTime(ecuExcel.getUpdTime())
                            .upgSeq(ecuExcel.getUpgSeq())
                            .build();
                    TargetEndModelInfo tm = TargetEndModelInfo
                            .builder()
                            .moduleId(ecuExcel.getModuleId())
                            .backupType(ecuExcel.getBackupType())
                            .modUpdPolicies(modUpdPolicies)
                            .build();
                    List<TargetEndModelInfo> targetEndModelInfo = new ArrayList<>();
                    targetEndModelInfo.add(tm);
                    EcuUpdInfo ecuUpdInfo = EcuUpdInfo
                            .builder()
                            .ecuName(ecuExcel.getEcuName())
                            .phyAddr(ecuExcel.getPhyAddr())
                            .targetEndModelInfo(targetEndModelInfo)
                            .build();
                    ecuUpdInfos.add(ecuUpdInfo);
                } else {
                    EcuUpdInfo ecuUpdInfo = ecuUpdInfos.get(ecuUpdInfos.size() - 1);
                    ModUpdPolicies modUpdPolicies = ModUpdPolicies
                            .builder()
                            .resetGroup(ecuExcel.getResetGroup())
                            .updTime(ecuExcel.getUpdTime())
                            .upgSeq(ecuExcel.getUpgSeq())
                            .build();
                    TargetEndModelInfo tm = TargetEndModelInfo
                            .builder()
                            .moduleId(ecuExcel.getModuleId())
                            .backupType(ecuExcel.getBackupType())
                            .modUpdPolicies(modUpdPolicies)
                            .build();
                    ecuUpdInfo.getTargetEndModelInfo().add(tm);
                }

            }
        }
        return ecuUpdInfos;
    }
}
