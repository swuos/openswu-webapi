package com.swu.openswu.httpServer;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;

import java.net.InetSocketAddress;

/**
 * 服务器启动类。
 */
public class Server {


    public static void main(String[] args) throws Exception {

        HttpServerProvider httpServerProvider = HttpServerProvider.provider();
        //同时允许100的访问量，监听端口7749
        HttpServer httpServer = httpServerProvider.createHttpServer(new InetSocketAddress(7749), 100);
        //映射到http://url/openswu
        httpServer.createContext("/openswu", new MyHttpHandler());
        httpServer.setExecutor(null);
        httpServer.start();

    }
}
