package com.swu.openswu.httpServer;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

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

        HttpPost httpPost = new HttpPost("http://127.0.0.1:7749/openswu");
        String json = "{\"swuID\":\"222014321210033\", \"password\":\"7281542csd\", \"xnm\": \"2014\",\"xqm\": \"2\"} ";
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        HttpResponse response = null;
        response = httpClient.execute(httpPost);

        System.out.println(response.getEntity());


    }
}