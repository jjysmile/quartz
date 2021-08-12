package com.jjy.quartzjar.service;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/10 17:30
 * @Version: 1.0
 */

import com.jjy.quartzjar.entity.QuartzDTO;
import com.jjy.quartzjar.util.Result;

/**
 * @Description:    服务层接口
 * @Author JiaYun
 * @Date 2021/8/10 17:30
 * @Version V1.0
 **/
public interface IQuartzService {

    //分页查询定时任务
    Result selectPage(QuartzDTO quartzDTO,Integer pageNo,Integer pageSize);

    //新增删除定时任务
    Result save(QuartzDTO quartzDTO);

    //触发定时任务
    Result trigger(QuartzDTO quartzDTO);

    //暂停定时任务
    Result pause(QuartzDTO quartzDTO);

    //恢复定时任务
    Result resume(QuartzDTO quartzDTO);

    //删除定时任务
    Result remove(QuartzDTO quartzDTO);
}
