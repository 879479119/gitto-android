package com.stormphoenix.ogit.mvp.model.interactor.trend;

import android.content.Context;

import com.stormphoenix.ogit.mvp.model.api.GithubApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-4-10.
 */

public class TrendInteractor {
    private GithubApi githubApi = null;
    private Context mContext;

    public TrendInteractor(Context context) {
        mContext = context;
        githubApi = RetrofitCreator.getStringRetrofit(mContext).create(GithubApi.class);
    }

    public Observable<Response<String>> loadTrendRepos(String url) {
        return githubApi.loadTrendRepos(url);
    }
}
