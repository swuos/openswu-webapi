package com.swu.openswu.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by csd on 2016/3/18.
 */
public class ResultSetToJson {

    public String resultSetToJson(ResultSet rs) {

        JsonArray jsonArray = new JsonArray();

        ResultSetMetaData resultSetMetaData = null;
        try {
            resultSetMetaData = rs.getMetaData();

            int columnCount = resultSetMetaData.getColumnCount();


            while (rs.next()) {

                JsonObject jsonObject = new JsonObject();

                for (int i = 1; i <= columnCount; ++i) {
                    String columnName = resultSetMetaData.getColumnName(i);
                    String value = rs.getString(i);
                    jsonObject.addProperty(columnName, value);
                }
                jsonArray.add(jsonObject);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return jsonArray.toString();
    }

}
