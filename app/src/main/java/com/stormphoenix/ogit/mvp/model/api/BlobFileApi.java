package com.stormphoenix.ogit.mvp.model.api;

import com.stormphoenix.ogit.entity.github.GitBlob;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wanlei on 18-3-10.
 */

public interface BlobFileApi {
    /**
     * 获取blob文件的内容
     *
     * @param user  拥有者
     * @param repo   仓库名字
     * @param path   blob文件所在路径
     * @param branch 仓库的分支
     * @return
     */
    @GET("repos/{user}/{repo}/contents/{path}")
    Observable<GitBlob> loadBlob(@Path("user") String user, @Path("repo") String repo, @Path("path") String path, @Query("ref") String branch);
}
