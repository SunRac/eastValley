package cn.eastvalley.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.util.Properties;

/**
 * @author java_shj
 * @desc 加载配置，以便在spring的xml配置中可以通过${}使用
 * 通过继承，除了读取配置文件以外，还可以做一些其他操作
 * 如果不需要也可以直接在sping配置中引入PropertyPlaceholderConfigurer
 * @createTime 2020/1/16 10:50
 **/
public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
    @Override
    protected void loadProperties(Properties props) throws IOException {
        super.loadProperties(props);
    }
}
