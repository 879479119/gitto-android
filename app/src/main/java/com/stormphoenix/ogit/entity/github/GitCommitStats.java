package com.stormphoenix.ogit.entity.github;

/**
 * Created by wanlei on 18-3-23.
 */

public class GitCommitStats {
    private int total;
    private int additions;
    private int deletions;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAdditions() {
        return additions;
    }

    public void setAdditions(int additions) {
        this.additions = additions;
    }

    public int getDeletions() {
        return deletions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }
}
