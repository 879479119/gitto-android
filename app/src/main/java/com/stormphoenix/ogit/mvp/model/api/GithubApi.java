package com.stormphoenix.ogit.mvp.model.api;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by wanlei on 18-2-26.
 */

public interface GithubApi {
    @GET
    Observable<Response<String>> loadTrendRepos(@Url String url);
}
