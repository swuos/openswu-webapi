package com.swu.openswu.httpServer;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by csd on 2016/3/14.
 */
public class HttpResponse implements Response {

    HttpExchange exchange;

    public HttpResponse(HttpExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public void sendResponse(String responseBody) {
        try {
            //设置头属性
            this.exchange.sendResponseHeaders(200, responseBody.length());

//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(this.exchange.getResponseBody());
//            //码流的转换
//            bufferedOutputStream.write(responseBody.getBytes());
//            System.out.println(responseBody);
//            bufferedOutputStream.flush();

            OutputStream out = this.exchange.getResponseBody();
            out.write(responseBody.getBytes());
            out.flush();

            this.exchange.close();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
