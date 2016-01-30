package com.swu.openswu.jwxt;

/**
 * Created by 陈思定 on 2016/1/28.
 */
public interface GradeHandler {


    /**
     * 计算GPA
     */
    public double getGPA(TotalInfo totalInfo);

    /**
     * 计算学分总和
     */
    public double getSum(TotalInfo totalInfo);


    /**
     * 计算平均分
     */
    public double getAverage(TotalInfo totalInfo);


}
