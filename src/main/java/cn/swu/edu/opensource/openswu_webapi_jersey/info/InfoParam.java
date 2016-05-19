package cn.swu.edu.opensource.openswu_webapi_jersey.info;


import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Param;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by mxd on 2016/5/18.
 * 查询的学生基本信息所需要的参数列表
 */
@XmlRootElement
public class InfoParam implements Param{
    //学号
    private String swuID;
    //密码
    private String password;

    public String getSwuID() {
        return swuID;
    }

    public void setSwuID(String swuID) {
        this.swuID = swuID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
