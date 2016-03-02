package com.swu.openswu.jwxt;

import com.swu.openswu.constant.Constant;
import com.swu.openswu.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/1/30.
 */
public class GradesLookuper implements Lookup {

    private Client client;

    public GradesLookuper(Client client) {
        this.client = client;
    }

    public GradesLookuper() {


    }

    @Override
    public String lookup(SearchParam searchParam) {
        List<NameValuePair> nameValuePair = new ArrayList<>();

        nameValuePair.add(new BasicNameValuePair("_search", "false"));
        nameValuePair.add(new BasicNameValuePair("nd", "1451922678091"));
        nameValuePair.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        nameValuePair.add(new BasicNameValuePair("queryModel.showCount", "100"));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortName", ""));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        nameValuePair.add(new BasicNameValuePair("time", "1"));
        nameValuePair.add(new BasicNameValuePair("xnm", "" + searchParam.getXnm()));
        nameValuePair.add(new BasicNameValuePair("xqm", "" + searchParam.getXqm()));

        String response = this.client.doPost(Constant.urlGradeSearch + searchParam.getSwuID(), nameValuePair);

        return response;
    }

}
