package com.saic.cloud.fota.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author: chenchunrong
 * @Date: 2024/5/8 4:10 PM
 * @description: TaskDescTemplate
 */
@Data
@Builder
public class TaskDescTemplate {
    private String title;
    private TaskDesc taskDesc;
}
