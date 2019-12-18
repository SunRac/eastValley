package com.eastValley.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author java_shj
 * @desc 读取.properties文件的工具类
 * @createTime 2019/12/17 13:22
 **/
public class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String fileName) throws IOException {
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            LOGGER.error("加载属性文件异常：" + fileName + "is not found");
            throw new FileNotFoundException();
        }
        Properties props = new Properties();
        props.load(is);
        if(is != null) {
            try {
                is.close();
            } catch (IOException e) {
                LOGGER.error("关闭properties文件异常:", e);
            }

        }
        return props;
    }

    /**
     * 读取字符串属性
     * @param defaultValue 没有读取到属性时，使用默认值
     */
    public static String getString(Properties props, String key, String defaultValue) {
        String value = defaultValue;
        if(props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }

    public static String getString(Properties props, String key) {
        return getString(props, key, "");
    }


    //获取数值型属性(默认值为0)
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if(props.containsKey(key)) {
            value = Integer.parseInt(props.getProperty(key));
        }
        return value;
    }

    //获取boolean属性值（默认为false）
    public  static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if(props.containsKey(key)) {
            value = Boolean.parseBoolean(props.getProperty(key));
        }
        return value;
    }

}
