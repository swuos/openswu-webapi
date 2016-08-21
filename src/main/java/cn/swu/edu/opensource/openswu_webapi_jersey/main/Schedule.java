package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.auth.SecurityFilter;
import cn.swu.edu.opensource.openswu_webapi_jersey.exception.ParamException;
import cn.swu.edu.opensource.openswu_webapi_jersey.schedule.ScheduleParam;
import cn.swu.edu.opensource.openswu_webapi_jersey.schedule.SwuSchedule;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.impl.Log4JLogger;
import org.glassfish.jersey.server.ContainerRequest;

import javax.sound.midi.SysexMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/11.
 * <p>
 * Email : sidingchan@gmail.com
 *
 */

@Path("schedule")
public class Schedule {

    private static Log LOGGER = LogFactory.getLog(Schedule.class);

    //利用该类进行基本认证
    SecurityFilter filter = new SecurityFilter();

    //将请求注入
    @Context
    ContainerRequest cr;



    /**
     * json转化为java对象，java对象转换为json的过程由框架完成。
     * @param scheduleParam 接受json格式参数。参数javabean为ScheduleParam
     * @return json字符串。javabean为schedule包下的Schedule类
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public cn.swu.edu.opensource.openswu_webapi_jersey.schedule.Schedule getIt(ScheduleParam scheduleParam){


        filter.filter(cr);

        LOGGER.info("Schedule => " + scheduleParam.toString());

        String response = null;

        try {
            SwuSchedule swuSchedule  = new SwuSchedule(scheduleParam);
            response = swuSchedule.getSchedule();
        } catch (ParamException e) {
            LOGGER.error(e.getMessage());
        }

        return new Gson().fromJson(response, cn.swu.edu.opensource.openswu_webapi_jersey.schedule.Schedule.class);
    }

}
