package com.stormphoenix.ogit.log.subType;

public class SearchItem {

    public SearchItem(String text, TYPE type) {
        this.text = text;
        this.type = type;
    }

    static public enum TYPE { REPO, USER }

    private String text;

    private TYPE type;

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
