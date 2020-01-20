package cn.eastvalley.util;

import cn.eastvalley.control.UserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author java_shj
 * @desc 类型转换工具类
 * @createTime 2020/1/16 18:56
 **/
public class ConvertUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ConvertUtil.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 把数据库查询得到的Map转换成Java的Map
     * @param mapList
     * @return
     */
    public static List<Map<String, Object>> convertSqlMap2JavaMap(List<Map<String,Object>> mapList) {
        List<Map<String, Object>> beans = new ArrayList();
        Iterator<Map<String, Object>> iterator = mapList.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> next = iterator.next();
            HashMap<String, Object> bean = new HashMap();
            Iterator<Map.Entry<String, Object>> iterator1 = next.entrySet().iterator();
            while (iterator1.hasNext()) {
                Map.Entry<String, Object> entry = iterator1.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                String newVaule;
                if(value == null) {
                    newVaule = "";
                }else {
                    if(value instanceof Date){
                        newVaule = sdf.format(value);
                    }
                    else if(value instanceof Timestamp) {
                        newVaule = sdf.format(value);
                    }
                    else if( value instanceof Clob) {
                        newVaule = clobToSTring((Clob) value);
                    }
                    else if(value instanceof Blob) {
                        newVaule = blobToSTring((Blob) value);
                    }
                    else if(value.getClass() == byte[].class) {
                        try {
                            newVaule = new String(((byte[])value), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            newVaule = "";
                            LOGGER.error("convertSqlMap2JavaMap异常", e);
                        }
                    }
                    else if(value instanceof Byte) {
                        newVaule = value.toString();
                    }
                    else {
                        newVaule = String.valueOf(value);
                    }
                }
                bean.put(key, newVaule);
            }
            beans.add(bean);
        }
        return beans;
    }

    private static String blobToSTring(Blob value) {
        return null;
    }

    private static String clobToSTring(Clob value) {
        return null;
    }
}
