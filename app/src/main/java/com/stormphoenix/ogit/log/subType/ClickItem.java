package com.stormphoenix.ogit.log.subType;

public class ClickItem {

    static public enum TYPE {BUTTON, VIEW, IMG, OTHER}

    static public enum ORIGIN {SCREEN, WEBVIEW, VIEW}

    private String id;

    private TYPE type;

    private ORIGIN origin;

    private String originId;

    private double x;

    private double y;

    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public ORIGIN getOrigin() {
        return origin;
    }

    public void setOrigin(ORIGIN origin) {
        this.origin = origin;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
