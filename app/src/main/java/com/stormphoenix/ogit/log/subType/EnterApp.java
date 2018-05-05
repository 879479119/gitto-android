package com.stormphoenix.ogit.log.subType;

import com.stormphoenix.ogit.log.Detail;

import java.util.Date;

public class EnterApp {

    public EnterApp(Date date, SOURCE source, long loadTime, boolean splashJump) {
        this.date = date;
        this.source = source;
        this.loadTime = loadTime;
        this.splashJump = splashJump;
    }

    static public enum SOURCE { URL, USER, SYSTEM, NOTIFICATION }

    private Date date;

    private SOURCE source;

    private long loadTime;

    private boolean splashJump;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SOURCE getSource() {
        return source;
    }

    public void setSource(SOURCE source) {
        this.source = source;
    }

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public boolean isSplashJump() {
        return splashJump;
    }

    public void setSplashJump(boolean splashJump) {
        this.splashJump = splashJump;
    }
}
