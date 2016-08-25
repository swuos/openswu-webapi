package cn.swu.edu.opensource.openswu_webapi_jersey.ecard;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Lookup;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Param;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/23.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class EcardLookup implements Lookup {

    private EcardParam ecardParam;
    private Client client;

    public EcardLookup() {
        client = new Client();
    }

    @Override
    public String lookup(Param param) {
        ecardParam = (EcardParam) param;

        //Constant.urlEcard = "http://ecard.swu.edu.cn/search/oracle/queryresult.asp?cardno={0}&password={1}"
        String url = MessageFormat.format(Constant.urlEcard, ecardParam.getCardno(), ecardParam.getPassword());

        return htmlParse(client.doGet(url));
    }

    private String htmlParse(String htmlFormatText) {

        try {
            String html = new String(htmlFormatText.getBytes("ISO-8859-1"), "gb2312");

            Matcher attMatcher = Pattern
                    .compile("<div align=\"center\">[\\s]*([\\S]*)[\\s]*</div>")
                    .matcher(html);

            Matcher valueMatcher = Pattern
                    .compile("<div align=\"left\">[\\s]*([\\S]*)[\\s]*</div>")
                    .matcher(html);

            //将html解析的结果存放到map中,用gson将map转换为json返回。
            LinkedHashMap<String, String> resMap = new LinkedHashMap<>();

            String att, value;
            while (attMatcher.find() && valueMatcher.find(attMatcher.end())) {
                att = attMatcher.group(1);
                value = valueMatcher.group(1);

                /*
                * 学校的奇葩html中有很多无用标签,上面valueMatcher.find(attMatcher.end())每次都从找到属性的地方开始找属性值
                * 也是考虑到这些无用却相同的标签
                */
                if (att.equals("") || att == null) continue;

                // switch case 同样不是多余的，att会匹配到一些无用的值，通过switch case匹配需要的值并将其转换为英文属性
                switch (att) {
                    case "姓名":
                        att = "name";
                        break;
                    case "性别":
                        att = "sex";
                        break;
                    case "部门":
                        att = "department";
                        break;
                    case "身份":
                        att = "degree";
                        break;
                    case "证件号码":
                        att = "IDcard";
                        break;
                    case "一卡通号":
                        att = "cardSerialNumber";
                        break;
                    case "显示卡号":
                        att = "cardno";
                        break;
                    case "绑定的银行卡号":
                        att = "bankcard";
                        break;
                    case "借书证号":
                        att = "libraryCard";
                        break;
                    case "卡注册日期":
                        att = "cardRegistrationDate";
                        break;
                    case "卡有效期":
                        att = "cardValidDate";
                        break;
                    case "卡状态":
                        att = "cardStatus";
                        break;
                    default:
                        att = "";
                        break;
                }
                if (!att.equals(""))
                    resMap.put(att, value);
            }
            return new Gson().toJson(resMap);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
        }


        return null;
    }

}
