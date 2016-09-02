package cn.swu.edu.opensource.openswu_webapi_jersey.library;

import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Lookup;
import cn.swu.edu.opensource.openswu_webapi_jersey.interfaces.Parameter;
import cn.swu.edu.opensource.openswu_webapi_jersey.utils.Client;

/**
 * Created by 西南大学开源协会 陈思定  on 2016/8/23.
 * <p>
 * Email : sidingchan@gmail.com
 */
public class History extends AbsLibraryLookuper {

    private Client client;

    @Override
    public String lookup(Parameter parameter) {
        loginInLibrarySystem(parameter);
        return "finish lookup";
    }
}
