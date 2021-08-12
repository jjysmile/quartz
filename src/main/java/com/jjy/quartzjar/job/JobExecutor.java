package com.jjy.quartzjar.job;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/11 17:17
 * @Version: 1.0
 */

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.PersistJobDataAfterExecution;


/**
 * @Description:    定时任务的接口
 * @Author JiaYun
 * @Date 2021/8/11
 * @Version V1.0
 **/
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public interface JobExecutor extends Job {

}
