package com.stormphoenix.ogit.log.subType;

public class LeaveApp {

    static public enum KILL { USER, SYSTEM, CRASH }

    private long activeTime;

    private KILL killBy;

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public KILL getKillBy() {
        return killBy;
    }

    public void setKillBy(KILL killBy) {
        this.killBy = killBy;
    }
}
