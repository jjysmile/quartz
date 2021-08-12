package com.jjy.quartzjar.controller;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/10 20:13
 * @Version: 1.0
 */

import com.jjy.quartzjar.entity.QuartzDTO;
import com.jjy.quartzjar.service.IQuartzService;
import com.jjy.quartzjar.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author JiaYun
 * @Date 2021/8/10 20:13
 * @Version V1.0
 **/
@RestController
@RequestMapping("/job")
@Api
public class JobController {

    @Autowired
    private IQuartzService quartzService;

    @ApiOperation("添加或修改一个定时任务")
    @PostMapping("/save")
    public Result save(@ApiParam("oldJobName和oldJobGroup若为null则是新增，否则视为修改") @RequestBody QuartzDTO quartzDTO){
        return quartzService.save(quartzDTO);
    }

    @ApiOperation("分页查询定时任务")
    @PostMapping("/page/{pageNo}/{pageSize}")
    public Result page(@ApiParam("jobName的模糊查询") @RequestBody QuartzDTO quartzDTO, @PathVariable Integer pageNo,@PathVariable Integer pageSize){
        return quartzService.selectPage(quartzDTO,pageNo,pageSize);
    }

    @ApiOperation("立即触发一个定时任务")
    @PostMapping("/trigger")
    public Result trigger(@ApiParam("传递jobName和jobGroup") @RequestBody QuartzDTO quartzDTO){
        return quartzService.trigger(quartzDTO);
    }

    @ApiOperation("暂停一个定时任务")
    @PostMapping("/pause")
    public Result pause(@ApiParam("传递jobName和jobGroup") @RequestBody QuartzDTO quartzDTO){
        return quartzService.pause(quartzDTO);
    }

    @ApiOperation("继续暂停的一个定时任务")
    @PostMapping("/resume")
    public Result resume(@ApiParam("传递jobName和jobGroup") @RequestBody QuartzDTO quartzDTO){
        return quartzService.resume(quartzDTO);
    }

    @ApiOperation("删除一个定时任务")
    @PostMapping("/remove")
    public Result remove(@ApiParam("传递jobName和jobGroup") @RequestBody QuartzDTO quartzDTO){
        return quartzService.remove(quartzDTO);
    }

}
