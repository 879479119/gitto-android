package com.stormphoenix.ogit.entity.github.payload;

import com.stormphoenix.ogit.entity.GitIssueComment;
import com.stormphoenix.ogit.entity.github.GitIssue;

/**
 * Created by wanlei on 18-3-18.
 */

public class GitIssueCommentPayload extends GitPayload {
    private String action;
    private GitIssue issue;
    private GitIssueComment comment;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public GitIssue getIssue() {
        return issue;
    }

    public void setIssue(GitIssue issue) {
        this.issue = issue;
    }

    public GitIssueComment getComment() {
        return comment;
    }

    public void setComment(GitIssueComment comment) {
        this.comment = comment;
    }
}
