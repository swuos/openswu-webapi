package com.swu.openswu.jwxt;

/**
 * Created by 陈思定 on2016/1/28
 */
public interface Lookup {

    /**
     * 查询成绩，没有学年和学期参数则查询所有成绩
     *
     * @param TotalInfo 存储信息的对象
     */
    public void lookup(TotalInfo TotalInfo);


    /**
     * 查询成绩，通过传入学年参数查询该学年所有成绩
     *
     * @param xnm
     * @param TotalInfo 存储信息的对象
     */
    public void lookup(int xnm, TotalInfo TotalInfo);


    /**
     * 查询成绩，通过传入学年和学期参数查询所有成绩
     *
     * @param xnm       学年
     * @param xqm       学期
     * @param TotalInfo 存储信息的对象
     */
    public void lookup(int xnm, int xqm, TotalInfo TotalInfo);


}
