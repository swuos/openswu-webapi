package cn.swu.edu.opensource.openswu_webapi_jersey.issues;

import javax.ws.rs.DefaultValue;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/18.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * POJO,处理用户提交的bug和建议。
 */

@XmlRootElement
public class Issue {


    //未post学号数据时，设置默认学号
    @DefaultValue("222014321210033")
    public String swuID;

    public String issue;

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
}
