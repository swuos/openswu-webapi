package com.swu.openswu.socketServer;

import com.swu.openswu.jwxt.GradeData;
import com.swu.openswu.jwxt.SearchParam;
import com.swu.openswu.jwxt.SwuGrades;
import com.swu.openswu.jwxt.TotalInfo;
import com.swu.openswu.login.Login;
import com.swu.openswu.utils.JsonHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
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

            out.println("请输入   SEARCH  进入成绩查询");

            boolean done = false;

            while (!done && in.hasNext()) {
                String line = in.nextLine();
                if (line.trim().equals("BYE")) {
                    break;
                }
                if (line.equals("SEARCH")) {

                    out.println("进入查询，请依次输入： 学号 密码 查询的学年数 查询的学期数 您还可以随时输入BYE退出 输入SEARCH再次查询");
                    String swuID = in.nextLine().trim();
                    if (swuID.equals("BYE")) {
                        break;
                    }
                    String password = in.nextLine().trim();
                    if (password.equals("BYE")) {
                        break;
                    }
                    String xnm = in.nextLine().trim();
                    if (xnm.equals("BYE")) {
                        break;
                    }
                    String xqm = in.nextLine().trim();
                    if (xqm.equals("BYE")) {
                        break;
                    }

                    Login login = new Login(swuID, password);

                    TotalInfo totalInfo = login.getBasicInfo();

                    SwuGrades swuGrades = new SwuGrades(login.getClient());

                    try {
                        SearchParam searchParam = new SearchParam();
                        searchParam.setXnm(Integer.valueOf(xnm));
                        searchParam.setXqm(Integer.valueOf(xqm));
                        swuGrades.lookup(searchParam);
//                        swuGrades.lookup(Integer.valueOf(xnm), Integer.valueOf(xqm), totalInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    GradeData grades = totalInfo.getGrades();

                    JsonHandler jsonHandler = new JsonHandler();

                    for (GradeData.Items item :
                            grades.getItems()) {
                        out.println(item.getKcmc());
                        out.println(item.getCj());
                        out.println(item.getJd());
                        out.println(item.getXf());
                    }
                }

            }

            incoming.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
