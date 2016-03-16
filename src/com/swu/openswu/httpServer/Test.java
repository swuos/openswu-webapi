package com.swu.openswu.httpServer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by csd on 2016/2/27.
 */

/**
 * 测试httpServer的主类，String json 中的swuID和password需要填写
 */
public class Test {


    public static void main(String[] a) throws IOException {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = httpClientBuilder.useSystemProperties().build();


        long date = System.currentTimeMillis() / 1000L;

        HttpPost httpPost = new HttpPost("http://127.0.0.1:7749/openswu");
        String json = "{\"swuID\":\"\", \"password\":\"\", \"xnm\": \"2015\",\"xqm\": \"1\"} ";
        String lostfindJson = "{\n" +
                "\"function\":\"3\",\n" +
                "\"swuid\":\"222014321210033\",\n" +
                "\"text\":\"dada\",\n" +
                "\"details\":\"dadsa\",\n" +
                "\"time\":" + "\"" + date + "\"" + ",\n" +
                "\"place\" : \"dasdsa\",\n" +
                "\"done\" : \"0\"\n" +
                "}\n";
        StringEntity stringEntity = new StringEntity(lostfindJson, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        HttpResponse response = null;
        response = httpClient.execute(httpPost);


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }
        bufferedReader.close();


    }
}
