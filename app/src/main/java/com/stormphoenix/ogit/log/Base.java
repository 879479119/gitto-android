package com.stormphoenix.ogit.log;

import java.util.Date;

public class Base {

    private int type;

    private int subType;

    private int app;

    private int platform;

    private int version;

    private String sessionId;

    private String name;

    private Date clientTime;

    private String url;

    private String versionHash;

    public String getVersionHash() {
        return versionHash;
    }

    public void setVersionHash(String versionHash) {
        this.versionHash = versionHash;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSubType() {
        return subType;
    }

    public void setSubType(int subType) {
        this.subType = subType;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getClientTime() {
        return clientTime;
    }

    public void setClientTime(Date clientTime) {
        this.clientTime = clientTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
