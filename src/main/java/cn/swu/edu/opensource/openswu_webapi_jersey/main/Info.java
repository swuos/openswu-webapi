package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
import cn.swu.edu.opensource.openswu_webapi_jersey.exception.ParamException;
import cn.swu.edu.opensource.openswu_webapi_jersey.info.InfoParam;
import cn.swu.edu.opensource.openswu_webapi_jersey.info.PersonalInfo;
import cn.swu.edu.opensource.openswu_webapi_jersey.info.SwuInfo;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.server.ContainerRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by mxd on 2016/5/18.
 * 使用此功能可以查看学生的基本信息
 * post地址：http://localhost:8080/openswu/info
 * post时只需要两个参数：swuID和password
 */

@Path("info")
public class Info {

    //利用该类进行基本认证
    SecurityFilter filter = new SecurityFilter();

    private static Log LOGGER = LogFactory.getLog(Info.class);
    //将请求注入
    @Context
    ContainerRequest cr;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PersonalInfo getIt(InfoParam infoParam){
        filter.filter(cr);
        String response = null;

        LOGGER.info("Info => " + infoParam.toString());

        try {
            SwuInfo swuInfo  = new SwuInfo(infoParam);
            response = swuInfo.getInfo();
        } catch (ParamException e) {
            LOGGER.error(e.getMessage());
        }

        return new Gson().fromJson(response, PersonalInfo.class);
    }

}
