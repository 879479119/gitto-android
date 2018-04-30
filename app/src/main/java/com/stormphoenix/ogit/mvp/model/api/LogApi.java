package com.stormphoenix.ogit.mvp.model.api;

import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.entity.log.ABInfo;
import com.stormphoenix.ogit.log.Vacant;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface LogApi {

    @GET("http://test.com/api/hybrid/abInfo")
    Observable<Response<com.stormphoenix.ogit.entity.log.Response<ABInfo>>> loadABInfo(@Query("name") String name);

    @GET("http://log.test.com")
    Observable<Response<Vacant>> batchLog(@Query("logs") String logs);
}
