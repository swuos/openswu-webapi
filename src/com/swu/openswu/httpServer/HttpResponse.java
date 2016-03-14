package com.swu.openswu.httpServer;

import com.sun.net.httpserver.HttpExchange;

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

    }
}
