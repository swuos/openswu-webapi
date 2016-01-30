package com.swu.openswu.main;

import com.swu.openswu.constant.Constant;
import com.swu.openswu.jwxt.SwuGrades;
import com.swu.openswu.jwxt.TotalInfo;
import com.swu.openswu.login.Login;

/**
 * Created by csd on 2016/1/27.
 */
public class Test {

    public static void main(String[] args) {

        Login login = new Login("222014321210033", "7281542csd");

        System.out.println(login.getResponse());
        System.out.println(login.getClient().doGet(Constant.urlEms));
        System.out.println(login.getClient().doGet("http://jw.swu.edu.cn/jwglxt/xtgl/index_initMenu.html"));
        SwuGrades swuGrades = new SwuGrades(login.getClient());

        System.out.println(swuGrades.getSum(null));

        TotalInfo totalInfo = new TotalInfo();
        try {

            swuGrades.lookup(2015, 1, totalInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
