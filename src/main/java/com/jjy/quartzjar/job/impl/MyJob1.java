package com.jjy.quartzjar.job.impl;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/12 11:10
 * @Version: 1.0
 */

import com.jjy.quartzjar.job.JobExecutor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @Description:    个性化定制定时任务，需要实现 JobExecutor 接口
 * @Author JiaYun
 * @Date 2021/8/12 10:21
 * @Version V1.0
 **/
public class MyJob1 implements JobExecutor {

    /**
     * @Author JiaYun
     * @Description     每个定时任务只有一个执行方法
     * @param	context
     * @Return void
     * @Date 2021/8/12 15:27
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("MyJob1 executing at "+new Date());
    }
}
