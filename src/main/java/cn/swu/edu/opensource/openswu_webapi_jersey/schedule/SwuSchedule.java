package cn.swu.edu.opensource.openswu_webapi_jersey.schedule;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.login.LoginToEms;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Lookup;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Parameter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 开源协会 陈思定 on 2016/5/8.
 */
public class SwuSchedule implements Lookup{

    private String schedule;
    private Client client;

    public SwuSchedule(Parameter parameter) {

        LoginToEms loginToEms = new LoginToEms(parameter);

        this.client = loginToEms.getClient();

        this.schedule = lookup(parameter);
    }

    public String getSchedule(){
        return this.schedule;
    }

    @Override
    public String lookup(Parameter parameter) {
        ScheduleParameter scheduleParam = null;
        /*
            强制类型转换一下，类型错误就返回空串
         */
        if (parameter instanceof ScheduleParameter) scheduleParam = (ScheduleParameter) parameter;
        else return "";

        List<NameValuePair> nameValuePair = new ArrayList<>();

        nameValuePair.add(new BasicNameValuePair("xnm", scheduleParam.getXn().toString()));
        nameValuePair.add(new BasicNameValuePair("xqm", Constant.ALL_XQM[scheduleParam.getXq()]));

        String response = this.client.doPost(Constant.urlScheduleSearch+scheduleParam.getSwuID(), nameValuePair);


        return response;
    }

}
