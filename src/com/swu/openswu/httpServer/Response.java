package com.swu.openswu.httpServer;

/**
 * Created by 陈思定 on 2016/3/2.
 */
public interface Response {

    /**
     * 发送响应
     */

    public String sendResponse(String responseBody);
}
