package com.stormphoenix.ogit.entity.github;

import java.util.List;

/**
 * Created by wanlei on 18-3-2.
 */

public class GitTree {
    private String sha;
    private String url;
    private List<GitTreeItem> tree;

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<GitTreeItem> getTree() {
        return tree;
    }

    public void setTree(List<GitTreeItem> tree) {
        this.tree = tree;
    }
}
