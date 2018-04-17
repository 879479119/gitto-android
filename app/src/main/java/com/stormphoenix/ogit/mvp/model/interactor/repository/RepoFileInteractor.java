package com.stormphoenix.ogit.mvp.model.interactor.repository;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitBlob;
import com.stormphoenix.ogit.mvp.model.api.BlobFileApi;
import com.stormphoenix.ogit.mvp.model.api.CodeFileApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import rx.Observable;

/**
 * Created by wanlei on 18-3-10.
 */

public class RepoFileInteractor {
    private CodeFileApi codeFileApi = null;
    private BlobFileApi blobFileApi = null;
    private Context mContext = null;

    public RepoFileInteractor(Context mContext) {
        blobFileApi = RetrofitCreator.getJsonRetrofit(mContext).create(BlobFileApi.class);
        codeFileApi = RetrofitCreator.getStringRetrofit(mContext).create(CodeFileApi.class);
    }

    public Observable<GitBlob> loadBlob(String owner, String repo, String path, String branch) {
        return blobFileApi.loadBlob(owner, repo, path, branch);
    }

    /**
     * 加载代码内容
     *
     * @param url
     * @return
     */
    public Observable<String> loadCodeContent(String url) {
        return codeFileApi.loadCodeContent(url);
    }
}
