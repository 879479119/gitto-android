package com.stormphoenix.ogit.mvp.presenter.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.stormphoenix.ogit.bridge.Tracker;
import com.stormphoenix.ogit.entity.github.GitEmpty;
import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.entity.log.ABInfo;
import com.stormphoenix.ogit.mvp.model.interactor.LogInteractor;
import com.stormphoenix.ogit.mvp.model.interactor.user.UserInteractor;
import com.stormphoenix.ogit.mvp.presenter.base.OwnerProfilePresenter;
import com.stormphoenix.ogit.mvp.view.UserDetailsView;
import com.stormphoenix.ogit.mvp.view.base.BaseUIView;
import com.stormphoenix.ogit.shares.rx.RxHttpLog;
import com.stormphoenix.ogit.shares.rx.RxJavaCustomTransformer;
import com.stormphoenix.ogit.shares.rx.subscribers.DefaultUiSubscriber;
import com.stormphoenix.ogit.utils.Constants;
import com.stormphoenix.ogit.utils.TextTools;
import com.stormphoenix.ogit.utils.TimeUtils;
import com.stormphoenix.ogit.widget.ImageVerticalKeyValueLabel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by wanlei on 18-3-5.
 */

public class UserProfilePresenter extends OwnerProfilePresenter<UserDetailsView> {
    public static final String TAG = UserProfilePresenter.class.getSimpleName();

    private UserInteractor mInteractor = null;
    private LogInteractor logInteractor;
    private GitUser mUser;

    @Inject
    public UserProfilePresenter(Context context) {
        super(context);
        mInteractor = new UserInteractor(mContext);
        logInteractor = new LogInteractor(mContext);
    }

    public void refreshViewInfo() {
//        mView.loadHeaderImage(mUser.getAvatarUrl());
        mView.showProgress();
//        mView.setOwnerDescription(mUser.getHtmlUrl());
        mInteractor.loadUser(mUser.getLogin())
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe(new Subscriber<Response<GitUser>>() {
                    @Override
                    public void onCompleted() {
                        mView.stopProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.stopProgress();
                        mView.showMessage(e.toString());
                    }

                    @Override
                    public void onNext(Response<GitUser> response) {
                        if (response.isSuccessful()) {
                            mUser = response.body();
                            setUpUserInfo();
                        } else {
                            mView.showMessage(response.message());
                        }
                        mView.stopProgress();
                    }
                });
        mInteractor.loadStaredCount(mUser.getLogin())
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe(new Subscriber<Response<List<GitRepository>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.toString());
                        mView.showMessage(e.toString());
                    }

                    @Override
                    public void onNext(Response<List<GitRepository>> response) {
                        RxHttpLog.logResponse(response);
                        if (response.isSuccessful()) {
                            String linkHeader = response.headers().get("Link");
                            Log.e(TAG, "linkHeader = " + linkHeader);
                            if (!TextUtils.isEmpty(linkHeader)) {
                                int count = TextTools.parseListCount(linkHeader);
                                mView.setStaredCount(String.valueOf(count));
                            }
                        } else {
                            mView.showMessage(response.message());
                        }
                    }
                });
    }

    public void hasFollowed() {
        if (mUser == null) {
            return;
        }
        mInteractor.hasFollowed(mUser.getLogin())
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe(new DefaultUiSubscriber<Response<GitEmpty>, BaseUIView>(mView, mContext.getString(R.string.network_error)) {
                    @Override
                    public void onNext(Response<GitEmpty> response) {
                        if (response.code() == 204) {
                            mView.setIsFollow(true);
                        } else if (response.code() == 404) {
                            mView.setIsFollow(false);
                        } else {
                            mView.showMessage(response.message());
                        }
                    }
                });
    }

    public void unFollow() {
        if (mUser == null) {
            return;
        }
        mInteractor.unFollow(mUser.getLogin())
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe(new DefaultUiSubscriber<Response<GitEmpty>, BaseUIView>(mView, mContext.getString(R.string.network_error)) {
                    @Override
                    public void onNext(Response<GitEmpty> response) {
                        if (response.isSuccessful()) {
                            mView.setIsFollow(false);
                        } else {
                            mView.showMessage(response.message());
                        }
                    }
                });
    }

    public void follow() {
        if (mUser == null) {
            return;
        }
        mInteractor.follow(mUser.getLogin())
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe(new DefaultUiSubscriber<Response<GitEmpty>, BaseUIView>(mView, mContext.getString(R.string.network_error)) {
                    @Override
                    public void onNext(Response<GitEmpty> response) {
                        if (response.isSuccessful()) {
                            mView.setIsFollow(true);
                        } else {
                            mView.showMessage(response.message());
                        }
                    }
                });
    }

    private void setUpUserInfo() {
        Tracker.getInstance().trackPageShow("app://user?" + mUser.getName(), "app://");

        mView.loadHeaderImage(mUser.getAvatarUrl());
        mView.setOwnerDescription(mUser.getHtmlUrl());

        mView.setFollowersCount(String.valueOf(mUser.getFollowers()));
        mView.setFollowingCount(String.valueOf(mUser.getFollowing()));

        mView.initWebView(mUser.getLogin(), "");
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMainThreadEvent(GitUser user) {
        Log.i(TAG, "onMainThreadEvent: ");
        this.mUser = user;
        EventBus.getDefault().unregister(this);
        mView.setUpToolbar(mUser.getLogin());
        refreshViewInfo();
        hasFollowed();
    }
}
