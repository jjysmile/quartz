package com.jjy.quartzjar.config;
/**
 * @ProjectName: learning
 * @Author: JiaYun
 * @Date: 2021/8/9 16:47
 * @Version: 1.0
 */

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @Description:
 * @Author JiaYun
 * @Date 2021/8/9 16:47
 * @Version V1.0
 **/
@Configuration
public class SchedulerConfig {

    @Autowired
    private DataSource dataSource;

    //DirectScheduleFactory:在代码定制schedule参数
    @Bean
    public Scheduler scheduler() throws IOException {
        return factoryBean().getScheduler();
    }

    @Bean
    public SchedulerFactoryBean factoryBean() throws IOException {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setSchedulerName("cluster_schedule");
        factoryBean.setDataSource(dataSource);
        //可有可无
        //factoryBean.setApplicationContextSchedulerContextKey("application");
        factoryBean.setQuartzProperties(quartzProperties());
        factoryBean.setTaskExecutor(schedulerThreadPool());
        factoryBean.setStartupDelay(10);
        return factoryBean;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));

        //afterPropertiesSet()是spring中的方法
        propertiesFactoryBean.afterPropertiesSet();

        return propertiesFactoryBean.getObject();
    }

    @Bean
    public Executor schedulerThreadPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程数，设为cpu核心数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());

        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
        executor.setQueueCapacity(Runtime.getRuntime().availableProcessors());

        return executor;
    }
}
