package cn.swu.edu.opensource.openswu_webapi_jersey.login;

import cn.swu.edu.opensource.openswu_webapi_jersey.constant.Constant;
import cn.swu.edu.opensource.openswu_webapi_jersey.exceptions.ParameterException;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Parameter;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/8.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * 扩展Login。进一步封装登陆到教务系统的过程。
 */
public class LoginToEms {

    Client client;

    /**
     * 直接进入教务系统。如果用户名或密码错误，抛出异常。
     * @param parameter 该参数中至少要包含学号和密码。
     * @throws ParameterException 用户不存在或密码错误
     */
    public LoginToEms(Parameter parameter) {

        Login login = new Login(parameter.getSwuID(), parameter.getPassword());


        String response = login.getResponse();

        if(response.contains("用户不存在或密码错误"))
            throw new ParameterException("用户不存在或密码错误");

        this.client = login.getClient();

        client.doGet(Constant.urlEms);
    }

    public Client getClient(){
        return this.client;
    }

}
