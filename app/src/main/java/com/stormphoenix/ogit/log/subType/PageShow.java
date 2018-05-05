package com.stormphoenix.ogit.log.subType;

public class PageShow {

    public PageShow(String sourceUrl, String url) {
        this.sourceUrl = sourceUrl;
        this.url = url;
    }

    private String sourceUrl;

    private String url;

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
