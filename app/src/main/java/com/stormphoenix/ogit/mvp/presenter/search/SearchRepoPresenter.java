package com.stormphoenix.ogit.mvp.presenter.search;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitSearchResult;
import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.cache.FileCache;
import com.stormphoenix.ogit.mvp.presenter.search.SearchPresenter;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-3-12.
 */

public class SearchRepoPresenter extends SearchPresenter<GitRepository> {
    @Inject
    public SearchRepoPresenter(Context context) {
        super(context);
    }

    @Override
    protected FileCache.CacheType getCacheType() {
        return null;
    }

    @Override
    protected Observable<Response<GitSearchResult<GitRepository>>> load(int page) {
        if (keyword == null) {
            return null;
        }
        return mInteractor.searchRepos(keyword, page);
    }
}
