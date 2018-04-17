package com.stormphoenix.ogit.mvp.model.api;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by wanlei on 18-3-4.
 * <p>
 * CodeFileApi 获取文件内容，不包括文件夹
 * BASE_URL http://raw.githubusercontent.com/
 */

public interface CodeFileApi {
    @GET
    Observable<String> loadCodeContent(@Url String url);

    /**
     * Get the README
     * This method returns the preferred README for a repository.
     *
     * @param
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/readme")
    @Headers({"Cache-Control: public, max-age=600", "Accept: application/vnd.github.VERSION.html"})
    Observable<String> loadReadMeHtml(@Path("owner") String owner,
                                      @Path("repo") String repo,
                                      @Query("ref") String ref);
}
