package com.stormphoenix.ogit.entity.github.payload;

import com.stormphoenix.ogit.entity.github.GitRelease;

/**
 * Created by wanlei on 18-3-11.
 */

public class GitReleasePayload extends GitPayload {
    private String action;
    private GitRelease release;

    public GitRelease getRelease() {
        return release;
    }

    public void setRelease(GitRelease release) {
        this.release = release;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
