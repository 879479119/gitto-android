package com.stormphoenix.ogit.mvp.view.base;

import com.stormphoenix.ogit.mvp.contract.BaseContract;

/**
 * Created by wanlei on 18-3-10.
 */

public interface ProgressView extends BaseContract.View {
    void showProgress();

    void hideProgress();
}
