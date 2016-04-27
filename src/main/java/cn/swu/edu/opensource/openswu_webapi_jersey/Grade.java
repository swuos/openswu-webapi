package cn.swu.edu.opensource.openswu_webapi_jersey;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.GradeData;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.SearchParam;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.SwuGrades;
import cn.swu.edu.opensource.openswu_webapi_jersey.grade.TotalInfo;
import cn.swu.edu.opensource.openswu_webapi_jersey.login.Login;
import com.google.gson.Gson;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * 查询成绩的类。由于直接由之前版本迁移而来，所以代码可能存在冗余，项目发展到一定时候会回过头来重新修改这个模块。
 * 也希望任何人自行修改。
 * @author chensiding/chensiding@qq.com
 *
 */
@Path("grade")
public class Grade {

    //利用该类进行基本认证
    SecurityFilter filter = new SecurityFilter();

    //将请求注入
    @Context
    ContainerRequest cr;

    /**
     * 处理POST请求。
     * MOXy会将接受到的json串自动转化java对象，也可将java对象转化为json串输出。
     * 查询成绩中我们使用grade包下的GradeData作为POJO进行互相转化。
     *
     * @param param 符合规则的json
     * @return 成功查询则返回查询结果，不成功则返回报错信息。
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GradeData getIt(SearchParam param) {
        filter.filter(cr);

        /*登录*/
        Login login = new Login(param.getSwuID(), param.getPassword());

        /*检查用户名和密码是否正确*/
        if (login.getResponse().contains("用户不存在或密码错误")) {
            return responseForError("用户不存在或密码错误");
        }
        /*检查学年和学期号是否合法*/
        if (!(contain(new String[]{"1", "2", "3"}, param.getXqm())
                && contain(Constant.ALL_XNM, param.getXnm()))) {
            return responseForError("学年或学期号不合法");
        }

        /*进入教务系统*/
        TotalInfo info = login.getBasicInfo();

        SwuGrades grades = new SwuGrades(login.getClient());
        Gson gson = new Gson();
        info.setGrades(gson.fromJson(grades.lookup(param), GradeData.class));

        return info.getGrades();
    }

    /**
     * 传入需要返回的错误信息字符串。将错误信息封装在返回的json中，格式不会发生变化，kcmc对应的值会变为错误信息，如：
     * <pre>
     * {
     * "items": [
     * {
     * "kcmc": "errorString"
     * }
     * ]
     * }
     * </pre>
     *
     * @param errorString
     * @return 包含错误信息的json串
     */
    private GradeData responseForError(String errorString) {
        GradeData gradeData = new GradeData();
        GradeData.Items errorItem = new GradeData.Items();
        errorItem.setKcmc(errorString);
        ArrayList<GradeData.Items> array = new ArrayList<>();
        array.add(errorItem);
        gradeData.setItems(array);
        return gradeData;
    }

    /**
     * 判断src中是否存在sub或sub是否为0（在接收参数的过程中，若post过来的某参数值为空。我们会将其默认赋值为0）
     * 当传入的参数在合法值数组内或者为0时都认为是合法的，否则不合法。
     *
     * @param src 储存参数合法值的数组
     * @param sub 某参数值
     * @return 参数值是否合法
     */
    private Boolean contain(String[] src, Integer sub) {
        for (String s :
                src) {
            if (sub == 0 || s.equals(sub.toString())) return true;
        }
        return false;
    }

}
