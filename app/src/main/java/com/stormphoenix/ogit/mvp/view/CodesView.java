package com.stormphoenix.ogit.mvp.view;

import com.stormphoenix.ogit.entity.github.GitBlob;
import com.stormphoenix.ogit.mvp.view.base.BaseUIView;

/**
 * Created by wanlei on 18-3-4.
 */
public interface CodesView extends BaseUIView {
    void initWebView();

    void setMarkdown(boolean isMarkdown);

    void setSource(String name, GitBlob blob);
}
