package cn.eastvalley.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author java_shj
 * @desc    contextLoaderListener应用上下文配置类
 * @createTime 2019/9/28 18:27
 **/
@Configuration
@ComponentScan(basePackages = {"cn.eastvalley.dao","cn.eastvalley.service"},
    excludeFilters = {@Filter(type=FilterType.ANNOTATION, value= EnableWebMvc.class)})
@PropertySource( name="contextConfiglocation",value = "classpath:spring/spring-all.xml")
public class RootConfig {
}
