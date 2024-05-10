package com.saic.cloud.fota;

import com.saic.cloud.fota.constants.ToolContants;
import com.saic.cloud.fota.model.*;
import com.saic.cloud.fota.service.TaskDescService;
import com.saic.cloud.fota.service.impl.TaskDescServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 任务描述符json生成controller
 */
@Slf4j
public class TaskDescJsonConfigController implements Initializable {
    /**
     * 标题
     */
    @FXML
    private TextField title_text;
    /**
     * 描述版本号
     */
    @FXML
    private TextField descriptorVersion_text;
    /**
     * 弹窗-电源模式-OFF
     */
    @FXML
    private CheckBox sysPwrMd_cb_up_off;
    /**
     * 弹窗-电源模式-ACC
     */
    @FXML
    private CheckBox sysPwrMd_cb_up_acc;
    /**
     * 弹窗-电源模式-ON
     */
    @FXML
    private CheckBox sysPwrMd_cb_up_on;
    /**
     * 弹窗-电源模式-CRANK
     */
    @FXML
    private CheckBox sysPwrMd_cb_up_crank;
    /**
     * 弹窗-档位-R
     */
    @FXML
    private CheckBox trShftLvrPos_cb_up_r;
    /**
     * 弹窗-档位-N
     */
    @FXML
    private CheckBox trShftLvrPos_cb_up_n;
    /**
     * 弹窗-档位-P
     */
    @FXML
    private CheckBox trShftLvrPos_cb_up_p;
    /**
     * 弹窗-档位-D
     */
    @FXML
    private CheckBox trShftLvrPos_cb_up_d;
    /**
     * 弹窗-车速
     */
    @FXML
    private TextField vehSpdAvgDrvn_text_up;
    /**
     * 前置-蓄电池能量等级
     */
    @FXML
    private TextField vehicleEnergyReadyLevel_text_pre;

    /**
     * 前置-电源模式-OFF
     */
    @FXML
    private CheckBox sysPwrMd_cb_pre_off;
    /**
     * 前置-电源模式-ACC
     */
    @FXML
    private CheckBox sysPwrMd_cb_pre_acc;
    /**
     * 前置-电源模式-ON
     */
    @FXML
    private CheckBox sysPwrMd_cb_pre_on;
    /**
     * 前置-电源模式-CRANK
     */
    @FXML
    private CheckBox sysPwrMd_cb_pre_crank;
    /**
     * 前置-档位-R
     */
    @FXML
    private CheckBox trShftLvrPos_cb_pre_r;
    /**
     * 前置-档位-N
     */
    @FXML
    private CheckBox trShftLvrPos_cb_pre_n;
    /**
     * 前置-档位-P
     */
    @FXML
    private CheckBox trShftLvrPos_cb_pre_p;
    /**
     * 前置-档位-D
     */
    @FXML
    private CheckBox trShftLvrPos_cb_pre_d;
    /**
     * 前置-热力总成就绪状态-Y
     */
    @FXML
    private RadioButton ePTRdy_rd_pre_y;
    /**
     * 前置-热力总成就绪状态-N
     */
    @FXML
    private RadioButton ePTRdy_rd_pre_n;
    /**
     * 前置-车速
     */
    @FXML
    private TextField vehSpdAvgDrvn_text_pre;
    /**
     * 前置-诊断仪接入-Y
     */
    @FXML
    private RadioButton extnlTstrDet_rd_pre_y;
    /**
     * 前置-诊断仪接入-N
     */
    @FXML
    private RadioButton extnlTstrDet_rd_pre_n;
    /**
     * 前置-动力电池电量
     */
    @FXML
    private TextField bMSPackSOCDsp_text_pre;
    /**
     * 前置-充电类型-快充
     */
    @FXML
    private CheckBox chargingState_cb_pre_kc;
    /**
     * 前置-充电类型-慢充
     */
    @FXML
    private CheckBox chargingState_cb_pre_mc;
    /**
     * 前置-充电类型-未充电
     */
    @FXML
    private CheckBox chargingState_cb_pre_wcd;
    /**
     * 前置-充电桩状态-私桩
     */
    @FXML
    private CheckBox chargingPileType_cb_pre_sz;
    /**
     * 前置-充电桩状态-公桩
     */
    @FXML
    private CheckBox chargingPileType_cb_pre_gz;
    /**
     * 前置-充电桩状态-未充电
     */
    @FXML
    private CheckBox chargingPileType_cb_pre_wcd;
    /**
     * 前置-远程启动状态-远程启动中
     */
    @FXML
    private RadioButton rVSSts_rd_pre_ycqdz;
    /**
     * 前置-远程启动状态-未远程启动
     */
    @FXML
    private RadioButton rVSSts_rd_pre_wycqd;
    /**
     * 前置-蓄电池电量
     */
    @FXML
    private TextField batSOC_text_pre;
    /**
     * 前置-智能场景-小憩模式
     */
    @FXML
    private RadioButton oneHitScene_rd_pre_xqms;
    /**
     * 前置-智能场景-宠物模式
     */
    @FXML
    private RadioButton oneHitScene_rd_pre_cwms;
    /**
     * 前置-智能场景-宿营模式
     */
    @FXML
    private RadioButton oneHitScene_rd_pre_syms;
    /**
     * 前置-智能场景-尊享模式
     */
    @FXML
    private RadioButton oneHitScene_rd_pre_zxms;
    /**
     * 前置-智能场景-洗车模式
     */
    @FXML
    private RadioButton oneHitScene_rd_pre_xcms;
    /**
     * 前置-智能场景-露营模式
     */
    @FXML
    private RadioButton oneHitScene_rd_pre_lyms;
    /**
     * 前置-充电场景-公桩快充
     */
    @FXML
    private RadioButton chargeScene_rd_pre_gzkc;
    /**
     * 前置-充电场景-公桩慢充
     */
    @FXML
    private RadioButton chargeScene_rd_pre_gzmc;
    /**
     * 前置-充电场景-无线充电
     */
    @FXML
    private RadioButton chargeScene_rd_pre_wxcd;
    /**
     * 前置-充电场景-不充电
     */
    @FXML
    private RadioButton chargeScene_rd_pre_bcd;
    /**
     * 前置-充电场景-放电
     */
    @FXML
    private RadioButton chargeScene_rd_pre_fd;
    /**
     * 前置-超级节能模式-超级节能模式启动中
     */
    @FXML
    private RadioButton drivingModeSta_rd_pre_cjjnmsqdz;
    /**
     * 前置-最大可用电量
     */
    @FXML
    private TextField battMaxEstCap_text_pre;
    /**
     * 前置-当前可用电量
     */
    @FXML
    private TextField battCurEstCap_text_pre;
    /**
     * 升级中-蓄电池能量等级
     */
    @FXML
    private TextField vehicleEnergyReadyLevel_text_reentry;

