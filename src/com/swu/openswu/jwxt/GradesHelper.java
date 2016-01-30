package com.swu.openswu.jwxt;

import com.google.gson.Gson;
import com.swu.openswu.constant.Constant;
import com.swu.openswu.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public abstract class GradesHelper implements GradeHandler, Lookup {

    private Client client;

    public GradesHelper(Client client) {
        this.client = client;
    }

    public GradesHelper() {


    }

    /**
     * 计算GPA
     *
     * @param totalInfo
     */
    @Override
    public double getGPA(TotalInfo totalInfo) {


        return 0;
    }

    /**
     * 计算学分总和
     *
     * @param totalInfo
     */
    @Override
    public double getSum(TotalInfo totalInfo) {


        return 0;
    }

    /**
     * 计算平均分
     *
     * @param totalInfo
     */
    @Override
    public double getAverage(TotalInfo totalInfo) {
        return 0;
    }

    /**
     * 查询成绩，没有学年和学期参数则查询所有成绩
     *
     * @param totalInfo 存储信息的对象
     */
    @Override
    public void lookup(TotalInfo totalInfo) {

    }

    /**
     * 查询成绩，通过传入学年参数查询该学年所有成绩
     *
     * @param xnm
     * @param totalInfo 存储信息的对象
     */
    @Override
    public void lookup(int xnm, TotalInfo totalInfo) {

    }

    /**
     * 查询成绩，通过传入学年和学期参数查询所有成绩
     *
     * @param xnm       学年
     * @param xqm       学期
     * @param totalInfo 存储信息的对象
     */
    @Override
    public void lookup(int xnm, int xqm, TotalInfo totalInfo) throws Exception {


        List<NameValuePair> nameValuePair = new ArrayList<>();

        nameValuePair.add(new BasicNameValuePair("_search", "false"));
        nameValuePair.add(new BasicNameValuePair("nd", "1451922678091"));
        nameValuePair.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        nameValuePair.add(new BasicNameValuePair("queryModel.showCount", "30"));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortName", ""));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        nameValuePair.add(new BasicNameValuePair("time", "0"));
        nameValuePair.add(new BasicNameValuePair("xnm", "2015"));
        nameValuePair.add(new BasicNameValuePair("xqm", "3"));

        String response = this.client.doPost(Constant.urlGradeSearch + "222014321210033", nameValuePair);

        if (!response.contains(Constant.NO_NET)) {
            /*因为获得数据前面有一个"null"所以对获得的内容进行整理*/
            response = response.substring(4);
            /*构建gson数据来解析json数据*/
            Gson gson = new Gson();
            totalInfo.setGrades(gson.fromJson(response, GradeData.class));
        } else {
            throw new Exception("No net");
        }


    }
}
