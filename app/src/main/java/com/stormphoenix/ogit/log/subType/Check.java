package com.stormphoenix.ogit.log.subType;

import java.util.Date;

public class Check {

    private int logCount;

    private int retrialTimes;

    private Date recentTrail;

    private Date lastSuccess;

    public int getLogCount() {
        return logCount;
    }

    public void setLogCount(int logCount) {
        this.logCount = logCount;
    }

    public int getRetrialTimes() {
        return retrialTimes;
    }

    public void setRetrialTimes(int retrialTimes) {
        this.retrialTimes = retrialTimes;
    }

    public Date getRecentTrail() {
        return recentTrail;
    }

    public void setRecentTrail(Date recentTrail) {
        this.recentTrail = recentTrail;
    }

    public Date getLastSuccess() {
        return lastSuccess;
    }

    public void setLastSuccess(Date lastSuccess) {
        this.lastSuccess = lastSuccess;
    }
}
