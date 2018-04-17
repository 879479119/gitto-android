package com.stormphoenix.ogit.mvp.model.api;

import com.stormphoenix.ogit.entity.github.GitSearchResult;
import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.entity.github.GitUser;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wanlei on 18-3-12.
 * <p>
 * 搜索功能
 */

public interface SearchApi {
    @GET("search/repositories?per_page=10")
    Observable<Response<GitSearchResult<GitRepository>>> searchRepos(@Query("q") String keyword, @Query("page") int page);

    //    https://api.github.com/search/repositories?q=imageselector+language:python&sort=stars&order=desc
    @GET("search/repositories?q={keyword}+language:{language}&sort={sort}&order={order}")
    Observable<Response<GitSearchResult<GitRepository>>> searchReposByLang(@Path("keyword") String keyword,
                                                                           @Path("order") String order);

    @GET("search/users?per_page=10")
    Observable<Response<GitSearchResult<GitUser>>> searchUsers(@Query("q") String keyword, @Query("page") int page);
}
