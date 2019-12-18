package com.eastValley.util;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author java_shj
 * @desc 封装了HttpClient的工具类
 * 1、连接池
 * 2、关闭资源
 * 3、超时处理
 * @createTime 2019/12/17 10:53
 **/
public class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

    /**
     *  使用json格式发送post请求
     * @return 响应结果字符串，可以根据需要转换成对象
     */
    public static String invokePost4Json(String url, String paramStr, Map<String, Object> headers)  {
        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = null;
//        建立post请求
        HttpPost post = new HttpPost(url);
//        设置请求头
        for (String headerName : headers.keySet()) {
            post.addHeader(headerName, String.valueOf(headers.get(headerName)));
        }
//        设置Json字符串格式的请求参数
        StringEntity entity = new StringEntity(paramStr, "UTF-8");
        post.setEntity(entity);
//        发送请求，接收相应
        try {
            response = client.execute(post);
//            获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();
            if(HttpStatus.SC_OK == statusCode) {
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (IOException e) {
            LOGGER.error("HttpClient发送请求失败：", e);
        }finally {
//            释放资源
            if(response != null) {
                try {
                    response.close();
                    //关闭连接，使用连接池时则不用关闭连接
                    client.close();
                } catch (Exception e) {
                    LOGGER.error("HttpClientUtil关闭连接异常：", e);
                }
            }
        }
        return result;
    }


    /**
     * 获取httpClient
     */
    public CloseableHttpClient getHttpClient() {
        //不使用连接池时
        CloseableHttpClient httpClient = HttpClients.createDefault();

//        使用httpClient连接池
        //TODO
        return httpClient;
    }


}
