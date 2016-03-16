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
import com.swu.openswu.lostAndFound.*;
import com.swu.openswu.utils.JsonHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.HashMap;

/**
 * Created by csd on 2016/2/27.
 */
public class MyHttpHandler implements HttpHandler {

    private final static int FUNCTION_GRADE = 0;
    private final static int FUNCTION_SCHEDULE = 1;
    private final static int FUNCTION_LOSTANDFOUND = 2;

    private final static int TODO_WITHDRAW = 1;
    private final static int TODO_REGISTER_LOST = 0;
    private final static int TODO_REGISTER_FINDHOST = 4;
    private final static int TODO_DISPLAY_ALL = 2;
    private final static int TODO_DISPLAY_SELF = 3;


    private JsonHandler jsonHandler;
    private SearchParam searchParam;

    private Request request;
    private Response response;

    /*
       Imformation  实现了该接口的类携带了失物招领模块中用户的信息
       Register     实现了该接口的类负责登记失物招领信息
       Withdraw     实现了改接口的类负责撤销失物招领信息
    */
    private Imformation imformation;
    private Register register;
    private Withdraw withdraw;

    private Display display;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        System.out.println(httpExchange.getRemoteAddress() + "in");

        request = new HttpRequest(httpExchange);
        response = new HttpResponse(httpExchange);


        /*            ·读取请求              */


        String requestBody = request.getRequestBody();
        System.out.println(requestBody);
        // 请求为空则直接返回
        if (requestBody == null) {
            return;
        }

        jsonHandler = new JsonHandler();
        //把请求的json转换为map
        HashMap jsonMap = jsonHandler.fromJson(requestBody);

        /*             进入功能模块          */

//        为什么下面这两句话都不行呢？
//        int function = (Integer) jsonMap.get("function");
//        int function =(int) jsonMap.get("function");

//        读出模块号
        int function = Integer.valueOf(jsonMap.get("function").toString());
        switch (function) {
            /*          0  查询成绩         */
            case FUNCTION_GRADE:
                try {
                    String swuID = (String) jsonMap.get("swuID");
                    String password = (String) jsonMap.get("password");
                    int xnm = Integer.valueOf((String) jsonMap.get("xnm"));
                    int xqm = Integer.valueOf((String) jsonMap.get("xqm"));
                    searchParam = new SearchParam();
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
                break;
            /*          1  查询课表         */
            case FUNCTION_SCHEDULE:
                break;
             /*         2  失物招领         */
            case FUNCTION_LOSTANDFOUND:
                //读取需要完成哪种操作，是登记还是撤销
                int done = Integer.valueOf(jsonMap.get("done").toString());
                switch (done) {
                    /*  丢失需登记   */
                    case TODO_REGISTER_LOST:
                        // 得到各数据段
                        imformation = new WhoWantToRegister();
                        imformation.getImformation(jsonMap);
                        // 登记
                        register = new SimpleRegister();
                        try {
                            register.register(imformation);
                        } catch (Throwable throwable) {
                            throwable.getCause().printStackTrace();
                            response.sendResponse("false");
                        }
                        response.sendResponse("true");
                        break;
                    /*  找回需撤销   */
                    case TODO_WITHDRAW:
                        // 得到各数据段
                        imformation = new WhoWantToWithDraw();
                        imformation.getImformation(jsonMap);
                        // 撤销
                        withdraw = new SimpleWithDraw();
                        try {
                            withdraw.withdraw(imformation);
                        } catch (Throwable throwable) {
                            throwable.getCause().printStackTrace();
                            response.sendResponse("false");
                        }
                        response.sendResponse("true");
                        break;
                    /*  拾到需登记*/
                    case TODO_REGISTER_FINDHOST:
                        imformation = new WhoWantToFindHost();
                        imformation.getImformation(jsonMap);
                        //
                        register = new SimpleFindHost();
                        try {
                            register.register(imformation);
                        } catch (Throwable throwable) {
                            throwable.getCause().printStackTrace();
                            response.sendResponse("false");
                        }
                        response.sendResponse("true");
                        break;
                    case TODO_DISPLAY_ALL:
                        display = new DisplayAll();
                        

                        break;
                    case TODO_DISPLAY_SELF:
                        break;
                    default:
                        //应对错误的操作选项

                }


                break;
            default:
                //应对空包
        }


    }
}
