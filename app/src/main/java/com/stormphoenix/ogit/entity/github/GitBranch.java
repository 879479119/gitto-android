package com.stormphoenix.ogit.entity.github;

/**
 * Created by wanlei on 18-2-27.
 */

public class GitBranch {
    private String name;
    private GitCommitMessage commit;

    public GitBranch() {
    }

    public String getName() {
        return this.name;
    }

    public GitBranch setName(String name) {
        this.name = name;
        return this;
    }

    public GitCommitMessage getCommit() {
        return commit;
    }

    public void setCommit(GitCommitMessage commit) {
        this.commit = commit;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "commit=" + commit +
                ", name='" + name + '\'' +
                '}';
    }
}
