package cn.eastvalley.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Random;

/**
 * @author java_shj
 * @desc 主键工具类，根据表名来生成主键
 * @createTime 2020/1/12 20:42
 **/
public class PrimaryKeyUtil {

    /**
     * 根据表名生成主键
     * @return string
     */
    public static String getPrimaryKey( ) {
        long timestamp = System.currentTimeMillis();
        String pattern = "yyyyMMddHHmmss";
        //TODO 后期可以配合redis的incr(tableName)方法一起使用来生成
        return DateFormatUtils.format(timestamp, pattern).substring(2) + getRandomString(6);
    }

    /**
     * 生成指定长度的数字字符串
     * @param length  长度
     * @return String
     */
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
