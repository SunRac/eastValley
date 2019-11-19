package com.eastValley.javaConfig.mvc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author java_shj
 * @desc    contextLoaderListener应用上下文配置类
 * @createTime 2019/9/28 18:27
 **/
@Configuration
@ComponentScan(basePackages = {"com.eastValley"},
    excludeFilters = {@Filter(type=FilterType.ANNOTATION, value= EnableWebMvc.class)})
public class RootConfig {
}
