package com.stormphoenix.ogit.mvp.model.interactor;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.entity.github.GitSearchResult;
import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.entity.log.ABInfo;
import com.stormphoenix.ogit.log.Vacant;
import com.stormphoenix.ogit.mvp.model.api.LogApi;
import com.stormphoenix.ogit.mvp.model.api.SearchApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import retrofit2.Response;
import rx.Observable;

public class LogInteractor {

    private Context mContext;
    private LogApi logApi = null;

    public LogInteractor(Context context) {
        mContext = context;
        logApi = RetrofitCreator.getJsonRetrofit(mContext).create(LogApi.class);
    }

    public Observable<Response<com.stormphoenix.ogit.entity.log.Response<ABInfo>>> getABInfo(String name) {
        return logApi.loadABInfo(name);
    }

    public Observable<Response<Vacant>> batchLog(String logs) {
        return logApi.batchLog(logs);
    }
}
