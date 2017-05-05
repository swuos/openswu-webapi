package cn.swu.edu.opensource.openswu_webapi_jersey.quitnet;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/29.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * break the swu-net connection.
 */
public class Quit {

    private String response;

    public void doQuit(String username, String password) {
        List<NameValuePair> entries = new ArrayList<>();
        entries.add(new BasicNameValuePair("username", username));
        entries.add(new BasicNameValuePair("password", password));

        this.response = new Client().doPost(Constant.urlQuitNet, entries);
    }

    public String getResponse() {
        return this.response;
    }

}
