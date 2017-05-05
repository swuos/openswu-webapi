package cn.swu.edu.opensource.openswu_webapi_jersey.grade;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Lookup;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Parameter;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈思定 on 2016/1/30.
 */
public class GradesLookuper implements Lookup {

    private Client client;

    public GradesLookuper(Client client) {
        this.client = client;
    }

    private GradesLookuper() {
    }

    @Override
    public String lookup(Parameter parameter) {
        SearchParameter searchParam = new SearchParameter();
        if (parameter instanceof SearchParameter) {
            searchParam = (SearchParameter) parameter;
        }
        //这里应该有bug
        List<NameValuePair> nameValuePair = new ArrayList<>();

        nameValuePair.add(new BasicNameValuePair("_search", "false"));
        nameValuePair.add(new BasicNameValuePair("nd", "1451922678091"));
        nameValuePair.add(new BasicNameValuePair("queryModel.currentPage", "1"));
        nameValuePair.add(new BasicNameValuePair("queryModel.showCount", "150"));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortName", ""));
        nameValuePair.add(new BasicNameValuePair("queryModel.sortOrder", "asc"));
        nameValuePair.add(new BasicNameValuePair("time", "1"));
        //提交过来的参数中，学年为空时，会被自动赋值为0，故作此判断，当用户提交的json参数中xnm为空时，这里也提交的参数为空。
        if (searchParam.getXnm() == 0) {
            nameValuePair.add(new BasicNameValuePair("xnm", ""));
        } else {
            nameValuePair.add(new BasicNameValuePair("xnm", "" + searchParam.getXnm()));
        }
        //提交的表单中， 学期的对应关系是这样的，3->1 12->2 16->3 所以需要转换一下
        nameValuePair.add(new BasicNameValuePair("xqm", "" + Constant.ALL_XQM[searchParam.getXqm()]));

        String response = this.client.doPost(Constant.urlGradeSearch + parameter.getSwuID(), nameValuePair);

        return response;
    }
}
