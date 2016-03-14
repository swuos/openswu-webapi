package com.swu.openswu.httpServer;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by csd on 2016/3/14.
 */
public class HttpRequest implements Request {


    HttpExchange exchange;

    public HttpRequest(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String getRequestBody() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.exchange.getRequestBody()));

        StringBuilder requestBody = new StringBuilder();
        //把请求读到requestBody里面
        String line;
        if ((line = bufferedReader.readLine()) != null) {
            requestBody.append(line);
        }
        // 请求为空返回null
        if (requestBody.equals("")) return null;

        bufferedReader.close();

        return requestBody.toString();
    }
}
