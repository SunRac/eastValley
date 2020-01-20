package cn.eastvalley.util;

import cn.eastvalley.control.UserController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.mapping.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.sql.Date;
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
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final char[] HEXCHAR = new char[]{'0','1','2','3','4','5','6','7','8','9','a','b','c','c','e','f'};

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
                        newVaule = clob2String((Clob) value);
                    }
                    else if(value instanceof Blob) {
                        newVaule = blob2String((Blob) value);
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

    private static String blob2String(Blob blob) {
        try {
            byte[] bytes = blob.getBytes(1L, (int) blob.length());
            return bytes2String(bytes);
        } catch (SQLException e) {
            LOGGER.error("blob2String异常", e);
            return null;
        }
    }

    private static String clob2String(Clob clob) {
        if(clob == null) {
            return null;
        } else {
            Reader reader = null;
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bfReader = null;
            try {
                reader = clob.getCharacterStream();
                bfReader = new BufferedReader(reader);
                for(String s = bfReader.readLine(); null != s; s = bfReader.readLine()) {
                    stringBuffer.append(s);
                }
            } catch (Exception e) {
                LOGGER.error("clob2String异常", e);
            } finally {
                try {
                    if(null != reader) {
                        reader.close();;
                    }
                    if(null != bfReader) {
                        bfReader.close();
                    }
                } catch (Exception e1) {
                    LOGGER.error("clob2String关闭流异常", e1);
                }
            }
            return stringBuffer.toString();
        }
    }

    private static String bytes2String(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            builder.append(HEXCHAR[(bytes[i] & 240) >>> 4]);
            builder.append(HEXCHAR[(bytes[i] & 15)]);
        }
        return builder.toString();
    }

    private static byte[] string2Bytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i +2), 16);
        }
        return bytes;
    }

    public static InputStream string2InputStream(String str) {
        return new ByteArrayInputStream(string2Bytes(str));
    }

    public static String file2String(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fileInputStream.read(bytes);
        return bytes2String(bytes);
    }

    public static String inputStream2String(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[512];
        int i;
        while( ( i = inputStream.read(buff, 0, 512)) > 0) {
            outputStream.write(buff, 0, i);
        }
        byte[] bytes = outputStream.toByteArray();
        return bytes2String(bytes);
    }

    /**
     * 根据变量名获取对应的get名
     * @param field
     * @return
     */
    public static String getMethodByField(String field) {
        return field == null? null : "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    /**
     * 把value为对象的Map转换成value为字符串的Map
     * @param objMap
     * @return
     */
    public static Map<String,String> convertObjMap2StringMap(Map<String, Object> objMap) {
        if(objMap == null) {
            return null;
        } else {
            Map<String, String> resultMap = new HashMap<>();
            Object value;
            String newValue;
            for (String key : objMap.keySet()) {
                value = objMap.get(key);
                if(value != null) {
                    newValue = convertObject2Json(value);
                    resultMap.put(key, newValue);
                }

            }
            return resultMap;
        }
    }

    /**
     * 把对象转换成json字符串
     * @param object
     * @return
     */
    public static String convertObject2Json(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("covertObject2Json异常", e);
            return null;
        }
    }

    /**
     * 把json字符串转换成对象
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T convertJson2Object(String json, Class<T> cls) {
        String method = "convertJson2Object";
        try {
            return objectMapper.readValue(json, cls);
        } catch (IOException e) {
            LOGGER.error("convertJson2Object异常", e);
            return null;
        }
    }

}
