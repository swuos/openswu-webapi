package com.swu.openswu.lostAndFound;

import java.util.Map;

/**
 * Created by csd on 2016/3/13.
 */
public abstract class Information {
    /*
            学号
            完成情况            0丢失需登记 1已找回需撤销
         */
    protected String swuid;
    protected int done;

    public abstract void getImformation(Map dataParsedFromAppClient);

    public String getSwuid() {
        return swuid;
    }

    public int getDone() {
        return done;
    }


}
