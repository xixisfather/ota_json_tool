package com.saic.cloud.fota;

import com.alibaba.fastjson2.JSON;
import com.saic.cloud.fota.constants.ToolContants;
import com.saic.cloud.fota.model.*;
import com.saic.cloud.fota.service.TaskDescService;
import com.saic.cloud.fota.service.impl.TaskDescServiceImpl;
import com.saic.cloud.fota.util.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 任务描述符json生成controller
 */
@Slf4j
public class TaskDescJsonConfigController implements Initializable {
    /**
     * 版本号
     */
    @FXML
    private Label version_label;
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

    /**
     * +ECU
     */
    @FXML
    private Button add_ecu_btn;

    /**
     * ecu tabPane
     */
    @FXML
    private TabPane ecu_tp;

    /**
     * ecu tab
     */
    @FXML
    private Tab ecu_tab_1;

    /**
     * +ECU
     */
    @FXML
    private Button add_module_btn;

    /**
     * module tabPane
     */
    @FXML
    private TabPane module_tp;

    /**
     * ecu tab
     */
    @FXML
    private Tab module_tab_1;

    /**
     * error label
     */
    @FXML
    private Label error_label;

    /**
     * save draft btn
     */
    @FXML
    private Button saveDraft_btn;

    /**
     * import ecu btn
     */
    @FXML
    private Button import_ecu_btn;


    private TaskDescService taskDescService = new TaskDescServiceImpl();

