package com.swu.openswu.lostAndFound;

/**
 * Created by csd on 2016/3/12.
 */
public interface Withdraw {

    //撤销登记
    public void withdraw(Information informationAboutWithDraw) throws Throwable;
}
