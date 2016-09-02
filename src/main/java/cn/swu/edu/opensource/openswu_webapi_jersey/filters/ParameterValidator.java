package cn.swu.edu.opensource.openswu_webapi_jersey.filters;

import cn.swu.edu.opensource.openswu_webapi_jersey.exceptions.ParameterException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.*;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/9/1.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * <p>在请求匹配到路径对应的方法前，对请求参数进行简单的有效性验证。</p>
 * <p>若接受到的json请求中，某项数据为空则显然是无效的</p>
 * <p>当请求无效的情况发生时，携带着相关信息返回</p>
 */

@Provider
public class ParameterValidator implements ContainerRequestFilter{

    StringBuffer entity;

    @Override
    public void filter(ContainerRequestContext requestContext){
        System.out.println("validator in");
        if(requestContext.hasEntity()) {
            // initialize entity.
            setEntity(requestContext);

            System.out.println(entity);


        }else{
            throw new ParameterException("Parameters are required.");
        }
    }


    public void setEntity(ContainerRequestContext requestContext){

        this.entity = new StringBuffer();
        String t;
        BufferedReader bfReader = new BufferedReader(new InputStreamReader(requestContext.getEntityStream()));
        try {
            while((t=bfReader.readLine()) != null){
                entity.append(t);
            }
        } catch (IOException e) {
            throw new ParameterException("server problem.try again.");
        } finally {
            try {
                bfReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
