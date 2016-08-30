package cn.swu.edu.opensource.openswu_webapi_jersey.exception;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/5/8.
 * <p>
 * Email : sidingchan@gmail.com
 *
 * 参数错误异常类
 */
public class ParameterException extends RuntimeException {

    public ParameterException(String message) {
        super(message);
    }

    private String realm;

    public ParameterException(String message, String realm) {
        super(message);
        this.realm = realm;
    }

    public String getRealm() {
        return realm;
    }
}
