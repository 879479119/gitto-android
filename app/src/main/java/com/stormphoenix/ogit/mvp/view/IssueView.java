package com.stormphoenix.ogit.mvp.view;

import com.stormphoenix.ogit.mvp.view.base.BaseUIView;

/**
 * Created by wanlei on 18-4-11.
 */

public interface IssueView extends BaseUIView {
    void onSendIssueSuccess();
    void onSendIssueFailed();
}
