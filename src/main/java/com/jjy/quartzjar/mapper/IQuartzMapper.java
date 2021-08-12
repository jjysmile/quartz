package com.jjy.quartzjar.mapper;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/10 16:50
 * @Version: 1.0
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jjy.quartzjar.entity.QuartzDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Description:    查询mapper层
 * @Author JiaYun
 * @Date 2021/8/10 16:50
 * @Version V1.0
 **/
@Repository
@Mapper
public interface IQuartzMapper extends BaseMapper<QuartzDTO> {
    IPage<QuartzDTO> selectPage(Page<QuartzDTO> page,@Param("q") QuartzDTO quartzDTO);
}
