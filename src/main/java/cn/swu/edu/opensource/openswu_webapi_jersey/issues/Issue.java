package cn.swu.edu.opensource.openswu_webapi_jersey.issues;

import javax.ws.rs.DefaultValue;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/18.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * POJO,处理用户提交的bug和建议。
 */

public class Issue {


    //未post学号数据时，设置默认学号位数和正常学号相同 对齐格式
    @XmlElement(defaultValue = "xxxxxxxxxxxxxxx")
    public String swuID;

    public String issue;

    public String contact;

    public Issue() {
    }

    public Issue(String swuID, String issue, String contact) {
        this.swuID = swuID;
        this.issue = issue;
        this.contact = contact;
    }

    public String getSwuID() {
        return swuID;
    }

    public void setSwuID(String swuID) {
        this.swuID = swuID;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
