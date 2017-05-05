package cn.swu.edu.opensource.openswu_webapi_jersey.login;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.TotalInfo;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csd on 2016/1/27.
 * <p>
 * 完成西南大学统一认证
 */
public class Login {

    private Client client = new Client();
    private String response = null;
    private TotalInfo totalInfo = new TotalInfo();
    private String swuID;

    public Login(String swuID, String password) {
        totalInfo.setSwuID(swuID);
        this.response = this.doLogin(swuID, password);
    }

    /**
     * 通过西南大学统一认证，认证未通过会返回“连接问题”等等错误提示。
     *
     * @return 该用户名和密码通过认证的结果。
     */
    private String doLogin(String name, String password) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        nameValuePairs.add(new BasicNameValuePair("IDToken0", ""));
        nameValuePairs.add(new BasicNameValuePair("IDToken1", name));
        nameValuePairs.add(new BasicNameValuePair("IDToken2", password));
        nameValuePairs.add(new BasicNameValuePair("IDButton", "Submit"));
        nameValuePairs.add(new BasicNameValuePair("goto", "aHR0cDovL2p3LnN3dS5lZHUuY24vandnbHh0L2lkc3Rhci9pbmRleC5qc3A="));
        nameValuePairs.add(new BasicNameValuePair("encoded", "true"));
        nameValuePairs.add(new BasicNameValuePair("gx_charset", "UTF-8"));

        return this.client.doPost(Constant.urlUnifiedAuthentication, nameValuePairs);
    }

    public Client getClient() {
        return this.client;
    }

    public String getResponse() {
        return this.response;
    }

    public TotalInfo getBasicInfo() {

        /*进入教务系统*/
        client.doGet(Constant.urlEms);
        /*获得基本信息名字和学号*/
        setBasicInfo(totalInfo, client);
        return totalInfo;
    }

    private String setBasicInfo(TotalInfo totalInfo, Client client) {

        /*获得姓名*/
        String response = client.doGet("http://jw.swu.edu.cn/jwglxt/xtgl/index_cxYhxxIndex.html?xt=jw&gnmkdmKey=index&sessionUserKey=" + totalInfo.getSwuID());

        /*判断是否正确获得结果*/
        if (!response.contains(Constant.NO_NET)) {
           /*对结果进行切割获得姓名*/
//            String nametmple = response.substring(response.indexOf("heading\">"));
            /*将结果保存进totalInfo*/
//            totalInfo.setName(nametmple.substring(9, nametmple.indexOf("</h4>")));
        } else {
            return response;
        }
        return Constant.CLIENT_OK;
    }
}
