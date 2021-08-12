package com.jjy.quartzjar.entity;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/10 15:42
 * @Version: 1.0
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:    前端交互实体类
 * @Author JiaYun
 * @Date 2021/8/10 15:42
 * @Version V1.0
 **/
@Data
@ApiModel("定时DTO")
public class QuartzDTO implements Serializable {

    private static final long serialVersionUID = -4930013188652565101L;

    @ApiModelProperty(value = "任务名称",example = "任务名称测试1")
    private String jobName;//任务名称

    @ApiModelProperty(value = "任务分组",example = "任务分组测试1")
    private String jobGroup;//任务分组

    @ApiModelProperty(value = "任务描述",example = "描述测试1")
    private String description;//任务描述

    @ApiModelProperty(value = "执行类",example = "com.jjy.quartzjar.job.QuartzJob")
    private String jobClassName;//执行类

    //@ApiModelProperty(value = "执行方法",example = "test1")
    //private String jobMethodName;//执行方法

    @ApiModelProperty(value = "执行时间cron表达式",example = "0 * * * * ? ")
    private String cronExpression;//执行时间

    //private String triggerName;//触发器名称

    @ApiModelProperty(value = "任务状态",example = "")
    private String triggerState;//任务状态

    @ApiModelProperty(value = "任务名称",example = "任务名称测试1")
    private String oldJobName;//任务名称 用于修改
    @ApiModelProperty(value = "任务名称",example = "任务分组测试1")
    private String oldJobGroup;//任务分组 用于修改

}
