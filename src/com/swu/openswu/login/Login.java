package com.swu.openswu.login;


import com.swu.openswu.constant.Constant;
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

    public Login(String name, String password) {
        this.response = this.doLogin(name, password);
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
}
