package com.stormphoenix.ogit.mvp.presenter.user;

import android.content.Context;
import android.os.Bundle;

import com.google.gson.Gson;
import com.stormphoenix.ogit.entity.github.GitNotification;
import com.stormphoenix.ogit.cache.FileCache;
import com.stormphoenix.ogit.mvp.presenter.base.ListItemPresenter;
import com.stormphoenix.ogit.mvp.view.base.ListItemView;
import com.stormphoenix.ogit.shares.rx.RxJavaCustomTransformer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by wanlei on 18-3-18.
 */

public class NotifyPresenter extends ListItemPresenter<GitNotification, List<GitNotification>, ListItemView<GitNotification>> {
    private List<GitNotification> notifications = null;

    @Inject
    public NotifyPresenter(Context context) {
        super(context);
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected List<GitNotification> transformBody(List<GitNotification> body) {
        return body;
    }

    @Override
    protected void makeDataCached(List<GitNotification> data) {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Gson gson = new Gson();
                subscriber.onNext(gson.toJson(data));
            }
        }).compose(RxJavaCustomTransformer.defaultSchedulers()).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                FileCache.saveCacheFile(getCacheType(), s);
            }
        });
    }

    @Override
    protected FileCache.CacheType getCacheType() {
        return null;
    }

    @Override
    public void loadMoreListItem() {
        // Do nothing
    }

    @Override
    public void loadNewlyListItem() {
        load(0);
    }

    @Override
    protected Observable<Response<List<GitNotification>>> load(int page) {
        if (notifications == null) {
            return null;
        } else {
            mView.loadNewlyListItem(notifications);
            mView.hideProgress();
            return null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMainThreadEvent(List<GitNotification> notifications) {
        this.notifications = notifications;
        EventBus.getDefault().unregister(this);
        loadNewlyListItem();
    }
}
