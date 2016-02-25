package com.swu.openswu.login;


import com.swu.openswu.constant.Constant;
import com.swu.openswu.jwxt.TotalInfo;
import com.swu.openswu.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csd on 2016/1/27.
 */
public class Login {


    private Client client = new Client();
    private String response = null;
    private TotalInfo totalInfo = new TotalInfo();
    private String swuID;

    public Login(String swuID, String password) {
        totalInfo.setSwuID(swuID);
        this.response = this.doLogin(swuID, password);
    }

    private String doLogin(String name, String password) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        nameValuePairs.add(new BasicNameValuePair("goto", Constant.gotos));
        nameValuePairs.add(new BasicNameValuePair("gotoOnFail", Constant.gotoOnFail));
        nameValuePairs.add(new BasicNameValuePair("Login.Token1", name));
        nameValuePairs.add(new BasicNameValuePair("Login.Token2", password));

        return this.client.doPost(Constant.urlLogin, nameValuePairs);
    }

    public Client getClient() {
        return this.client;
    }

    public String getResponse() {
        return this.response;
    }

    public TotalInfo getBasicInfo() {

        /*进入教务系统*/
        client.doGet(Constant.urlEms);
        /*获得基本信息名字和学号*/
        setBasicInfo(totalInfo, client);
        return totalInfo;
    }

    private String setBasicInfo(TotalInfo totalInfo, Client client) {

        /*获得姓名*/
        String response1 = client.doGet("http://jw.swu.edu.cn/jwglxt/xtgl/index_cxYhxxIndex.html?xt=jw&gnmkdmKey=index&sessionUserKey=" + totalInfo.getSwuID());

        /*判断是否正确获得结果*/
        if (!response1.contains(Constant.NO_NET)) {
           /*对结果进行切割获得姓名*/
            String nametmple = response1.substring(response1.indexOf("heading\">"));
            /*将结果保存进totalInfo*/
            totalInfo.setName(nametmple.substring(9, nametmple.indexOf("</h4>")));
        } else return response1;
        return Constant.CLIENT_OK;
    }
}
