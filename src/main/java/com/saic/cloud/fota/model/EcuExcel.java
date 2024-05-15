package com.saic.cloud.fota.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author: chenchunrong
 * @Date: 2024/5/15 2:59 PM
 * @description: EcuExcel
 */
@Data
public class EcuExcel {
    @ExcelProperty("ecuName")
    private String ecuName;
    @ExcelProperty("phyAddr")
    private String phyAddr;
    @ExcelProperty("module ID")
    private String moduleId;
    @ExcelProperty("backupType")
    private String backupType;
    @ExcelProperty("resetGroup")
    private Integer resetGroup;
    @ExcelProperty("updTime")
    private Integer updTime;
    @ExcelProperty("upgSeq")
    private Integer upgSeq;
}
