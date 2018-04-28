package com.stormphoenix.ogit.log.subType;

public class Script {

    static public enum ERROR_TYPE { ERROR, TYPE_ERROR, PROMISE_ERROR }

    private ERROR_TYPE errorType;

    private String file;

    private String line;

    public ERROR_TYPE getErrorType() {
        return errorType;
    }

    public void setErrorType(ERROR_TYPE errorType) {
        this.errorType = errorType;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
