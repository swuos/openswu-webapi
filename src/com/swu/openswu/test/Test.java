package com.swu.openswu.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by csd on 2016/2/25.
 */
public class Test {
    private static final String APPLICATION_JSON = "application/json";

    private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

    public static void main(String[] args) throws IOException {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = httpClientBuilder.useSystemProperties().build();

        HttpPost httpPost = new HttpPost("http://127.0.0.1:7749");
        httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);

        String encoderJson = URLEncoder.encode("{\"Xf10\":\"3.0\",\"Cj10\":\"61\",\"Cj11\":\"87\",\"Xf11\":\"1.0\",\"Cj2\":\"90\",\"Cj1\":\"89\",\"Cj4\":\"85\",\"Cj3\":\"67\",\"Cj6\":\"79\",\"Cj5\":\"66\",\"Cj8\":\"73\",\"kcmc1\":\"性、艾滋病与健康\",\"Cj7\":\"61\",\"Cj9\":\"78\",\"kcmc4\":\"电子技术基础\",\"kcmc5\":\"汇编语言程序设计\",\"Jd1\":\"3.5\",\"kcmc2\":\"数据结构\",\"kcmc3\":\"概率论与数理统计\",\"Jd3\":\"1.5\",\"kcmc8\":\"形势与政策\",\"Jd2\":\"4\",\"kcmc9\":\"毛泽东思想和中国特色社会主义理论体系概论A\",\"Jd11\":\"3.5\",\"Jd5\":\"1.5\",\"kcmc6\":\"JAVA程序设计\",\"Jd10\":\"1\",\"Jd4\":\"3.5\",\"kcmc7\":\"马克思主义基本原理概论\",\"Jd7\":\"1\",\"Jd6\":\"2.5\",\"Jd9\":\"2.5\",\"Jd8\":\"2\",\"Xf1\":\"2.0\",\"Xf3\":\"3.0\",\"Xf2\":\"5.0\",\"Xf5\":\"3.0\",\"Xf4\":\"5.0\",\"Xf7\":\"3.0\",\"Xf6\":\"3.5\",\"Xf9\":\"3.0\",\"Xf8\":\"1.0\",\"kcmc10\":\"大学英语ⅠD（留学海外）\",\"kcmc11\":\"网球\"}", HTTP.UTF_8);
        StringEntity se = new StringEntity(encoderJson);
        se.setContentType(CONTENT_TYPE_TEXT_JSON);
        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httpPost.setEntity(se);
        HttpResponse s = httpClient.execute(httpPost);

        System.out.println(s.getEntity());

    }
}
