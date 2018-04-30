package com.stormphoenix.ogit.mvp.presenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.stormphoenix.ogit.entity.github.GitNotification;
import com.stormphoenix.ogit.entity.log.ABInfo;
import com.stormphoenix.ogit.mvp.model.interactor.LogInteractor;
import com.stormphoenix.ogit.mvp.model.interactor.user.NotifyInteractor;
import com.stormphoenix.ogit.mvp.view.MainView;
import com.stormphoenix.ogit.shares.rx.RxJavaCustomTransformer;
import com.stormphoenix.ogit.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by wanlei on 18-2-26.
 */

public class MainPresenter extends BasePresenter<MainView> {
    public static final String TAG = MainPresenter.class.getSimpleName();
    private Context mContext;
    private NotifyInteractor mInteractor;
    private LogInteractor logInteractor;


    @Inject
    public MainPresenter(Context context) {
        mContext = context;
        mInteractor = new NotifyInteractor(context);
        logInteractor = new LogInteractor(context);
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        loadNotification();
    }

    public void fetchABInfo() {
        logInteractor.getABInfo(PreferenceUtils.getUsername(mContext))
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe((Observer<? super Response<com.stormphoenix.ogit.entity.log.Response<ABInfo>>>) new Subscriber<Response<com.stormphoenix.ogit.entity.log.Response<ABInfo>>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.toString());
                    }

                    @Override
                    public void onNext(Response<com.stormphoenix.ogit.entity.log.Response<ABInfo>> response) {
                        if (response.isSuccessful()) {
                            Gson gson = new Gson();

                            PreferenceUtils.putString(mContext, PreferenceUtils.ABTEST, gson.toJson(response.body().getData()));

                            Log.i(TAG, "onNext: " + gson.toJson(response.body()));
                        } else {
                            mView.showMessage(response.message());
                        }
                    }
                });
    }

    public void loadNotification() {
        mInteractor.loadNotification().compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe(new Subscriber<Response<List<GitNotification>>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showMessage(e.toString());
                    }

                    @Override
                    public void onNext(Response<List<GitNotification>> response) {
                        Log.e(TAG, "onNext: ");
                        mView.saveNotificationMessage(response.body());
                    }
                });
    }
}





