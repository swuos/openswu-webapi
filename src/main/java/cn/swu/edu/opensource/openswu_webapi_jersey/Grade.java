package cn.swu.edu.opensource.openswu_webapi_jersey;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
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
 * @author 陈思定/chensiding@qq.com
 */
@Path("grade")
public class Grade {

    SecurityFilter filter = new SecurityFilter();
    //注入请求
    @Context
    ContainerRequest cr;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GradeData getIt(SearchParam param) {
        filter.filter(cr);
        //登录


        Login login = new Login(param.getSwuID(), param.getPassword());

        //检查用户名和密码是否正确
        if (login.getResponse().contains("用户不存在或密码错误")) {
            return responseForError("用户不存在或密码错误");
        }


        //进入教务系统
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
     * "kcmc": "用户不存在或密码错误"
     * }
     * ]
     * }
     * </pre>
     *
     * @param errorString
     * @return json which contain error
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

}
