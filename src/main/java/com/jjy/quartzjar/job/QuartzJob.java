package com.jjy.quartzjar.job;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/9 16:34
 * @Version: 1.0
 */

import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description:        启动定时任务
 * @Author JiaYun
 * @Date 2021/8/9 16:34
 * @Version V1.0
 **/

public class QuartzJob extends QuartzJobBean {
    //@Override
    //protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
    //    JobDetail jobDetail = context.getJobDetail();
    //    JobDataMap dataMap = jobDetail.getJobDataMap();
    //
    //
    //    Class<? extends Job> className = jobDetail.getJobClass();
    //    String methodName = dataMap.getString("jobMethodName");
    //
    //    Job job = null;
    //    try {
    //        job = className.newInstance();
    //        Method method = className.getMethod(methodName);
    //        method.invoke(job);
    //    } catch (InstantiationException e) {
    //        e.printStackTrace();
    //    } catch (IllegalAccessException e) {
    //        e.printStackTrace();
    //    } catch (NoSuchMethodException e) {
    //        e.printStackTrace();
    //    } catch (InvocationTargetException e) {
    //        e.printStackTrace();
    //    }
    //
    //}


    /**
     * @Author JiaYun
     * @Description     扫描数据库的定时任务并执行
     * @param	context
     * @Return void
     * @Date 2021/8/12 15:29
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();


        Class className = jobDetail.getJobClass();

        try {
            Object instance = className.newInstance();
            Method method = className.getMethod("execute");
            method.invoke(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
