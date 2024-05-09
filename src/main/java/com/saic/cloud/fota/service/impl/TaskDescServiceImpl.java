package com.saic.cloud.fota.service.impl;

import com.alibaba.fastjson2.JSON;
import com.saic.cloud.fota.model.TaskDesc;
import com.saic.cloud.fota.service.TaskDescService;

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
}
