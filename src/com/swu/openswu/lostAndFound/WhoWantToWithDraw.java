package com.swu.openswu.lostAndFound;

import java.util.Map;

/**
 * Created by csd on 2016/3/13.
 */
public final class WhoWantToWithDraw extends Information {


    String id;

    @Override
    public void getImformation(Map dataParsedFromAppClient) {

        this.id = dataParsedFromAppClient.get("id").toString();
    }

    public String getId() {
        return id;
    }
}
