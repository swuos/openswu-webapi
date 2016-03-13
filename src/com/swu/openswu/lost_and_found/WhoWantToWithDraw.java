package com.swu.openswu.lost_and_found;

import java.util.Map;

/**
 * Created by csd on 2016/3/13.
 */
public class WhoWantToWithDraw extends Imformation {


    @Override
    public void getImformation(Map dataParsedFromAppClient) {

        this.swuid = dataParsedFromAppClient.get("swuid").toString();
        this.done = (int) dataParsedFromAppClient.get("int");

    }


}
