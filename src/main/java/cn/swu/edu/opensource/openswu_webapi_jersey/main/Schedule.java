package cn.swu.edu.opensource.openswu_webapi_jersey.main;

import cn.swu.edu.opensource.openswu_webapi_jersey.schedule.ScheduleParameter;
import cn.swu.edu.opensource.openswu_webapi_jersey.schedule.SwuSchedule;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

    /**
     * json转化为java对象，java对象转换为json的过程由框架完成。
     * @param scheduleParam 接受json格式参数。参数javabean为ScheduleParam
     * @return json字符串。javabean为schedule包下的Schedule类
     */

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public cn.swu.edu.opensource.openswu_webapi_jersey.schedule.Schedule getIt(ScheduleParameter scheduleParam) {

        LOGGER.info("Schedule => " + scheduleParam.toString());

        String response = null;

            SwuSchedule swuSchedule  = new SwuSchedule(scheduleParam);
            response = swuSchedule.getSchedule();


        return new Gson().fromJson(response, cn.swu.edu.opensource.openswu_webapi_jersey.schedule.Schedule.class);
    }

}
