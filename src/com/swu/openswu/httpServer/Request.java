package com.swu.openswu.httpServer;


import java.io.IOException;

/**
 * Created by 陈思定 on 2016/3/2.
 */
public interface Request {
    /**
     * 获取请求正文
     */
    public String getRequestBody() throws IOException;


}
