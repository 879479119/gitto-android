package com.stormphoenix.ogit.entity.log;

import java.util.List;

public class ABInfo {

    private List<Detail> detail;

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }
}

class Detail {

    private int testId;

    private int paramsId;

    private String params;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getParamsId() {
        return paramsId;
    }

    public void setParamsId(int paramsId) {
        this.paramsId = paramsId;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}