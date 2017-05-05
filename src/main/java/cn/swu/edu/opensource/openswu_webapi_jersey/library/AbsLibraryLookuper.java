package cn.swu.edu.opensource.openswu_webapi_jersey.library;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Lookup;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Parameter;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/23.
 * <p>
 * Email : sidingchan@gmail.com
 */
public abstract class AbsLibraryLookuper implements Lookup {

    protected AbsLibraryLookuper() {
    }

    protected Client loginInLibrarySystem(Parameter libraryParameter) {
        libraryParameter = (LibraryParameter) libraryParameter;

        List<NameValuePair> nameValuePairs = new ArrayList();

        nameValuePairs.add(new BasicNameValuePair("username", libraryParameter.getSwuID()));
        nameValuePairs.add(new BasicNameValuePair("password", libraryParameter.getPassword()));
        nameValuePairs.add(new BasicNameValuePair("refer", "space.jsp?do=home"));
        nameValuePairs.add(new BasicNameValuePair("loginsubmit", ""));
        nameValuePairs.add(new BasicNameValuePair("formhash", "a476d524"));
        nameValuePairs.add(new BasicNameValuePair("backUrl", ""));

        Client client = new Client();
        String res;
        res = client.doPost(Constant.urlLibrary, nameValuePairs);
        System.out.println(res);
        return client;
    }

}
