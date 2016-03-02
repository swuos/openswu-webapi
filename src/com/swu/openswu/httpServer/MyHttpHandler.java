package com.swu.openswu.httpServer;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.swu.openswu.constant.Constant;
import com.swu.openswu.jwxt.GradeData;
import com.swu.openswu.jwxt.SearchParam;
import com.swu.openswu.jwxt.SwuGrades;
import com.swu.openswu.jwxt.TotalInfo;
import com.swu.openswu.login.Login;
import com.swu.openswu.utils.JsonHandler;

import java.io.*;
import java.net.HttpURLConnection;
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
        bufferedReader.close();

        JsonHandler jsonHandler = new JsonHandler();
        //把请求的json转换为map
        HashMap jsonMap = jsonHandler.fromJson(requestBody.toString());


        try {
            String swuID = (String) jsonMap.get("swuID");
            String password = (String) jsonMap.get("password");
            int xnm = Integer.valueOf((String) jsonMap.get("xnm"));
            int xqm = Integer.valueOf((String) jsonMap.get("xqm"));
            SearchParam searchParam = new SearchParam();
            searchParam.setSwuID(swuID);
            searchParam.setPassword(password);
            searchParam.setXnm(xnm);
            searchParam.setXqm(xqm);
            //登录
            Login login = new Login(swuID, password);
            if (login.getResponse().contains("用户不存在或密码错误")) {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_ACCEPTABLE, "用户不存在或密码错误".getBytes().length);
                BufferedWriter responseWriter = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
                responseWriter.write("用户不存在或密码错误");
                responseWriter.close();
                return;
            }
            //得到信息
            TotalInfo totalInfo = login.getBasicInfo();

            SwuGrades swuGrades = new SwuGrades(login.getClient());

            String response = swuGrades.lookup(searchParam);
            if (!response.contains(Constant.NO_NET)) {
            /*构建gson数据来解析json数据*/
                Gson gson = new Gson();
                totalInfo.setGrades(gson.fromJson(response, GradeData.class));
            } else {
                httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_ACCEPTABLE, "no net".getBytes().length);
                BufferedWriter responseWriter = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
                responseWriter.write("no net");
                responseWriter.close();
                return;
            }
            GradeData grades = totalInfo.getGrades();
            /*
            grades里面存放着所查询的成绩信息
            把成绩放到map里面等一下方便转换为json数据
             */

            String responseBody = jsonHandler.toJson(grades);
            System.out.println(responseBody);

            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, responseBody.getBytes().length);
            BufferedWriter responseWriter = new BufferedWriter(new OutputStreamWriter(httpExchange.getResponseBody()));
            responseWriter.write(responseBody.toString());
            responseWriter.close();


        } catch (Exception e) {
            /*
            别乱传数据啊，现在还没有空来处理
             */
            e.printStackTrace();
        }


    }
}
