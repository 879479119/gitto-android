package com.stormphoenix.ogit.mvp.view;

import com.stormphoenix.ogit.mvp.view.base.BaseUIView;

/**
 * Created by wanlei on 18-2-26.
 */

public interface LoginView extends BaseUIView {
    void onLoginSuccess();

    void finishView();

    void startMainActivity();
}