    /**
     * 初始化
     *
     * @param location
     * @param resources
     */
    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        import_ecu_btn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择文件");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx", "*.xls"));
            File file = fileChooser.showOpenDialog(new Stage());
            try {
                List<EcuUpdInfo> ecuUpdInfos = taskDescService.buildEcuInfoListFromExcle(file.getAbsolutePath());
                if(ecu_tp.getTabs().size() > 1) {
                    ecu_tp.getTabs().remove(1, ecu_tp.getTabs().size());
                }
                if(module_tp.getTabs().size() > 1) {
                    module_tp.getTabs().remove(1, module_tp.getTabs().size());
                }
                loadEcuUpdInfo(ecuUpdInfos);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
                error_label.setText("导入文件错误");
            }
        });

        add_module_btn.setOnAction(event -> {
            addEcuModule();
        });

        add_ecu_btn.setOnAction(event -> {
            addEcu();
        });

        version_label.setText(ToolContants.TOOL_VERSION);
        String jarPath = TaskDescJsonConfigStage.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        File jarFile = new File(jarPath);
        String draftPath = jarFile.getParent() + "/" + ToolContants.TOOL_DRAFT_NAME;
        File draftTemplate = new File(draftPath);
        if (draftTemplate.exists()) {
            String initJson = org.apache.commons.io.FileUtils.readFileToString(draftTemplate, "UTF-8");
            loadDraftTemplate(initJson);
        }

        saveDraft_btn.setOnAction(event -> {
            String draftJson = JSON.toJSONString(buildTaskDescTemplate());
            File draftFile = new File(draftPath);
            if (draftFile.exists()) {
                draftFile.delete();
            }
            FileUtils.TextToFile(draftPath, draftJson);
        });

        buildJson_btn.setOnAction(event -> {
            if (StringUtils.isEmpty(title_text.getText())) {
                error_label.setText("请输入标题");
                return;
            }
            String buildJson = buildJson();
            log.info("buildJson = {}", buildJson);

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("另存为");
            File fileDirectory = directoryChooser.showDialog(new Stage());
            FileUtils.TextToFile(fileDirectory.getAbsolutePath() + "/" + title_text.getText() + ".json", buildJson);
            log.info("jsonFile = {}", fileDirectory.getAbsolutePath() + "/" + title_text.getText() + ".json");
            error_label.setText("生成json文件成功：" + fileDirectory.getAbsolutePath() + "/" + title_text.getText() + ".json");
            File draftFile = new File(draftPath);
            if (draftFile.exists()) {
                draftFile.delete();
            }
        });
    }

    private TaskDescTemplate buildTaskDescTemplate() {
        TaskDesc taskDesc = buildTaskDesc();
        return TaskDescTemplate.builder()
                .taskDesc(taskDesc)
                .title(title_text.getText())
                .build();
    }

    private void loadDraftTemplate(String initJson) {
        TaskDescTemplate taskDescTemplate = JSON.parseObject(initJson, TaskDescTemplate.class);
        title_text.setText(taskDescTemplate.getTitle());
        descriptorVersion_text.setText(taskDescTemplate.getTaskDesc().getDescriptorVersion());
        loadInstallPopupPolices(taskDescTemplate.getTaskDesc().getInstallationDescriptor().getInstallPopupPolices());
        loadInstallPreCheck(taskDescTemplate.getTaskDesc().getInstallationDescriptor().getInstallPreCheck());
        loadInstallReentryPreCheck(taskDescTemplate.getTaskDesc().getInstallationDescriptor().getInstallReentryPreCheck());
        loadEcuUpdInfo(taskDescTemplate.getTaskDesc().getInstallationDescriptor().getEcuUpdInfo());
    }

    private void loadEcuUpdInfo(List<EcuUpdInfo> ecuUpdInfos) {
        if (CollectionUtils.isNotEmpty(ecuUpdInfos)) {
            for (int i = 0; i < ecuUpdInfos.size(); i++) {
                if (i > 0) {
                    addEcu();
                }
                ecu_tp.getSelectionModel().select(i);
                EcuUpdInfo ecuUpdInfo = ecuUpdInfos.get(i);
                List<TargetEndModelInfo> targetEndModelInfos = ecuUpdInfo.getTargetEndModelInfo();
                if (CollectionUtils.isNotEmpty(targetEndModelInfos)) {
                    for (int j = 0; j < targetEndModelInfos.size(); j++) {
                        if (j > 0) {
                            addEcuModule();
                        }

                    }

                }
            }
            for (int i = 0; i < ecuUpdInfos.size(); i++) {
                EcuUpdInfo ecuUpdInfo = ecuUpdInfos.get(i);
                Tab tab = ecu_tp.getTabs().get(i);
                for (Node node : ((AnchorPane) (tab.contentProperty().getValue())).getChildren()) {
                    if (StringUtils.equals("ecuName_cm", node.getId())) {
                        ((ComboBox) node).setValue(ecuUpdInfo.getEcuName());
                    } else if (StringUtils.equals("phyAddr_text", node.getId())) {
                        ((TextField) node).setText(ecuUpdInfo.getPhyAddr());
                    } else if (StringUtils.equals("module_tp", node.getId())) {
                        List<TargetEndModelInfo> targetEndModelInfos = ecuUpdInfo.getTargetEndModelInfo();
                        int j = 0;
                        for (Tab moduleTab : ((TabPane) node).getTabs()) {
                            TargetEndModelInfo targetEndModelInfo = targetEndModelInfos.get(j);
                            for (Node moduleNode : ((AnchorPane) (moduleTab.contentProperty().getValue())).getChildren()) {
                                if (StringUtils.equals("moduleId_cm", moduleNode.getId())) {
                                    ((ComboBox) moduleNode).setValue(targetEndModelInfo.getModuleId());
                                } else if (StringUtils.equals("backupType_cm", moduleNode.getId())) {
                                    ((ComboBox) moduleNode).setValue(targetEndModelInfo.getBackupType());
                                } else if (StringUtils.equals("resetGroup_cm", moduleNode.getId())) {
                                    ((ComboBox) moduleNode).setValue(targetEndModelInfo.getModUpdPolicies().getResetGroup());
                                } else if (StringUtils.equals("updTime_text", moduleNode.getId())) {
                                    if (null != targetEndModelInfo.getModUpdPolicies().getUpdTime()) {
                                        ((TextField) moduleNode).setText(String.valueOf(targetEndModelInfo.getModUpdPolicies().getUpdTime()));

                                    }
                                } else if (StringUtils.equals("upgSeq_text", moduleNode.getId())) {
                                    if (null != targetEndModelInfo.getModUpdPolicies().getUpgSeq()) {
                                        ((TextField) moduleNode).setText(String.valueOf(targetEndModelInfo.getModUpdPolicies().getUpgSeq()));

                                    }
                                }
                            }
                            j++;
                        }
                    }
                }
            }
        }
    }

    private void loadInstallPreCheck(List<InstallCondition> installPreCheck) {
        if (CollectionUtils.isNotEmpty(installPreCheck)) {
            for (InstallCondition installCondition : installPreCheck) {
                if (StringUtils.equals(ToolContants.SYS_PWR_MD, installCondition.getType())) {
                    if (StringUtils.equals(sysPwrMd_cb_pre_off.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_pre_off.setSelected(true);
                    } else if (StringUtils.equals(sysPwrMd_cb_pre_acc.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_pre_acc.setSelected(true);
                    } else if (StringUtils.equals(sysPwrMd_cb_pre_on.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_pre_on.setSelected(true);
                    } else if (StringUtils.equals(sysPwrMd_cb_pre_crank.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_pre_crank.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.TR_SHFT_LVR_POS, installCondition.getType())) {
                    if (StringUtils.equals(trShftLvrPos_cb_pre_r.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_pre_r.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_pre_n.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_pre_n.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_pre_p.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_pre_p.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_pre_d.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_pre_d.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.VEH_SPD_AVG_DRVN, installCondition.getType())) {
                    vehSpdAvgDrvn_text_pre.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.VEHICLE_ENERGY_READY_LEVEL, installCondition.getType())) {
                    vehicleEnergyReadyLevel_text_pre.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.BAT_SOC, installCondition.getType())) {
                    batSOC_text_pre.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.BMS_PACK_SOC_DSP, installCondition.getType())) {
                    bMSPackSOCDsp_text_pre.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.EPT_RDY, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.EPT_RDY_READY, installCondition.getValue())) {
                        ePTRdy_rd_pre_y.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.EPT_RDY_NON_READY, installCondition.getValue())) {
                        ePTRdy_rd_pre_n.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.EXTNL_TSTR_DET, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.EXTNL_TSTR_DET_ON, installCondition.getValue())) {
                        extnlTstrDet_rd_pre_y.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.EXTNL_TSTR_DET_OFF, installCondition.getValue())) {
                        extnlTstrDet_rd_pre_n.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.CHARGING_STATE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.CHARGING_STATE_FAST_CHARGING, installCondition.getValue())) {
                        chargingState_cb_pre_kc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_STATE_SLOW_CHARGING, installCondition.getValue())) {
                        chargingState_cb_pre_mc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_STATE_NO_CHARGING, installCondition.getValue())) {
                        chargingState_cb_pre_wcd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE_PRIVATE, installCondition.getValue())) {
                        chargingPileType_cb_pre_sz.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE_PUBLIC, installCondition.getValue())) {
                        chargingPileType_cb_pre_gz.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE_NO_CONNECT, installCondition.getValue())) {
                        chargingPileType_cb_pre_wcd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.RVS_STS, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.RVS_STS_ON, installCondition.getValue())) {
                        rVSSts_rd_pre_ycqdz.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.RVS_STS_OFF, installCondition.getValue())) {
                        rVSSts_rd_pre_wycqd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000001, installCondition.getValue())) {
                        oneHitScene_rd_pre_xqms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000002, installCondition.getValue())) {
                        oneHitScene_rd_pre_cwms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000003, installCondition.getValue())) {
                        oneHitScene_rd_pre_syms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000004, installCondition.getValue())) {
                        oneHitScene_rd_pre_zxms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_0000000B, installCondition.getValue())) {
                        oneHitScene_rd_pre_xcms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_0000000C, installCondition.getValue())) {
                        oneHitScene_rd_pre_lyms.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.CHARGE_SCENE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.CHARGE_SCENE_PUBLIC_FAST_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_pre_gzkc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_PUBLIC_SLOW_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_pre_gzmc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_WIRELESS_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_pre_wxcd.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_NO_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_pre_bcd.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_DISCHARGE, installCondition.getValue())) {
                        chargeScene_rd_pre_fd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.DRIVING_MODE_STA, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.DRIVING_MODE_STA_ON, installCondition.getValue())) {
                        drivingModeSta_rd_pre_cjjnmsqdz.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.BATT_MAX_EST_CAP, installCondition.getType())) {
                    battMaxEstCap_text_pre.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.BATT_CUR_EST_CAP, installCondition.getType())) {
                    battCurEstCap_text_pre.setText(installCondition.getValue());
                }
            }
        }
    }

    private void loadInstallReentryPreCheck(List<InstallCondition> installReentryPreCheck) {
        if (CollectionUtils.isNotEmpty(installReentryPreCheck)) {
            for (InstallCondition installCondition : installReentryPreCheck) {
                if (StringUtils.equals(ToolContants.SYS_PWR_MD, installCondition.getType())) {
                    if (StringUtils.equals(sysPwrMd_cb_reentry_off.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_reentry_off.setSelected(true);
                    } else if (StringUtils.equals(sysPwrMd_cb_reentry_acc.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_reentry_acc.setSelected(true);
                    } else if (StringUtils.equals(sysPwrMd_cb_reentry_on.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_reentry_on.setSelected(true);
                    } else if (StringUtils.equals(sysPwrMd_cb_reentry_crank.getText(), installCondition.getValue())) {
                        sysPwrMd_cb_reentry_crank.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.TR_SHFT_LVR_POS, installCondition.getType())) {
                    if (StringUtils.equals(trShftLvrPos_cb_reentry_r.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_reentry_r.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_reentry_n.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_reentry_n.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_reentry_p.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_reentry_p.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_reentry_d.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_reentry_d.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.VEH_SPD_AVG_DRVN, installCondition.getType())) {
                    vehSpdAvgDrvn_text_reentry.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.VEHICLE_ENERGY_READY_LEVEL, installCondition.getType())) {
                    vehicleEnergyReadyLevel_text_reentry.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.BAT_SOC, installCondition.getType())) {
                    batSOC_text_reentry.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.BMS_PACK_SOC_DSP, installCondition.getType())) {
                    bMSPackSOCDsp_text_reentry.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.EPT_RDY, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.EPT_RDY_READY, installCondition.getValue())) {
                        ePTRdy_rd_reentry_y.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.EPT_RDY_NON_READY, installCondition.getValue())) {
                        ePTRdy_rd_reentry_n.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.EXTNL_TSTR_DET, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.EXTNL_TSTR_DET_ON, installCondition.getValue())) {
                        extnlTstrDet_rd_reentry_y.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.EXTNL_TSTR_DET_OFF, installCondition.getValue())) {
                        extnlTstrDet_rd_reentry_n.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.CHARGING_STATE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.CHARGING_STATE_FAST_CHARGING, installCondition.getValue())) {
                        chargingState_cb_reentry_kc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_STATE_SLOW_CHARGING, installCondition.getValue())) {
                        chargingState_cb_reentry_mc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_STATE_NO_CHARGING, installCondition.getValue())) {
                        chargingState_cb_reentry_wcd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE_PRIVATE, installCondition.getValue())) {
                        chargingPileType_cb_reentry_sz.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE_PUBLIC, installCondition.getValue())) {
                        chargingPileType_cb_reentry_gz.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGING_PILE_TYPE_NO_CONNECT, installCondition.getValue())) {
                        chargingPileType_cb_reentry_wcd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.RVS_STS, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.RVS_STS_ON, installCondition.getValue())) {
                        rVSSts_rd_reentry_ycqdz.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.RVS_STS_OFF, installCondition.getValue())) {
                        rVSSts_rd_reentry_wycqd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000001, installCondition.getValue())) {
                        oneHitScene_rd_reentry_xqms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000002, installCondition.getValue())) {
                        oneHitScene_rd_reentry_cwms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000003, installCondition.getValue())) {
                        oneHitScene_rd_reentry_syms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_00000004, installCondition.getValue())) {
                        oneHitScene_rd_reentry_zxms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_0000000B, installCondition.getValue())) {
                        oneHitScene_rd_reentry_xcms.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.ONE_HIT_SCENE_0000000C, installCondition.getValue())) {
                        oneHitScene_rd_reentry_lyms.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.CHARGE_SCENE, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.CHARGE_SCENE_PUBLIC_FAST_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_reentry_gzkc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_PUBLIC_SLOW_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_reentry_gzmc.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_WIRELESS_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_reentry_wxcd.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_NO_CHARGE, installCondition.getValue())) {
                        chargeScene_rd_reentry_bcd.setSelected(true);
                    } else if (StringUtils.equals(ToolContants.CHARGE_SCENE_DISCHARGE, installCondition.getValue())) {
                        chargeScene_rd_reentry_fd.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.DRIVING_MODE_STA, installCondition.getType())) {
                    if (StringUtils.equals(ToolContants.DRIVING_MODE_STA_ON, installCondition.getValue())) {
                        drivingModeSta_rd_pre_cjjnmsqdz.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.BATT_MAX_EST_CAP, installCondition.getType())) {
                    battMaxEstCap_text_pre.setText(installCondition.getValue());
                } else if (StringUtils.equals(ToolContants.BATT_CUR_EST_CAP, installCondition.getType())) {
                    battCurEstCap_text_pre.setText(installCondition.getValue());
                }
            }
        }
    }

    private void loadInstallPopupPolices(List<InstallCondition> installPopupPolices) {
        if (CollectionUtils.isNotEmpty(installPopupPolices)) {
            for (InstallCondition installCondition : installPopupPolices) {
                if (StringUtils.equals(ToolContants.SYS_PWR_MD, installCondition.getType())) {
                    for(String value : installCondition.getValue().split(",")) {
                        if (StringUtils.equals(sysPwrMd_cb_up_off.getText(), value)) {
                            sysPwrMd_cb_up_off.setSelected(true);
                        } else if (StringUtils.equals(sysPwrMd_cb_up_acc.getText(), value)) {
                            sysPwrMd_cb_up_acc.setSelected(true);
                        } else if (StringUtils.equals(sysPwrMd_cb_up_on.getText(), value)) {
                            sysPwrMd_cb_up_on.setSelected(true);
                        } else if (StringUtils.equals(sysPwrMd_cb_up_crank.getText(), value)) {
                            sysPwrMd_cb_up_crank.setSelected(true);
                        }
                    }

                } else if (StringUtils.equals(ToolContants.TR_SHFT_LVR_POS, installCondition.getType())) {
                    if (StringUtils.equals(trShftLvrPos_cb_up_r.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_up_r.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_up_n.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_up_n.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_up_p.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_up_p.setSelected(true);
                    } else if (StringUtils.equals(trShftLvrPos_cb_up_d.getText(), installCondition.getValue())) {
                        trShftLvrPos_cb_up_d.setSelected(true);
                    }
                } else if (StringUtils.equals(ToolContants.VEH_SPD_AVG_DRVN, installCondition.getType())) {
                    vehSpdAvgDrvn_text_up.setText(installCondition.getValue());
                }
            }
        }
    }

    private TaskDesc buildTaskDesc() {
        List<EcuUpdInfo> ecuUpdInfo = new ArrayList<>();
        for (Tab tab : ecu_tp.getTabs()) {
            EcuUpdInfo ei = EcuUpdInfo.builder().build();
            List<TargetEndModelInfo> targetEndModelInfo = new ArrayList<>();
            for (Node node : ((AnchorPane) (tab.contentProperty().getValue())).getChildren()) {
                if (node instanceof TabPane) {
                    for (Tab moduleTab : ((TabPane) node).getTabs()) {
                        ModUpdPolicies modUpdPolicies = ModUpdPolicies.builder()
                                .build();
                        TargetEndModelInfo tm = TargetEndModelInfo.builder()
                                .build();
                        for (Node mouduleNode : ((AnchorPane) (moduleTab.contentProperty().getValue())).getChildren()) {
                            if (StringUtils.equals(ToolContants.RESET_GROUP_FX_ID, mouduleNode.getId())) {
                                if (null != ((ComboBox) mouduleNode).getValue()) {
                                    Integer resetGroup = Integer.valueOf(((ComboBox) mouduleNode).getValue().toString());
                                    modUpdPolicies.setResetGroup(resetGroup);
                                }
                            } else if (StringUtils.equals(ToolContants.UPD_TIME_FX_ID, mouduleNode.getId())) {
                                if (StringUtils.isNotEmpty(((TextField) mouduleNode).getText())) {
                                    Integer updTime = Integer.valueOf(((TextField) mouduleNode).getText());
                                    modUpdPolicies.setUpdTime(updTime);
                                }
                            } else if (StringUtils.equals(ToolContants.UPG_SEQ_FX_ID, mouduleNode.getId())) {
                                if (StringUtils.isNotEmpty(((TextField) mouduleNode).getText())) {
                                    Integer upgSeq = Integer.valueOf(((TextField) mouduleNode).getText());
                                    modUpdPolicies.setUpgSeq(upgSeq);
                                }
                            }

                            if (StringUtils.equals(ToolContants.BACKUP_TYPE_FX_ID, mouduleNode.getId())) {
                                if (null != ((ComboBox) mouduleNode).getValue()) {
                                    String backupType = ((ComboBox) mouduleNode).getValue().toString();
                                    tm.setBackupType(backupType);
                                }
                            }
                            if (StringUtils.equals(ToolContants.MODULE_ID_FX_ID, mouduleNode.getId())) {
                                if (null != ((ComboBox) mouduleNode).getValue()) {
                                    String moduleId = ((ComboBox) mouduleNode).getValue().toString();
                                    tm.setModuleId(moduleId);
                                }
                            }
                        }
                        tm.setModUpdPolicies(modUpdPolicies);
                        targetEndModelInfo.add(tm);
                    }
                }

                if (StringUtils.equals(ToolContants.ECU_NAME_FX_ID, node.getId())) {
                    if (null != ((ComboBox) node).getValue()) {
                        String ecuName = ((ComboBox) node).getValue().toString();
                        ei.setEcuName(ecuName);
                    }
                }
                if (StringUtils.equals(ToolContants.PHY_ADDR_FX_ID, node.getId())) {
                    if (null != ((TextField) node).getText()) {
                        String phyAddr = ((TextField) node).getText();
                        ei.setPhyAddr(phyAddr);
                    }
                }

            }
            ei.setTargetEndModelInfo(targetEndModelInfo);
            ecuUpdInfo.add(ei);
        }
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
        if (!sysPwrMds.isEmpty()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.SYS_PWR_MD, StringUtils.join(sysPwrMds.toArray(), ",")));
        }

        List<String> trShftLvrPoss = new ArrayList<>();
        if (trShftLvrPos_cb_reentry_r.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_reentry_r.getText());
        }
        if (trShftLvrPos_cb_reentry_n.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_reentry_n.getText());
        }
        if (trShftLvrPos_cb_reentry_p.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_reentry_p.getText());
        }
        if (trShftLvrPos_cb_reentry_d.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_reentry_d.getText());
        }
        if (!trShftLvrPoss.isEmpty()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.TR_SHFT_LVR_POS, StringUtils.join(trShftLvrPoss.toArray(), ",")));
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
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EPT_RDY, ToolContants.EPT_RDY_READY));
        }
        if (ePTRdy_rd_reentry_n.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EPT_RDY, ToolContants.EPT_RDY_NON_READY));
        }

        if (extnlTstrDet_rd_reentry_y.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EXTNL_TSTR_DET, ToolContants.EXTNL_TSTR_DET_ON));
        }
        if (extnlTstrDet_rd_reentry_n.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EXTNL_TSTR_DET, ToolContants.EXTNL_TSTR_DET_OFF));
        }

        List<String> chargingStates = new ArrayList<>();
        if (chargingState_cb_reentry_kc.isSelected()) {
            chargingStates.add(ToolContants.CHARGING_STATE_FAST_CHARGING);
        }
        if (chargingState_cb_reentry_mc.isSelected()) {
            chargingStates.add(ToolContants.CHARGING_STATE_SLOW_CHARGING);
        }
        if (chargingState_cb_reentry_wcd.isSelected()) {
            chargingStates.add(ToolContants.CHARGING_STATE_NO_CHARGING);
        }
        if (!chargingStates.isEmpty()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.CHARGING_STATE, StringUtils.join(chargingStates.toArray(), ",")));
        }

        List<String> chargingPileTypes = new ArrayList<>();
        if (chargingPileType_cb_reentry_sz.isSelected()) {
            chargingPileTypes.add(ToolContants.CHARGING_PILE_TYPE_PRIVATE);
        }
        if (chargingPileType_cb_reentry_gz.isSelected()) {
            chargingPileTypes.add(ToolContants.CHARGING_PILE_TYPE_PUBLIC);
        }
        if (chargingPileType_cb_reentry_wcd.isSelected()) {
            chargingPileTypes.add(ToolContants.CHARGING_PILE_TYPE_NO_CONNECT);
        }
        if (!chargingPileTypes.isEmpty()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.CHARGING_PILE_TYPE, StringUtils.join(chargingPileTypes.toArray(), ",")));
        }

        if (rVSSts_rd_reentry_ycqdz.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.RVS_STS, ToolContants.RVS_STS_ON));
        }
        if (rVSSts_rd_reentry_wycqd.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.RVS_STS, ToolContants.RVS_STS_OFF));
        }

        if (oneHitScene_rd_reentry_xqms.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000001));
        }
        if (oneHitScene_rd_reentry_cwms.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000002));
        }
        if (oneHitScene_rd_reentry_syms.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000003));
        }
        if (oneHitScene_rd_reentry_zxms.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000004));
        }
        if (oneHitScene_rd_reentry_xcms.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_0000000B));
        }
        if (oneHitScene_rd_reentry_lyms.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_0000000C));
        }

        if (chargeScene_rd_reentry_gzkc.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_PUBLIC_FAST_CHARGE));
        }
        if (chargeScene_rd_reentry_gzmc.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_PUBLIC_SLOW_CHARGE));
        }
        if (chargeScene_rd_reentry_wxcd.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_WIRELESS_CHARGE));
        }
        if (chargeScene_rd_reentry_bcd.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_NO_CHARGE));
        }
        if (chargeScene_rd_reentry_fd.isSelected()) {
            installReentryPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_DISCHARGE));
        }


        List<InstallCondition> installPreCheck = new ArrayList<>();
        if (StringUtils.isNotEmpty(vehicleEnergyReadyLevel_text_pre.getText())) {
            installPreCheck.add(buildInstallCondition(ToolContants.LTE, ToolContants.VEHICLE_ENERGY_READY_LEVEL, vehicleEnergyReadyLevel_text_pre.getText()));
        }

        sysPwrMds = new ArrayList<>();
        if (sysPwrMd_cb_pre_off.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_pre_off.getText());
        }
        if (sysPwrMd_cb_pre_acc.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_pre_acc.getText());
        }
        if (sysPwrMd_cb_pre_on.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_pre_on.getText());
        }
        if (sysPwrMd_cb_pre_crank.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_pre_crank.getText());
        }
        if (!sysPwrMds.isEmpty()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.SYS_PWR_MD, StringUtils.join(sysPwrMds.toArray(), ",")));
        }

        trShftLvrPoss = new ArrayList<>();
        if (trShftLvrPos_cb_pre_r.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_pre_r.getText());
        }
        if (trShftLvrPos_cb_pre_n.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_pre_n.getText());
        }
        if (trShftLvrPos_cb_pre_p.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_pre_p.getText());
        }
        if (trShftLvrPos_cb_pre_d.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_pre_d.getText());
        }
        if (!trShftLvrPoss.isEmpty()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.TR_SHFT_LVR_POS, StringUtils.join(trShftLvrPoss.toArray(), ",")));
        }

        if (StringUtils.isNotEmpty(vehSpdAvgDrvn_text_pre.getText())) {
            installPreCheck.add(buildInstallCondition(ToolContants.LTE, ToolContants.VEH_SPD_AVG_DRVN, vehSpdAvgDrvn_text_pre.getText()));
        }

        if (StringUtils.isNotEmpty(batSOC_text_pre.getText())) {
            installPreCheck.add(buildInstallCondition(ToolContants.GTE, ToolContants.BAT_SOC, batSOC_text_pre.getText()));
        }

        if (StringUtils.isNotEmpty(bMSPackSOCDsp_text_pre.getText())) {
            installPreCheck.add(buildInstallCondition(ToolContants.GTE, ToolContants.BMS_PACK_SOC_DSP, bMSPackSOCDsp_text_pre.getText()));
        }

        if (ePTRdy_rd_pre_y.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EPT_RDY, ToolContants.EPT_RDY_READY));
        }
        if (ePTRdy_rd_pre_n.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EPT_RDY, ToolContants.EPT_RDY_NON_READY));
        }

        if (extnlTstrDet_rd_pre_y.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EXTNL_TSTR_DET, ToolContants.EXTNL_TSTR_DET_ON));
        }
        if (extnlTstrDet_rd_pre_n.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.EXTNL_TSTR_DET, ToolContants.EXTNL_TSTR_DET_OFF));
        }

        chargingStates = new ArrayList<>();
        if (chargingState_cb_pre_kc.isSelected()) {
            chargingStates.add(ToolContants.CHARGING_STATE_FAST_CHARGING);
        }
        if (chargingState_cb_pre_mc.isSelected()) {
            chargingStates.add(ToolContants.CHARGING_STATE_SLOW_CHARGING);
        }
        if (chargingState_cb_pre_wcd.isSelected()) {
            chargingStates.add(ToolContants.CHARGING_STATE_NO_CHARGING);
        }
        if (!chargingStates.isEmpty()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.CHARGING_STATE, StringUtils.join(chargingStates.toArray(), ",")));
        }

        chargingPileTypes = new ArrayList<>();
        if (chargingPileType_cb_pre_sz.isSelected()) {
            chargingPileTypes.add(ToolContants.CHARGING_PILE_TYPE_PRIVATE);
        }
        if (chargingPileType_cb_pre_gz.isSelected()) {
            chargingPileTypes.add(ToolContants.CHARGING_PILE_TYPE_PUBLIC);
        }
        if (chargingPileType_cb_pre_wcd.isSelected()) {
            chargingPileTypes.add(ToolContants.CHARGING_PILE_TYPE_NO_CONNECT);
        }
        if (!chargingPileTypes.isEmpty()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.CHARGING_PILE_TYPE, StringUtils.join(chargingPileTypes.toArray(), ",")));
        }

        if (rVSSts_rd_pre_ycqdz.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.RVS_STS, ToolContants.RVS_STS_ON));
        }
        if (rVSSts_rd_pre_wycqd.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.RVS_STS, ToolContants.RVS_STS_OFF));
        }

        if (oneHitScene_rd_pre_xqms.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000001));
        }
        if (oneHitScene_rd_pre_cwms.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000002));
        }
        if (oneHitScene_rd_pre_syms.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000003));
        }
        if (oneHitScene_rd_pre_zxms.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_00000004));
        }
        if (oneHitScene_rd_pre_xcms.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_0000000B));
        }
        if (oneHitScene_rd_pre_lyms.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.NEQ, ToolContants.ONE_HIT_SCENE, ToolContants.ONE_HIT_SCENE_0000000C));
        }

        if (chargeScene_rd_pre_gzkc.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_PUBLIC_FAST_CHARGE));
        }
        if (chargeScene_rd_pre_gzmc.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_PUBLIC_SLOW_CHARGE));
        }
        if (chargeScene_rd_pre_wxcd.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_WIRELESS_CHARGE));
        }
        if (chargeScene_rd_pre_bcd.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_NO_CHARGE));
        }
        if (chargeScene_rd_pre_fd.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.CHARGE_SCENE, ToolContants.CHARGE_SCENE_DISCHARGE));
        }

        if (drivingModeSta_rd_pre_cjjnmsqdz.isSelected()) {
            installPreCheck.add(buildInstallCondition(ToolContants.EQ, ToolContants.DRIVING_MODE_STA, ToolContants.DRIVING_MODE_STA_ON));
        }

        if (StringUtils.isNotEmpty(battMaxEstCap_text_pre.getText())) {
            installPreCheck.add(buildInstallCondition(ToolContants.GTE, ToolContants.BATT_MAX_EST_CAP, battMaxEstCap_text_pre.getText()));
        }

        if (StringUtils.isNotEmpty(battCurEstCap_text_pre.getText())) {
            installPreCheck.add(buildInstallCondition(ToolContants.GTE, ToolContants.BATT_CUR_EST_CAP, battCurEstCap_text_pre.getText()));
        }

        List<InstallCondition> installPopupPolices = new ArrayList<>();
        sysPwrMds = new ArrayList<>();
        if (sysPwrMd_cb_up_off.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_up_off.getText());
        }
        if (sysPwrMd_cb_up_acc.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_up_acc.getText());
        }
        if (sysPwrMd_cb_up_on.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_up_on.getText());
        }
        if (sysPwrMd_cb_up_crank.isSelected()) {
            sysPwrMds.add(sysPwrMd_cb_up_crank.getText());
        }
        if (!sysPwrMds.isEmpty()) {
            installPopupPolices.add(buildInstallCondition(ToolContants.NEQ, ToolContants.SYS_PWR_MD, StringUtils.join(sysPwrMds.toArray(), ",")));
        }

        trShftLvrPoss = new ArrayList<>();
        if (trShftLvrPos_cb_up_r.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_up_r.getText());
        }
        if (trShftLvrPos_cb_up_n.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_up_n.getText());
        }
        if (trShftLvrPos_cb_up_p.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_up_p.getText());
        }
        if (trShftLvrPos_cb_up_d.isSelected()) {
            trShftLvrPoss.add(trShftLvrPos_cb_up_d.getText());
        }
        if (!trShftLvrPoss.isEmpty()) {
            installPopupPolices.add(buildInstallCondition(ToolContants.EQ, ToolContants.TR_SHFT_LVR_POS, StringUtils.join(trShftLvrPoss.toArray(), ",")));
        }

        if (StringUtils.isNotEmpty(vehSpdAvgDrvn_text_up.getText())) {
            installPopupPolices.add(buildInstallCondition(ToolContants.LTE, ToolContants.VEH_SPD_AVG_DRVN, vehSpdAvgDrvn_text_up.getText()));
        }

        InstallationDescriptor installationDescriptor = buildInstallationDescriptor(installPopupPolices, installPreCheck, installReentryPreCheck, ecuUpdInfo);

        TaskDesc taskDesc = buildTaskDesc(installationDescriptor, descriptorVersion_text.getText());

        return taskDesc;
    }

    private String buildJson() {
        String json = taskDescService.buildTaskDescJson(buildTaskDesc());
        return json;
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
                .ecuUpdInfo(ecuUpdInfo)
                .build();
    }

    private TaskDesc buildTaskDesc(InstallationDescriptor installationDescriptor, String descriptorVersion) {
        return TaskDesc.builder()
                .installationDescriptor(installationDescriptor)
                .descriptorVersion(descriptorVersion)
                .build();
    }

    private void addEcuModule() {
        Tab newModuleTab = new Tab("module");
        Tab selected_ecu_tab = ecu_tp.getSelectionModel().getSelectedItem();
        TabPane selected_module_tab_pan = null;
        for (Node node : ((AnchorPane) (selected_ecu_tab.contentProperty().getValue())).getChildren()) {
            if (node instanceof TabPane) {
                selected_module_tab_pan = (TabPane) node;
                break;
            }
        }

        AnchorPane newModuleAnchorPange = new AnchorPane();
        for (Node moduleNode : ((AnchorPane) (selected_module_tab_pan.getTabs().get(0).contentProperty().getValue())).getChildren()) {
            Node newModuleNode = null;
            if (moduleNode instanceof TextField) {
                newModuleNode = new TextField();
                ((TextField) newModuleNode).setPromptText(((TextField) moduleNode).getPromptText());
            } else if (moduleNode instanceof Label) {
                newModuleNode = new Label();
                ((Label) newModuleNode).setText(((Label) moduleNode).getText());
                ((Label) newModuleNode).setTextFill(((Label) moduleNode).getTextFill());
            } else if (moduleNode instanceof ComboBox) {
                newModuleNode = new ComboBox<>();
                ((ComboBox) newModuleNode).setItems(((ComboBox) moduleNode).getItems());
            } else {
                continue;
            }
            ((Control) newModuleNode).setPrefWidth(((Control) moduleNode).getPrefWidth());
            ((Control) newModuleNode).setPrefHeight(((Control) moduleNode).getPrefHeight());
            newModuleNode.setId(moduleNode.getId());
            newModuleNode.setLayoutX(moduleNode.getLayoutX());
            newModuleNode.setLayoutY(moduleNode.getLayoutY());
            newModuleAnchorPange.getChildren().add(newModuleNode);
        }
        newModuleTab.setContent(newModuleAnchorPange);
        selected_module_tab_pan.getTabs().add(newModuleTab);
    }

    private void addEcu() {
        Tab newTab = new Tab("ecu");
        AnchorPane newAnchorPange = new AnchorPane();
        for (Node node : ((AnchorPane) (ecu_tab_1.contentProperty().getValue())).getChildren()) {
            Node newNode = null;
            if (node instanceof TextField) {
                newNode = new TextField();
                ((TextField) newNode).setPromptText(((TextField) node).getPromptText());
            } else if (node instanceof Label) {
                newNode = new Label();
                ((Label) newNode).setText(((Label) node).getText());
                ((Label) newNode).setTextFill(((Label) node).getTextFill());
            } else if (node instanceof ComboBox) {
                newNode = new ComboBox<>();
                ((ComboBox) newNode).setItems(((ComboBox) node).getItems());
            } else if (node instanceof Button) {
                newNode = new Button(((Button) node).getText());
                ((Button) newNode).setOnAction(((Button) node).getOnAction());
            } else if (node instanceof TabPane) {
                newNode = new TabPane();
                Tab newModuleTab = new Tab("module");
                newModuleTab.setClosable(false);
                AnchorPane newModuleAnchorPange = new AnchorPane();
                for (Node moduleNode : ((AnchorPane) (module_tab_1.contentProperty().getValue())).getChildren()) {
                    Node newModuleNode = null;
                    if (moduleNode instanceof TextField) {
                        newModuleNode = new TextField();
                        ((TextField) newModuleNode).setPromptText(((TextField) moduleNode).getPromptText());
                    } else if (moduleNode instanceof Label) {
                        newModuleNode = new Label();
                        ((Label) newModuleNode).setText(((Label) moduleNode).getText());
                        ((Label) newModuleNode).setTextFill(((Label) moduleNode).getTextFill());
                    } else if (moduleNode instanceof ComboBox) {
                        newModuleNode = new ComboBox<>();
                        ((ComboBox) newModuleNode).setItems(((ComboBox) moduleNode).getItems());
                    } else {
                        continue;
                    }
                    ((Control) newModuleNode).setPrefWidth(((Control) moduleNode).getPrefWidth());
                    ((Control) newModuleNode).setPrefHeight(((Control) moduleNode).getPrefHeight());
                    newModuleNode.setId(moduleNode.getId());
                    newModuleNode.setLayoutX(moduleNode.getLayoutX());
                    newModuleNode.setLayoutY(moduleNode.getLayoutY());
                    newModuleAnchorPange.getChildren().add(newModuleNode);
                }
                newModuleTab.setContent(newModuleAnchorPange);
                ((TabPane) newNode).getTabs().add(newModuleTab);
            } else {
                continue;
            }
            ((Control) newNode).setPrefWidth(((Control) node).getPrefWidth());
            ((Control) newNode).setPrefHeight(((Control) node).getPrefHeight());
            newNode.setId(node.getId());
            newNode.setLayoutX(node.getLayoutX());
            newNode.setLayoutY(node.getLayoutY());
            newAnchorPange.getChildren().add(newNode);

        }
        newTab.setContent(newAnchorPange);
        ecu_tp.getTabs().add(newTab);
    }
}
