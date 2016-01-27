package com.swu.openswu.utils;

/**
 * Created by 张孟尧 on 2016/1/6.
 * modified by csd on 2016/1/27.
 */

import com.swu.openswu.constant.Constant;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 这个Client中提供了doGet,doPost方法
 */
public class Client {


    /*设置请求配置,设置了连接超时和读取超时*/
    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(Constant.TIMEOUT)
            .setSocketTimeout(Constant.TIMEOUT)
            .build();

    private HttpClientBuilder httpClientBuilder;
    private CloseableHttpClient httpClient;


    public Client() {
        /*初始化连接*/
        httpClientBuilder = HttpClientBuilder.create();
        httpClient = httpClientBuilder.useSystemProperties().build();
    }

    public String doGet(String url) {
        /*接受网页内容*/
        String response = null;

        HttpGet httpGet = new HttpGet(url);
        //设置超时
        httpGet.setConfig(requestConfig);

        try (CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet)) {

            if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //get the response
                HttpEntity httpEntity = closeableHttpResponse.getEntity();
                response = EntityUtils.toString(httpEntity);

                //free recource

                EntityUtils.consume(httpEntity);


            } else {
                return Constant.CLIENT_ERROR;
//                log needed.
            }


        } catch (IOException e) {
            e.printStackTrace();
            // log needed.
        }


        return response;
    }


    public String doPost(String url, List<NameValuePair> nameValuePairList) {

        String response;
        HttpPost httpPost = new HttpPost(url);
        //超时设置
        httpPost.setConfig(requestConfig);


        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList));

            try {
                CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
                if (closeableHttpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    /*获得响应*/
                    HttpEntity httpEntity = closeableHttpResponse.getEntity();
/*存到response，然后释放资源*/
                    response = EntityUtils.toString(httpEntity);

                    EntityUtils.consume(httpEntity);
                    //log needed.
                    closeableHttpResponse.close();
                    return response;
                } else {
                    return Constant.CLIENT_ERROR;
                }


            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return url;


    }
}
