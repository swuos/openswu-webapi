package cn.swu.edu.opensource.openswu_webapi_jersey.grade;

/**
 * Created by 陈思定 on 2016/1/28.
 */

/**
 * 还没开工
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