    /**
     * 升级中-电源模式-OFF
     */
    @FXML
    private CheckBox sysPwrMd_cb_reentry_off;
    /**
     * 升级中-电源模式-ACC
     */
    @FXML
    private CheckBox sysPwrMd_cb_reentry_acc;
    /**
     * 升级中-电源模式-ON
     */
    @FXML
    private CheckBox sysPwrMd_cb_reentry_on;
    /**
     * 升级中-电源模式-CRANK
     */
    @FXML
    private CheckBox sysPwrMd_cb_reentry_crank;
    /**
     * 升级中-档位-R
     */
    @FXML
    private CheckBox trShftLvrPos_cb_reentry_r;
    /**
     * 升级中-档位-N
     */
    @FXML
    private CheckBox trShftLvrPos_cb_reentry_n;
    /**
     * 升级中-档位-P
     */
    @FXML
    private CheckBox trShftLvrPos_cb_reentry_p;
    /**
     * 升级中-档位-D
     */
    @FXML
    private CheckBox trShftLvrPos_cb_reentry_d;
    /**
     * 升级中-热力总成就绪状态-Y
     */
    @FXML
    private RadioButton ePTRdy_rd_reentry_y;
    /**
     * 升级中-热力总成就绪状态-N
     */
    @FXML
    private RadioButton ePTRdy_rd_reentry_n;
    /**
     * 升级中-车速
     */
    @FXML
    private TextField vehSpdAvgDrvn_text_reentry;
    /**
     * 升级中-诊断仪接入-Y
     */
    @FXML
    private RadioButton extnlTstrDet_rd_reentry_y;
    /**
     * 升级中-诊断仪接入-N
     */
    @FXML
    private RadioButton extnlTstrDet_rd_reentry_n;
    /**
     * 升级中-动力电池电量
     */
    @FXML
    private TextField bMSPackSOCDsp_text_reentry;
    /**
     * 升级中-充电类型-快充
     */
    @FXML
    private CheckBox chargingState_cb_reentry_kc;
    /**
     * 升级中-充电类型-慢充
     */
    @FXML
    private CheckBox chargingState_cb_reentry_mc;
    /**
     * 升级中-充电类型-未充电
     */
    @FXML
    private CheckBox chargingState_cb_reentry_wcd;
    /**
     * 升级中-充电桩状态-私桩
     */
    @FXML
    private CheckBox chargingPileType_cb_reentry_sz;
    /**
     * 升级中-充电桩状态-公桩
     */
    @FXML
    private CheckBox chargingPileType_cb_reentry_gz;
    /**
     * 升级中-充电桩状态-未充电
     */
    @FXML
    private CheckBox chargingPileType_cb_reentry_wcd;
    /**
     * 升级中-远程启动状态-远程启动中
     */
    @FXML
    private RadioButton rVSSts_rd_reentry_ycqdz;
    /**
     * 升级中-远程启动状态-未远程启动
     */
    @FXML
    private RadioButton rVSSts_rd_reentry_wycqd;
    /**
     * 升级中-蓄电池电量
     */
    @FXML
    private TextField batSOC_text_reentry;
    /**
     * 升级中-智能场景-小憩模式
     */
    @FXML
    private RadioButton oneHitScene_rd_reentry_xqms;
    /**
     * 升级中-智能场景-宠物模式
     */
    @FXML
    private RadioButton oneHitScene_rd_reentry_cwms;
    /**
     * 升级中-智能场景-宿营模式
     */
    @FXML
    private RadioButton oneHitScene_rd_reentry_syms;
    /**
     * 升级中-智能场景-尊享模式
     */
    @FXML
    private RadioButton oneHitScene_rd_reentry_zxms;
    /**
     * 升级中-智能场景-洗车模式
     */
    @FXML
    private RadioButton oneHitScene_rd_reentry_xcms;
    /**
     * 升级中-智能场景-露营模式
     */
    @FXML
    private RadioButton oneHitScene_rd_reentry_lyms;
    /**
     * 升级中-充电场景-公桩快充
     */
    @FXML
    private RadioButton chargeScene_rd_reentry_gzkc;
    /**
     * 升级中-充电场景-公桩慢充
     */
    @FXML
    private RadioButton chargeScene_rd_reentry_gzmc;
    /**
     * 升级中-充电场景-无线充电
     */
    @FXML
    private RadioButton chargeScene_rd_reentry_wxcd;
    /**
     * 升级中-充电场景-不充电
     */
    @FXML
    private RadioButton chargeScene_rd_reentry_bcd;
    /**
     * 升级中-充电场景-放电
     */
    @FXML
    private RadioButton chargeScene_rd_reentry_fd;

