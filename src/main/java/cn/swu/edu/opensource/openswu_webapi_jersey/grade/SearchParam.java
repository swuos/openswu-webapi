package cn.swu.edu.opensource.openswu_webapi_jersey.grade;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by csd on 2016/3/2.
 */

/**
 * 存放查询的参数，密码只能被设置而不能被读取
 */

public class SearchParam {

    private Integer xnm = null;
    private Integer xqm = null;

    private String swuID = null;
    private String password = null;

    public SearchParam() {
		// TODO Auto-generated constructor stub
	}
    public void setXnm(int xnm) {
        this.xnm = xnm;
    }

    public void setXqm(int xqm) {
        this.xqm = xqm;
    }

    public void setSwuID(String swuID) {
        this.swuID = swuID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getXnm() {

        return xnm;
    }

    public Integer getXqm() {
        return xqm;
    }

    public String getSwuID() {
        return swuID;
    }
	public String getPassword() {
		return password;
	}
    
}
