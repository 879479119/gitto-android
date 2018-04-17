package com.stormphoenix.ogit.mvp.model.api;

import com.stormphoenix.ogit.entity.github.GitIssue;

import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by wanlei on 18-4-11.
 */

public interface IssueApi {
    @POST("/repos/{owner}/{repo}/issues")
    Observable<Response<GitIssue>> sendAnIssue(@Path("owner") String owner, @Path("repo") String repo, @Body RequestBody body);
}
