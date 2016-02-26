package com.swu.openswu.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by csd on 2016/2/24.
 */
public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(7749);
            //一直运行，请求多线程处理
            while (true) {
                Socket incoming = serverSocket.accept();
                Runnable r = new ThreadedHandler(incoming);
                Thread t = new Thread(r);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
