package com.eastValley.util;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @author java_shj
 * @desc 封装了HttpClient的工具类
 * TODO 要不要每次请求时获取httpClient呢?
 * @createTime 2019/12/17 10:53
 **/
public class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);

  /*  //CloseableHttpClient是线程安全的，可以全局用一个吗？----待验证
    private static  final CloseableHttpClient client ;*/
    static PoolingHttpClientConnectionManager clientManager;
    static RequestConfig requestConfig;
    //静态初始化
    static {
        LayeredConnectionSocketFactory sslSocketFactory = null;
        try {
            sslSocketFactory = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("初始化LayeredConnectionSocketFactory失败", e);
        }

        //配置同时支持http和https请求
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("https", sslSocketFactory)
                .register("http", new PlainConnectionSocketFactory()).build();
        //初始化连接池管理器
        clientManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        //设置整个连接池最大连接数
        clientManager.setMaxTotal(1000);
        //设置单个服务器最大连接数
        clientManager.setDefaultMaxPerRoute(200);
        //配置：连接服务器、从连接池获取连接、读取服务器返回数据超时时间，避免内存或者线程池溢出
        requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(3000).setSocketTimeout(3000).build() ;
    }

    /**
     *  使用json格式发送post请求
     * @return 响应结果字符串，可以根据需要转换成对象
     */
    public static String invokePost4Json(String url, String paramStr, Map<String, Object> headers)  {
        CloseableHttpClient client = getHttpClient();
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
            closeResource(response);
        }
        return result;
    }

    /**
     * 释放资源
     */
    private static void closeResource(CloseableHttpResponse response){
        if(response != null) {
            try {
                response.close();
                //关闭连接，使用连接池时则不用手动关闭连接
//                    CLIENT.close();
            } catch (Exception e) {
                LOGGER.error("HttpClientUtil关闭连接异常：", e);
            } finally {
                HttpClientUtils.closeQuietly(response);
            }
        }
    }


    /**
     * 获取httpClient
     */
    private static CloseableHttpClient getHttpClient() {
        //不使用连接池时
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        使用httpClient连接池
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(clientManager).setDefaultRequestConfig(requestConfig).build();

        return httpClient;
    }


}
