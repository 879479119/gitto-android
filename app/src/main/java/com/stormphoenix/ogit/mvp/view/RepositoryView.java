package com.stormphoenix.ogit.mvp.view;

import com.stormphoenix.ogit.entity.github.GitEmpty;
import com.stormphoenix.ogit.mvp.view.base.BaseUIView;

import retrofit2.Response;

/**
 * Created by wanlei on 18-2-27.
 */
public interface RepositoryView extends BaseUIView {
//    void setDescription(String description);
//
//    void setStarCount(String s);
//
//    void setBranch(String branch);
//
//    void setForkCount(String forkCount);
//
    void setToolbarStatus(String repositoryName, String ownerName);

    void finishView();

    void initWebView(String user, String name);

//    void loadReadmeHtml(String readmeText, String repoHtmlUrl, String defaultBranch);
//
//    void setWatchersCount(String watcher);

    void setIsForked(boolean isFork);

    void setIsStared(boolean hasStared);

    void startForking();

    void stopForking();
}
