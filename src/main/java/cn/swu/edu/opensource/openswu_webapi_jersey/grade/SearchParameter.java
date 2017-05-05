package cn.swu.edu.opensource.openswu_webapi_jersey.grade;

/**
 * Created by csd on 2016/3/2.
 */

import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Parameter;

/**
 * The POJO supported by MOXy
 *
 * 该类作为成绩查询模块，被MOXy支持的javabean。
 */

public class SearchParameter implements Parameter {

    public Integer xnm = null;
    public Integer xqm = null;

    public String swuID = null;
    public String password = null;

    public SearchParameter() {
        // TODO Auto-generated constructor stub
    }

    public SearchParameter(Integer xnm, Integer xqm, String swuID, String password) {

        this.xnm = xnm;
        this.xqm = xqm;
        this.swuID = swuID;
        this.password = password;
    }

    public Integer getXnm() {

        return xnm;
    }

    public void setXnm(int xnm) {
        this.xnm = xnm;
    }

    public Integer getXqm() {
        return xqm;
    }

    public void setXqm(int xqm) {
        this.xqm = xqm;
    }

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

    @Override
    public String toString() {
        return "swuid : " + this.getSwuID() + " password : " + this.getPassword()
                + " xn : " + this.getXnm() + " xq :" + this.getXqm();
    }
}
