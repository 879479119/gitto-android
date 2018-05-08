package com.stormphoenix.ogit.log.subType;

import java.util.Date;

public class View {

    public View(long time, String url, Date startTime, Date endTime) {
        this.time = time;
        this.url = url;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private long time;

    private String url;

    private Date startTime;

    private Date endTime;

    public long getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
