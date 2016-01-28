package com.swu.openswu.main;

import com.swu.openswu.login.Login;

/**
 * Created by csd on 2016/1/27.
 */
public class Test {

    public static void main(String[] args) {

        Login login = new Login();

        String response = login.doLogin("222014321210033", "7281542csd");


        System.out.println(response);


    }
}
