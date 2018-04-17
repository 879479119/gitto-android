package com.stormphoenix.ogit.mvp.presenter.search;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitSearchResult;
import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.cache.FileCache;
import com.stormphoenix.ogit.mvp.presenter.search.SearchPresenter;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-3-14.
 */

public class SearchUsersPresenter extends SearchPresenter<GitUser> {

    @Inject
    public SearchUsersPresenter(Context context) {
        super(context);
    }

    @Override
    protected FileCache.CacheType getCacheType() {
        return null;
    }

    @Override
    protected Observable<Response<GitSearchResult<GitUser>>> load(int page) {
        if (keyword == null) {
            return null;
        }
        return mInteractor.searchUsers(keyword, page);
    }
}
