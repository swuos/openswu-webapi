package com.swu.openswu.login;


import com.swu.openswu.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csd on 2016/1/27.
 */
public class LoginPortal {

    //    校内门户地址
    private static final String urlUrp = "http://urp6.swu.edu.cn/login.portal";
    //    用户信息发送目标地址
    private static final String urlLogin = "http://urp6.swu.edu.cn/userPasswordValidate.portal";
    //    登陆后跳转网页
    private static final String urlPortal = "http://urp6.swu.edu.cn/index.portal";
    //    #教务系统网站 Ems意为swu Educational management system
    private static final String urlEms = "http://jw.swu.edu.cn/jwglxt/idstar/index.jsp";
    private static final String urlJW = "http://jw.swu.edu.cn/jwglxt/xtgl/index_initMenu.html";
    /*登陆校内门户是post的两个重要参数*/
    //goto 是java保留字
    private static final String gotos = "http://urp6.swu.edu.cn/loginSuccess.portal";
    private static final String gotoOnFail = "http://urp6.swu.edu.cn/loginFailure.portal";


    private Client client = new Client();

    public String doLogin(String name, String password) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        nameValuePairs.add(new BasicNameValuePair("goto", gotos));
        nameValuePairs.add(new BasicNameValuePair("gotoOnFail", gotoOnFail));
        nameValuePairs.add(new BasicNameValuePair("Login.Token1", name));
        nameValuePairs.add(new BasicNameValuePair("Login.Token2", password));

        return this.client.doPost(urlLogin, nameValuePairs);
    }


}
