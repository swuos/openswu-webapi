package com.swu.openswu.lostAndFound;

/**
 * Created by csd on 2016/3/16.
 */
public class SimpleFindHost implements Register {

    /*findhost也需要登记，所以实现Register接口*/
    private SimpleRegister simpleRegister;

    public SimpleFindHost() {
        this.simpleRegister = new SimpleRegister();
    }

    @Override
    public void register(Imformation imformationAboutRegister) throws Throwable {
        this.register(imformationAboutRegister);
    }
}
