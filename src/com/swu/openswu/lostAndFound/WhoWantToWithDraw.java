package com.swu.openswu.lostAndFound;

import java.util.Map;

/**
 * Created by csd on 2016/3/13.
 */
public final class WhoWantToWithDraw extends Imformation {


    @Override
    public void getImformation(Map dataParsedFromAppClient) {

        this.swuid = dataParsedFromAppClient.get("swuid").toString();
    }


}
