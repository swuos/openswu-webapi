package cn.swu.edu.opensource.openswu_webapi_jersey.library;

import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Parameter;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/31.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class LibraryParameter implements Parameter {

    private String swuID;
    private String password;

    public void setSwuID(String swuID) {
        this.swuID = swuID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getSwuID() {
        return this.swuID;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
