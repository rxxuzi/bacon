package com.bacon.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.bacon.mapper")
public class MyBatisConfig {
    // MyBatis configuration
    // The @MapperScan annotation will automatically scan for mapper interfaces
}