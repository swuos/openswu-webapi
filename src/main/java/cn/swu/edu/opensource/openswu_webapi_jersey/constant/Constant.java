package cn.swu.edu.opensource.openswu_webapi_jersey.constant;

/**
 * Created by 张孟尧 on 2016/1/10.
 *
 * Modified by chensiding@gmail.com
 */
public class Constant {
    public static final int TIMEOUT = 4000;
    //    设置Message标记
    public static final int SHOW_RESPONSE = 0;
    public static final int ERROR = 1;
    public static final int DELETE = 2;

    public static final int LOGIN_SUCCESE = 3;
    public static final int LOGIN_FAILED = 4;
    public static final int GRADES_OK = 5;
    public static final int UPDATA = 6;
    public static final int MAIN = 7;
    public static final int SHOW = 8;
    public static final int DISSHOW = 9;
    public static String NO_NET = "网络出现了问题";
    public static final String CLIENT_OK = "成功";
    public static final String CLIENT_ERROR = "连接出错";
    public static final String XQM_ONE = "3";
    public static final String XQM_TWO = "12";
    public static final String XQM_THREE = "16";
    public static final String XQM_ALL = "";
    public static final String[] ALL_XQM = {XQM_ALL, XQM_ONE, XQM_TWO, XQM_THREE};
    public static final String[] ALL_XNM = {"", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010"};
    public static final String NET_TIMEOUT = "NET_TIMEOUT";

    //    校内门户地址
    public static final String urlUrp = "http://urp6.swu.edu.cn/login.portal";
    //    用户信息发送目标地址
    public static final String urlLogin = "http://urp6.swu.edu.cn/userPasswordValidate.portal";
    //    登陆后跳转网页
    public static final String urlPortal = "http://urp6.swu.edu.cn/index.portal";
    //    #教务系统网站 Ems意为swu Educational management system
    public static final String urlEms = "http://jw.swu.edu.cn/jwglxt/idstar/index.jsp";
    public static final String urlJW = "http://jw.swu.edu.cn/jwglxt/xtgl/index_initMenu.html";
    /*登陆校内门户是post的两个重要参数*/
    //goto 是java保留字
    public static final String gotos = "http://urp6.swu.edu.cn/loginSuccess.portal";
    public static final String gotoOnFail = "http://urp6.swu.edu.cn/loginFailure.portal";
    //查询成绩地址
    public static final String urlGradeSearch = "http://jw.swu.edu.cn/jwglxt/cjcx/cjcx_cxDgXscj.html?" +
            "doType=query&gnmkdmKey=N305005&sessionUserKey=";
    //查询课表地址
    public static final String urlScheduleSearch ="http://jw.swu.edu.cn/jwglxt/kbcx/xskbcx_cxXsKb.html?" +
            "gnmkdmKey=N253508&sessionUserKey=";
    //退网地址
    public static final String urlQuitNet="http://service.swu.edu.cn/fee/remote_logout2.jsp";

    //查询个人基本信息
    public static final String  urlPersonalInfo ="http://jw.swu.edu.cn/jwglxt/xsxxxggl/xsgrxxwh_cxXsgrxx.html?"+
            "gnmkdmKey=N100801&sessionUserKey=";

    // 查询校园卡个人信息
    public static final String urlEcard = "http://ecard.swu.edu.cn/search/oracle/queryresult.asp?cardno={0}&password={1}";
    // 查询校园卡交易明细
    public static final String urlEcardFinance = "http://ecard.swu.edu.cn/search/oracle/finance.asp";
    // 西南大学统一认证
    public static final String urlUnifiedAuthentication = "http://ids1.swu.edu.cn:81/amserver/UI/Login";
    // 图书馆登录
    public static final String urlLibrary = "http://mylib.swu.edu.cn/do.jsp?ac=c64db6a3accfd5d750e9b26c1ad46d76&ref";

}

