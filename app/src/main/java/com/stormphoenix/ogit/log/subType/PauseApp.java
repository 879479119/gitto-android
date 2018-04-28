package com.stormphoenix.ogit.log.subType;

public class PauseApp {

    static public enum ACTION { OPEN_URL, CHECKOUT, INPUT, OPEN_APP }

    private long activeTime;

    private ACTION action;

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public ACTION getAction() {
        return action;
    }

    public void setAction(ACTION action) {
        this.action = action;
    }
}
