package com.swu.openswu.jwxt;

import java.util.List;
import java.util.Map;

/**
 * Created by 张孟尧 on 2016/1/6.
 * Edit by 陈思电 on 2016/3/2.
 */
public class TotalInfo {
    //    储存查询所需的xnm,xqm(代表学年和学期)以及用户的密码
    
    //    姓名
    private static String name = null;
    //    学号
    private static String swuID = null;
    //    private static Map<String, List<String>> gradesData = null;
    private static Map<String, List<String>> classScheedul = null;
    /*成绩*/
    private static GradeData gradesData;

    public void setName(String name) {
        TotalInfo.name = name;
    }

    public void setSwuID(String swuID) {
        TotalInfo.swuID = swuID;
    }

    public String getName() {
        return name;
    }

    public String getSwuID() {
        return swuID;
    }

    public void setGrades(GradeData gradesData) {
        TotalInfo.gradesData = gradesData;
    }

    public GradeData getGrades() {
        return gradesData;
    }
}
