package com.swu.openswu.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by csd on 2016/2/24.
 */
public class JsonHandler {


    private Type type = new TypeToken<HashMap<String, String>>() {
    }.getType();

    private Gson gson = new Gson();

    public JsonHandler() {
    }

    /*服务器端的json处理都是通过hashmap来处理的*/
    public HashMap<String, String> fromJson(String json) {
        return gson.fromJson(json, type);
    }

    public String toJson(HashMap<String, String> map) {

        return gson.toJson(map);
    }

    public String toJson(ArrayList list) {
        return gson.toJson(list);
    }


}
