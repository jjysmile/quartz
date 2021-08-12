package com.jjy.quartzjar.service.impl;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/10 17:40
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjy.quartzjar.entity.QuartzDTO;
import com.jjy.quartzjar.mapper.IQuartzMapper;
import com.jjy.quartzjar.service.IQuartzService;
import com.jjy.quartzjar.util.HttpStatus;
import com.jjy.quartzjar.util.Result;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:    服务层实现类
 * @Author JiaYun
 * @Date 2021/8/10 17:40
 * @Version V1.0
 **/
@Service
public class QuartzServiceImpl implements IQuartzService {

    @Autowired
    private IQuartzMapper quartzMapper;

    @Autowired
    private Scheduler scheduler;

    //分页查询
    @Override
    public Result selectPage(QuartzDTO quartzDTO, Integer pageNo, Integer pageSize) {
        Page<QuartzDTO> page = new Page<>(pageNo, pageSize);
        IPage<QuartzDTO> quartzDTOIPage = quartzMapper.selectPage(page, quartzDTO);
        return Result.response(HttpStatus.SUCCESS,quartzDTOIPage);
    }

    //新增和修改
    @Override
    public Result save(QuartzDTO quartzDTO) {

        try {
            if(quartzDTO.getOldJobName()!=null){
                JobKey key = new JobKey(quartzDTO.getOldJobName(), quartzDTO.getJobGroup());
                scheduler.deleteJob(key);
            }
            Class cls = Class.forName(quartzDTO.getJobClassName());

            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartzDTO.getJobName(), quartzDTO.getJobGroup())
                    .withDescription(quartzDTO.getDescription()).build();
            //job.getJobDataMap().put("jobMethodName",quartzDTO.getJobMethodName());

            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder
                    .cronSchedule(quartzDTO.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartzDTO.getJobName(), quartzDTO.getJobGroup())
                    .startNow()
                    .withSchedule(cronScheduleBuilder)
                    .build();

            scheduler.scheduleJob(job,trigger);


        } catch (Exception e) {
            e.printStackTrace();
            return Result.response(HttpStatus.ERROR,null);
        }

        return Result.response(HttpStatus.SUCCESS,null);
    }

    //触发
    @Override
    public Result trigger(QuartzDTO quartzDTO) {
        try {
            JobKey key = new JobKey(quartzDTO.getJobName(), quartzDTO.getJobGroup());
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.response(HttpStatus.ERROR,null);
        }
        return Result.response(HttpStatus.SUCCESS,null);
    }

    //暂停
    @Override
    public Result pause(QuartzDTO quartzDTO) {
        try {
            JobKey key = new JobKey(quartzDTO.getJobName(), quartzDTO.getJobGroup());
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.response(HttpStatus.ERROR,null);
        }
        return Result.response(HttpStatus.SUCCESS,null);
    }

    //恢复暂停
    @Override
    public Result resume(QuartzDTO quartzDTO) {
        try {
            JobKey key = new JobKey(quartzDTO.getJobName(), quartzDTO.getJobGroup());
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.response(HttpStatus.ERROR,null);
        }
        return Result.response(HttpStatus.SUCCESS,null);
    }

    //删除
    @Override
    public Result remove(QuartzDTO quartzDTO) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(quartzDTO.getJobName(), quartzDTO.getJobGroup());
            // 停止触发器
            scheduler.pauseTrigger(triggerKey);
            // 移除触发器
            scheduler.unscheduleJob(triggerKey);
            // 删除任务
            scheduler.deleteJob(JobKey.jobKey(quartzDTO.getJobName(), quartzDTO.getJobGroup()));
        } catch (SchedulerException e) {
            e.printStackTrace();
            return Result.response(HttpStatus.ERROR,null);
        }
        return Result.response(HttpStatus.SUCCESS,null);
    }
}
