package com.jiean.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zhangkang on 2020/6/19
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.jeian.vrcloud.mbg.mapper","com.jiean.dao"})
public class MyBatisConfig {
}
