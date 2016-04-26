package cn.swu.edu.opensource.openswu_webapi_jersey.utils;

import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.swu.edu.opensource.openswu_webapi_jersey.grade.GradeData;

/**
 * Created by csd on 2016/2/24.
 */
public class JsonHandler {


    private Type type = new TypeToken<HashMap<String, String>>() {
    }.getType();

    private Gson gson = new Gson();

    public JsonHandler() {
    }

    //处理形式为键值对的json
    public HashMap<String, String> fromJson(String json) {
        return gson.fromJson(json, type);
    }

    public String toJson(HashMap<String, String> map) {

        return gson.toJson(map);
    }

    public String toJson(GradeData gradeData) {
        return gson.toJson(gradeData.getItems());
    }


}
