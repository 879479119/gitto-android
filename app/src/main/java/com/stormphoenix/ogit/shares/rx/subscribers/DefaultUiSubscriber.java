package com.stormphoenix.ogit.shares.rx.subscribers;

import android.util.Log;

import com.stormphoenix.ogit.mvp.contract.BaseContract;

import rx.Subscriber;

/**
 * Created by wanlei on 18-3-10.
 */

public abstract class DefaultUiSubscriber<T, V extends BaseContract.View> extends Subscriber<T> {
    public static final String TAG = DefaultUiSubscriber.class.getSimpleName();

    private V ui;
    private String errorMessage;

    public DefaultUiSubscriber(V ui, String errorMessage) {
        this.ui = ui;
        this.errorMessage = errorMessage;
    }

    @Override
    public void onStart() {
        super.onStart();
        ui.showProgress();
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, "onError: ");
        ui.hideProgress();
        ui.showMessage(e.toString());
    }

    @Override
    public void onCompleted() {
        ui.hideProgress();
    }
}
