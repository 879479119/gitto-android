package com.stormphoenix.ogit.mvp.presenter.user;

import android.content.Context;
import android.content.Intent;

import com.stormphoenix.ogit.entity.github.GitEvent;
import com.stormphoenix.ogit.cache.FileCache;
import com.stormphoenix.ogit.mvp.model.interactor.user.UserInteractor;
import com.stormphoenix.ogit.mvp.presenter.base.EventsPresenter;
import com.stormphoenix.ogit.mvp.ui.activities.RepositoryActivity;
import com.stormphoenix.ogit.mvp.ui.activities.UserProfileActivity;
import com.stormphoenix.ogit.utils.ActivityUtils;
import com.stormphoenix.ogit.utils.NetworkUtils;
import com.stormphoenix.ogit.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-2-25.
 */

public class UserReceivedEventsPresenter extends EventsPresenter {
    /**
     * Interactor 用于提交网络请求获取数据
     **/
    private UserInteractor mInfoInfoInteractor;

    @Inject
    public UserReceivedEventsPresenter(Context context) {
        super(context);
        mInfoInfoInteractor = new UserInteractor(mContext);
    }

    @Override
    protected Observable<Response<List<GitEvent>>> load(int page) {
        if (!NetworkUtils.isNetworkConnected(mContext)) {
            return super.load(page);
        } else {
            // 加载最新收到的事件
            return mInfoInfoInteractor.loadReceiveEvents(PreferenceUtils.getUsername(mContext), page);
        }
    }

    public void startOwnerProfileActivity() {
        Intent intent = new Intent(mContext, UserProfileActivity.class);
        ActivityUtils.startActivity(mContext, intent);
    }

    @Override
    protected FileCache.CacheType getCacheType() {
        return FileCache.CacheType.USER_RECEIVED_EVENT;
    }

    public void startRepoDetailsActivity() {
        Intent intent = RepositoryActivity.getIntent(mContext);
        ActivityUtils.startActivity(mContext, intent);
    }
}
