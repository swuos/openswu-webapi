package com.swu.openswu.main;

import com.swu.openswu.constant.Constant;
import com.swu.openswu.jwxt.GradeData;
import com.swu.openswu.jwxt.SwuGrades;
import com.swu.openswu.jwxt.TotalInfo;
import com.swu.openswu.login.Login;

/**
 * Created by csd on 2016/1/27.
 */
public class Test {

    public static void main(String[] args) {

        Login login = new Login("222014321210033", "7281542csd");
        TotalInfo totalInfo = login.getBasicInfo();

        System.out.println(login.getResponse());
        System.out.println(login.getClient().doGet(Constant.urlEms));

        SwuGrades swuGrades = new SwuGrades(login.getClient());

        try {

            swuGrades.lookup(2015, 1, totalInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }


        GradeData grades = totalInfo.getGrades();

        for (GradeData.Items item :
                grades.getItems()) {
            System.out.println(item.getKcmc());
            System.out.println(item.getCj());
            System.out.println(item.getJd());
            System.out.println(item.getXf());
        }
    }
}
