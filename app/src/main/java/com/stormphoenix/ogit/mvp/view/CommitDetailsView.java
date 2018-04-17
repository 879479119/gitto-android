package com.stormphoenix.ogit.mvp.view;

import com.stormphoenix.ogit.entity.github.GitCommitFile;
import com.stormphoenix.ogit.mvp.view.base.BaseUIView;

import java.util.List;

/**
 * Created by wanlei on 18-3-27.
 */

public interface CommitDetailsView extends BaseUIView {
    void loadListData(List<GitCommitFile> files);

    void initListItemView();
}
