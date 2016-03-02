//package com.swu.openswu.socketServer;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//
///**
// * Created by csd on 2016/2/24.
// */
//
///**
// * 这是一个用socket实现的服务器程序，可以用来通过命令提示符telnet或其他方式访问查询成绩。<br/>
// * 例如：
// * <p>&nbsp;&nbsp;&nbsp;&nbsp telnet URL:7749</p>
// * 更重要的是用最底层的方式，方便大家用其他接近底层的编程语言使用这个程序，做出自己的实用性强的程序<br/><br/>
// * <Strong>网络接口服务则通过另一种方式实现。</Strong>
// */
//
//public class Server {
//
//    public static void main(String[] args) {
//
//        try {
//            ServerSocket serverSocket = new ServerSocket(8864);
//            //一直运行，请求多线程处理
//            while (true) {
//                Socket incoming = serverSocket.accept();
//                Runnable r = new ThreadedHandler(incoming);
//                Thread t = new Thread(r);
//                t.start();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//}
