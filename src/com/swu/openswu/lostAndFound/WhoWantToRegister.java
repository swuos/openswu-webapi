package com.swu.openswu.lostAndFound;

import java.util.Map;

/**
 * Created by csd on 2016/3/13.
 */
public final class WhoWantToRegister extends Imformation {

    /*
            内容
            备注
            时间                UNIX时间戳
            地点
     */
    private String text;
    private String details;
    private String time;
    private String place;


    @Override
    public void getImformation(Map dataParsedFromAppClient) {

        this.swuid = dataParsedFromAppClient.get("swuid").toString();
        this.text = dataParsedFromAppClient.get("text").toString();
        this.details = dataParsedFromAppClient.get("details").toString();
        this.time = dataParsedFromAppClient.get("time").toString();
        this.place = dataParsedFromAppClient.get("place").toString();
    }

    public String getText() {
        return text;
    }

    public String getDetails() {
        return details;
    }

    public String getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

}
