package com.stormphoenix.ogit.log.subType;

public class Image {

    static public enum REASON { UNLOAD, UNSUPPORTED, FORBIDDEN, VIOLATION }

    private REASON reason;

    private String url;

    public REASON getReason() {
        return reason;
    }

    public void setReason(REASON reason) {
        this.reason = reason;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
