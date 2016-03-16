package com.swu.openswu.lostAndFound;

/**
 * Created by csd on 2016/3/16.
 */
public final class DisplayAll implements Display {

    private DisplaySelf displaySelf;

    public DisplayAll() {
        this.displaySelf = new DisplaySelf(null);
    }

    @Override
    public String display() throws Throwable {
        return this.displaySelf.display();
    }
}
