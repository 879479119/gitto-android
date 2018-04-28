package com.stormphoenix.ogit.log.subType;

public class EnterApp {

    static public enum SOURCE { URL, USER, SYSTEM, NOTIFICATION }

    private SOURCE source;

    private long loadTime;

    private boolean splashJump;

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
