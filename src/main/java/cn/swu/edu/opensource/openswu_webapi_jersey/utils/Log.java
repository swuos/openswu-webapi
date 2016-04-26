package cn.swu.edu.opensource.openswu_webapi_jersey.utils;

/**
 * Created by csd on 2016/3/18.
 */


public class Log {

    /*
     *  还没想好要怎么写。
     */
    private volatile static Log log;


    private Log() {
    }

    public static Log getLog() {
        if (log == null) {
            synchronized (Log.class) {
                if (log == null) {
                    log = new Log();
                }
            }
        }
        return log;
    }


}
