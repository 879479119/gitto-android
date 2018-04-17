package com.stormphoenix.ogit.entity.github.payload;

import com.stormphoenix.ogit.entity.github.GitCommitComment;

/**
 * Created by wanlei on 18-4-1.
 */

public class GitCommitCommentPayload extends GitPayload {
    private GitCommitComment comment;

    public GitCommitComment getComment() {
        return comment;
    }

    public void setComment(GitCommitComment comment) {
        this.comment = comment;
    }
}
