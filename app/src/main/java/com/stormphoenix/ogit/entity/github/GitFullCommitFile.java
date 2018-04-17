package com.stormphoenix.ogit.entity.github;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wanlei on 18-3-26.
 */

public class GitFullCommitFile {
    private GitCommitFile commitFile;
    private SparseArray<List<GitCommitComment>> comments = new SparseArray<List<GitCommitComment>>(4);

    public GitFullCommitFile(GitCommitFile commitFile) {
        this.commitFile = commitFile;
    }

    public List<GitCommitComment> get(final int line) {
        List<GitCommitComment> lineComments = this.comments.get(line);
        return lineComments != null ? lineComments : Collections
                .<GitCommitComment>emptyList();
    }

    public GitFullCommitFile add(final GitCommitComment comment) {
        int line = comment.getPosition();
        if (line >= 0) {
            List<GitCommitComment> lineComments = comments.get(line);
            if (lineComments == null) {
                lineComments = new ArrayList<GitCommitComment>(4);
                comments.put(line, lineComments);
            }
            lineComments.add(comment);
        }
        return this;
    }

    public GitCommitFile getCommitFile() {
        return commitFile;
    }
}
