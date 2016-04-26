package cn.swu.edu.opensource.openswu_webapi_jersey.grade;

/**
 * Created by 陈思定 on2016/1/28
 */
public interface Lookup {
    //要查询的参数都在SearchParam对象里面
    public String lookup(SearchParam searchParam) throws Exception;


}
