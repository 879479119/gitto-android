package com.stormphoenix.ogit.mvp.model.interactor.commits;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitCommit;
import com.stormphoenix.ogit.mvp.model.api.CommitsApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-3-27.
 */

public class CommitDetailsInteractor {
    private CommitsApi commitsApi = null;

    public CommitDetailsInteractor(Context context) {
        commitsApi = RetrofitCreator.getJsonRetrofitWithToken(context).create(CommitsApi.class);
    }

    public Observable<Response<GitCommit>> loadSingleCommitDetails(String owner, String repo, String sha) {
        return commitsApi.loadSingleCommit(owner, repo, sha);
    }
}