    /**
     * 生成JSON按钮
     */
    @FXML
    private Button buildJson_btn;

    TaskDescService taskDescService = new TaskDescServiceImpl();

    /**
     * 初始化
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildJson_btn.setOnAction(event -> {

            List<InstallCondition> installReentryPreCheck = new ArrayList<>();
            if (StringUtils.isNotEmpty(vehicleEnergyReadyLevel_text_reentry.getText())) {
                installReentryPreCheck.add(buildInstallCondition(ToolContants.LTE, ToolContants.VEHICLE_ENERGY_READY_LEVEL, vehicleEnergyReadyLevel_text_reentry.getText()));
            }

            List<String> sysPwrMds = new ArrayList<>();
            if (sysPwrMd_cb_reentry_off.isSelected()) {
                sysPwrMds.add(sysPwrMd_cb_reentry_off.getText());
            }
            if (sysPwrMd_cb_reentry_acc.isSelected()) {
                sysPwrMds.add(sysPwrMd_cb_reentry_acc.getText());
            }
            if (sysPwrMd_cb_reentry_on.isSelected()) {
                sysPwrMds.add(sysPwrMd_cb_reentry_on.getText());
            }
            if (sysPwrMd_cb_reentry_crank.isSelected()) {
                sysPwrMds.add(sysPwrMd_cb_reentry_crank.getText());
            }
            if(!sysPwrMds.isEmpty()){
                installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.SYS_PWR_MD,StringUtils.join(sysPwrMds.toArray(),",")));
            }

            List<String> trShftLvrPoss = new ArrayList<>();
            if (trShftLvrPos_cb_reentry_r.isSelected()) {
                sysPwrMds.add(trShftLvrPos_cb_reentry_r.getText());
            }
            if (trShftLvrPos_cb_reentry_n.isSelected()) {
                sysPwrMds.add(trShftLvrPos_cb_reentry_n.getText());
            }
            if (trShftLvrPos_cb_reentry_p.isSelected()) {
                sysPwrMds.add(trShftLvrPos_cb_reentry_p.getText());
            }
            if (trShftLvrPos_cb_reentry_d.isSelected()) {
                sysPwrMds.add(trShftLvrPos_cb_reentry_d.getText());
            }
            if(!trShftLvrPoss.isEmpty()){
                installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.TR_SHFT_LVR_POS,StringUtils.join(trShftLvrPoss.toArray(),",")));
            }

            if (StringUtils.isNotEmpty(vehSpdAvgDrvn_text_reentry.getText())) {
                installReentryPreCheck.add(buildInstallCondition(ToolContants.LTE, ToolContants.VEH_SPD_AVG_DRVN, vehSpdAvgDrvn_text_reentry.getText()));
            }

            if (StringUtils.isNotEmpty(batSOC_text_reentry.getText())) {
                installReentryPreCheck.add(buildInstallCondition(ToolContants.GTE, ToolContants.BAT_SOC, batSOC_text_reentry.getText()));
            }

            if (StringUtils.isNotEmpty(bMSPackSOCDsp_text_reentry.getText())) {
                installReentryPreCheck.add(buildInstallCondition(ToolContants.GTE, ToolContants.BMS_PACK_SOC_DSP, bMSPackSOCDsp_text_reentry.getText()));
            }

            if (ePTRdy_rd_reentry_y.isSelected()) {
                installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EPT_RDY, ePTRdy_rd_reentry_y.getText()));
            }
            if (ePTRdy_rd_reentry_n.isSelected()) {
                installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EPT_RDY, ePTRdy_rd_reentry_n.getText()));
            }



            List<InstallCondition> installPreCheck = new ArrayList<>();

            List<InstallCondition> installPopupPolices = new ArrayList<>();

            List<EcuUpdInfo> ecuUpdInfo = new ArrayList<>();

            InstallationDescriptor installationDescriptor = buildInstallationDescriptor(installPopupPolices, installPreCheck, installReentryPreCheck, ecuUpdInfo);

            TaskDesc taskDesc = buildTaskDesc(installationDescriptor, descriptorVersion_text.getText());

            String buildJson = taskDescService.buildTaskDescJson(taskDesc);
            log.info("buildJson = {}", buildJson);

        });
    }

    private ModUpdPolicies buildModUpdPolicies(Integer resetGroup, Integer updTime, Integer upgSeq) {
        return ModUpdPolicies.builder()
                .resetGroup(resetGroup)
                .updTime(updTime)
                .upgSeq(upgSeq)
                .build();
    }

    private TargetEndModelInfo buildEcuUpdInfo(ModUpdPolicies modUpdPolicies, String backupType, String moduleId) {
        return TargetEndModelInfo.builder()
                .modUpdPolicies(modUpdPolicies)
                .backupType(backupType)
                .moduleId(moduleId)
                .build();
    }

    private EcuUpdInfo buildEcuUpdInfo(List<TargetEndModelInfo> targetEndModelInfos, String ecuName, String phyAddr) {
        return EcuUpdInfo.builder()
                .targetEndModelInfo(targetEndModelInfos)
                .ecuName(ecuName)
                .phyAddr(phyAddr)
                .build();
    }

    private InstallCondition buildInstallCondition(String op, String type, String value) {
        return InstallCondition.builder()
                .op(op)
                .type(type)
                .value(value)
                .build();
    }

    private InstallationDescriptor buildInstallationDescriptor(List<InstallCondition> installPopupPolices, List<InstallCondition> installPreCheck, List<InstallCondition> installReentryPreCheck, List<EcuUpdInfo> ecuUpdInfo) {
        return InstallationDescriptor.builder()
                .installPopupPolices(installPopupPolices)
                .installPreCheck(installPreCheck)
                .installReentryPreCheck(installReentryPreCheck)
                .build();
    }

    private TaskDesc buildTaskDesc(InstallationDescriptor installationDescriptor, String descriptorVersion) {
        return TaskDesc.builder()
                .installationDescriptor(installationDescriptor)
                .descriptorVersion(descriptorVersion)
                .build();
    }
}
