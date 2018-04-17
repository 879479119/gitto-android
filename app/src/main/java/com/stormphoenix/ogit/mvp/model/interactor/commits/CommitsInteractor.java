package com.stormphoenix.ogit.mvp.model.interactor.commits;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitCommit;
import com.stormphoenix.ogit.mvp.model.api.CommitsApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-3-18.
 */

public class CommitsInteractor {
    private Context mContext = null;
    private CommitsApi api = null;

    public CommitsInteractor(Context context) {
        mContext = context;
        api = RetrofitCreator.getJsonRetrofitWithToken(mContext).create(CommitsApi.class);
    }

    public Observable<Response<List<GitCommit>>> loadReposCommits(String owner, String repo) {
        return api.loadRepoCommits(owner, repo);
    }
}
