package cn.swu.edu.opensource.openswu_webapi_jersey.interfaces;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/8.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * 该接口为所有查询类服务所接受参数需实现的共同接口。
 */
public interface Parameter {

    public String getSwuID();

    public String getPassword();

    /*
    实现了该接口的类必须重写toString()
    public String toString();
    */

}
