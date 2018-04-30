package com.stormphoenix.ogit.log;

import java.util.List;

public class Log {

    private Base base;

    private List<AB> abList;

    private Detail detail;

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public List<AB> getAbList() {
        return abList;
    }

    public void setAbList(List<AB> abList) {
        this.abList = abList;
    }

    public Detail getDetail() {
        return detail;
    }

    public void setDetail(Detail detail) {
        this.detail = detail;
    }
}
