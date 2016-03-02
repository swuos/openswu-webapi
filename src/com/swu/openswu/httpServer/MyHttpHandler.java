package com.swu.openswu.httpServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.swu.openswu.jwxt.GradeData;
import com.swu.openswu.jwxt.SwuGrades;
import com.swu.openswu.jwxt.TotalInfo;
import com.swu.openswu.login.Login;
import com.swu.openswu.utils.JsonHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by csd on 2016/2/27.
 */
public class MyHttpHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpExchange.getRequestBody()));

        StringBuilder requestBody = new StringBuilder();
        String line;
        if ((line = bufferedReader.readLine()) != null) {
            requestBody.append(line);
        }
        if (requestBody.equals("")) return;

        JsonHandler jsonHandler = new JsonHandler();
        //把请求的json转换为map
        HashMap jsonMap = jsonHandler.fromJson(requestBody.toString());


        try {
            String swuID = (String) jsonMap.get("swuID");
            String password = (String) jsonMap.get("password");
            int xnm = Integer.valueOf((String) jsonMap.get("xnm"));
            int xqm = Integer.valueOf((String) jsonMap.get("xqm"));
            //登录
            Login login = new Login(swuID, password);
            //得到信息
            TotalInfo totalInfo = login.getBasicInfo();

            SwuGrades swuGrades = new SwuGrades(login.getClient());

            swuGrades.lookup(xnm, xqm, totalInfo);
            GradeData grades = totalInfo.getGrades();
            /*
            grades里面存放着所查询的成绩信息
            把成绩放到map里面等一下方便转换为json数据
             */
            ArrayList responseList = new ArrayList();
            for (GradeData.Items item :
                    grades.getItems()) {

                responseList.add(item);
            }
            String responseBody = jsonHandler.toJson(responseList);
            System.out.println(responseBody);

        } catch (Exception e) {
            /*
            别乱传数据啊，现在还没有空来处理
             */
            e.printStackTrace();
        }


    }
}
