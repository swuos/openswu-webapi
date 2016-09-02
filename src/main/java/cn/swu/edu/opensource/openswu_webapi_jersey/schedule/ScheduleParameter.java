package cn.swu.edu.opensource.openswu_webapi_jersey.schedule;

import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Parameter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by 开源协会 陈思定 on 2016/5/8.
 *
 * javabean:课表查询的javabean
 *
 */
@XmlRootElement
public class ScheduleParameter implements Parameter {

    //学年
    public Integer xn;
    //学期
    public Integer xq;
    //学号
    public String swuID;
    //密码
    public String password;

    public ScheduleParameter() {
    }

    public Integer getXn() {
        return xn;
    }

    public void setXn(Integer xn) {
        this.xn = xn;
    }

    public Integer getXq() {
        return xq;
    }

    public void setXq(Integer xq) {
        this.xq = xq;
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
                + " xn : " + this.getXn() + " xq :" + this.getXq();
    }
}
