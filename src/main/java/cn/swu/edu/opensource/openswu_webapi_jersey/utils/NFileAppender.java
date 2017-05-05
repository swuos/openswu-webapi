package cn.swu.edu.opensource.openswu_webapi_jersey.utils;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/21.
 * <p>
 * Email : sidingchan@gmail.com
 * <p>
 * 重载日志处理类，使打印日志时只打印相应等级的日志，而不需将高于其优先级的日志打印出来。
 */
public class NFileAppender extends DailyRollingFileAppender {

    @Override
    public boolean isAsSevereAsThreshold(Priority priority) {
        return this.getThreshold().equals(priority);
    }
}
