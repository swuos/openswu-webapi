package com.swu.openswu.test;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.Socket;

public class Test {


    public static void main(String[] args) throws IOException {

        HttpPost httpPost = new HttpPost();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpClient httpClient = httpClientBuilder.useSystemProperties().build();

        Socket socket = new Socket();


    }
}
