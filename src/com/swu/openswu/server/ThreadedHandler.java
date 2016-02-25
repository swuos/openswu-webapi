package com.swu.openswu.server;

import com.swu.openswu.utils.JsonHandler;

import java.io.*;
import java.net.Socket;

/**
 * Created by csd on 2016/2/24.
 */

/**
 * 1.查询成绩
 * 学号 密码
 */
public class ThreadedHandler implements Runnable {

    private Socket incoming;


    public ThreadedHandler(Socket socket) {
        this.incoming = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inStream = incoming.getInputStream();
            OutputStream outStream = incoming.getOutputStream();


            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            PrintWriter out = new PrintWriter(outStream, true);

            StringBuilder stringBuilder = new StringBuilder();
            String line = in.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = in.readLine();
            }
            //请求读取完成之后关闭输入流
            incoming.shutdownInput();
            System.out.println(stringBuilder.toString());
            JsonHandler jsonHandler = new JsonHandler();
            System.out.println(jsonHandler.fromJson(stringBuilder.toString()).getClass().getName());


//            boolean done = false;
//
//            while (!done && in.hasNext()) {
//                String line = in.nextLine();
//
//                if (line.trim().equals("BYE")) {
//                    break;
//                }
//                System.out.println(line);
//                if (line.equals("1")) {
//                    String swuID = in.nextLine();
//                    if (line.trim().equals("BYE")) {
//                        break;
//                    }
//                    String password = in.nextLine();
//                    if (line.trim().equals("BYE")) {
//                        break;
//                    }
//
//                    Login login = new Login(swuID, password);
//
//                    TotalInfo totalInfo = login.getBasicInfo();
//
//                    SwuGrades swuGrades = new SwuGrades(login.getClient());
//
//                    try {
//
//                        swuGrades.lookup(2015, 1, totalInfo);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//
//                    GradeData grades = totalInfo.getGrades();
//
//                    JsonHandler jsonHandler = new JsonHandler();
//                    HashMap<String,String> map = new HashMap<>();
//                    int i=1;
//                    for (GradeData.Items item :
//                            grades.getItems()) {
////                        out.println(item.getKcmc());
////                        out.println(item.getCj());
////                        out.println(item.getJd());
////                        out.println(item.getXf());
//
//                        map.put("kcmc"+i,item.getKcmc());
//                        map.put("Cj"+i,item.getCj());
//                        map.put("Jd"+i,item.getJd());
//                        map.put("Xf"+i,item.getXf());
//                        ++i;
//                    }
//                    out.println(jsonHandler.toJson(map));
//                }
//
//            }

            incoming.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
