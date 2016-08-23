package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.AuthFilter;
import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
import cn.swu.edu.opensource.openswu_webapi_jersey.quitnet.Quit;
import cn.swu.edu.opensource.openswu_webapi_jersey.quitnet.QuitNetParam;
import cn.swu.edu.opensource.openswu_webapi_jersey.quitnet.QuitNetQueue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.server.ContainerRequest;
import sun.nio.cs.ext.GBK;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/29.
 * <p>
 * Email : sidingchan@gmail.com
 */

@Path("quitnet")
public class QuitNet {

    private static Log LOGGER = LogFactory.getLog(QuitNet.class);

    @Context
    ContainerRequest cr;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(QuitNetParam quitNetParam){

//        /* 完成认证 */
        SecurityFilter.filter(cr);
//        new AuthFilter().filter(cr);
        /* 需要立即退网 */

        LOGGER.info("QuitNet => " + quitNetParam.toString());

        long d = quitNetParam.getDate();
        if (d == 0L) {
            Quit quiter = new Quit();
            quiter.doQuit(quitNetParam.getUsername(),quitNetParam.getPassword());

            /*  quiter.getResponse() 是信息中心在退网中产生的应答。
            *
            *   this.getResponse(String response)方法处理这个应答，返回我们需要返回给调用者的应答。
            *
            * */
            return this.getResponse(quiter.getResponse());
        } else if (d == -1L) {
            /* 取消定时退网 */
            if (QuitNetQueue.getInstance().removeItem(quitNetParam.getUsername())) {
                return "取消定时退网成功";
            }
            return "取消定时退网失败";
        } else {
        /* 需要延时退网 */
            QuitNetQueue.getInstance().addItem(quitNetParam);

            return "定时退网排队成功";
        }


    }

    /*
     *  谁做的这个退网的系统,真的是脑子有屎。
     *  为了解决蛋疼的编码问题，这里直接用相应的长度来判断发生的响应情况，并返回给用户合理的应答。
     *  一般来说：
     *  1.正确退网长度应该为800+
     *  2.账号没有登陆长度500-
     *
     * @param 退网时信息中心返回的应答。
     * @return 我们返回给调用者的应答。
     */
    private String getResponse(String response){
        int length = response.length();

        if(length>800)
            return "退网成功。";
        else if(length>700)
            return "用户名或密码错误。";
        else
            return "您的账户当前没有登陆。";

    }
}
