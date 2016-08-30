package cn.swu.edu.opensource.openswu_webapi_jersey.info;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.login.LoginToEms;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Lookup;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Param;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mxd on 2016/5/18.
 * 查询学生的基本信息
 */
public class SwuInfo implements Lookup {

    private String personalInfo;
    private Client client;

    public SwuInfo(Param param) {

        LoginToEms loginToEms = new LoginToEms(param);

        this.client = loginToEms.getClient();
        this.personalInfo = lookup(param);
    }

    public String getInfo(){
        return this.personalInfo;
    }

    public String lookup(Param param) {
        //转换类型
        InfoParam infoParam = null;
        if(param instanceof InfoParam) infoParam = (InfoParam)param;
        else return "";

        //加入POST DATA
        List<NameValuePair> nameValuePair = new ArrayList<>();
        nameValuePair.add(new BasicNameValuePair("gnmkdm", "N100801"));
        nameValuePair.add(new BasicNameValuePair("czdmKey", "00"));

        //POST 返回的是html格式，需要手动处理格式
        String response = this.client.doPost(Constant.urlPersonalInfo+infoParam.getSwuID()+"&_t="+System.currentTimeMillis(), nameValuePair);

        String ret = formatInfo(response);
        return ret;
    }

    String formatInfo(String str){
        //使用正则表达式 将返回格式是html的str字符串 转换为json类型

        /*m1和m2都对str这个字符串同步进行正则匹配
           m1是参数，例如 学院、学号、家庭住址
           m2对应： 计信院、222014321、重庆市
           每匹配到一个m1就会匹配到m1对应的值m2
        */

        //正则表达式  <label class="col-sm-4 control-label" for="" >性别：</label>
        //用来匹配属性
        Pattern p1 = Pattern.compile("<label class=\"col-sm-4 control-label\" for=\"\" >[\\s]*([\\S]*)：[\\s]*</label>");
        Matcher m1 = p1.matcher(str);
        //正则表达式  <p class="form-control-static">计算机与信息科学学院</p>
        //用来匹配属性对应的值
        Pattern p2 = Pattern.compile("<p class=\"form-control-static\">[\\s]*([\\S]*)[\\s]*</p>");
        Matcher m2 = p2.matcher(str);

        //匹配到的属性，例如学号、学院、家庭住址之类的
        String att;
        //匹配到的属性的值例如 计信院、222014321、重庆市
        String value;
        //返回值，类型是json
        String ret="{";
        //将网页上的拼音属性名att替换成英文的
        String name;

        while(m1.find()&&m2.find(m1.end())){
            //每匹配到一个参数
            att= m1.group(1);
            value= m2.group(1);

            if(att.equals(""))continue;

            switch (att){
                case "学号":
                    name="SwuID";
                    break;
                case "姓名":
                    name="name";
                    break;
                case "性别":
                    name="gender";
                    break;
                case "证件号码":
                    name="IDNumber";
                    break;
                case "出生日期":
                    name="birthday";
                    break;
                case "民族":
                    name="nation";
                    break;
                case "政治面貌":
                    name="political";
                    break;
                case "籍贯":
                    name="origin";
                    break;
                case "户口所在地":
                    name="locition";
                    break;
                case "年级":
                    name="grade";
                    break;
                case "学院名称":
                    name="college";
                    break;
                case "专业名称":
                    name="major";
                    break;
                case "班级名称":
                    name="className";
                    break;
                case "毕业中学":
                    name="graduation";
                    break;
                //case "手机号码":
                //    name="phoneNum";
                //    break;
                case "固定电话":
                    name="phoneNum";
                    break;
                case "家庭地址":
                    name="familyLocation";
                    break;
                case "出生地":
                    name="birthplace";
                    break;
                case "系名称":
                    name="department";
                    break;
                case "学生类别":
                    name="englishLevel";
                    break;
                case "考生号":
                    name="examNumber";
                    break;
                case "入学总分":
                    name="graduationScore";
                    break;
                default:
                    name="";
                    value="";
                    break;
            }
            //构建一行记录
            if(!name.equals(""))
                ret=ret+"\""+name+"\":\""+value+"\",";
        }
        //删除多余的逗号，并添加后花括号
        ret=ret.substring(0,ret.length()-1)+"}";

        return ret;
    }

}
