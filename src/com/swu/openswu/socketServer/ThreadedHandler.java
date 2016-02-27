package com.swu.openswu.socketServer;

import com.swu.openswu.jwxt.GradeData;
import com.swu.openswu.jwxt.SwuGrades;
import com.swu.openswu.jwxt.TotalInfo;
import com.swu.openswu.login.Login;
import com.swu.openswu.utils.JsonHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

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


            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream, true);


            boolean done = false;

            while (!done && in.hasNext()) {
                String line = in.nextLine();

                if (line.trim().equals("BYE")) {
                    break;
                }
                System.out.println(line);
                if (line.equals("1")) {
                    String swuID = in.nextLine();
                    if (line.trim().equals("BYE")) {
                        break;
                    }
                    String password = in.nextLine();
                    if (line.trim().equals("BYE")) {
                        break;
                    }

                    Login login = new Login(swuID, password);

                    TotalInfo totalInfo = login.getBasicInfo();

                    SwuGrades swuGrades = new SwuGrades(login.getClient());

                    try {

                        swuGrades.lookup(2015, 1, totalInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    GradeData grades = totalInfo.getGrades();

                    JsonHandler jsonHandler = new JsonHandler();
                    HashMap<String, String> map = new HashMap<>();
                    int i = 1;
                    for (GradeData.Items item :
                            grades.getItems()) {
//                        out.println(item.getKcmc());
//                        out.println(item.getCj());
//                        out.println(item.getJd());
//                        out.println(item.getXf());

                        map.put("kcmc" + i, item.getKcmc());
                        map.put("Cj" + i, item.getCj());
                        map.put("Jd" + i, item.getJd());
                        map.put("Xf" + i, item.getXf());
                        ++i;
                    }
                    out.println(jsonHandler.toJson(map));
                }

            }

            incoming.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
