package com.stormphoenix.ogit.mvp.model.interactor.repository;

import android.content.Context;
import android.util.Log;

import com.stormphoenix.ogit.entity.github.GitBranch;
import com.stormphoenix.ogit.entity.github.GitEmpty;
import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.entity.github.GitTree;
import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.mvp.model.api.CodeFileApi;
import com.stormphoenix.ogit.mvp.model.api.GithubApi;
import com.stormphoenix.ogit.mvp.model.api.RepoApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-3-1.
 */
public class RepoInteractor {
    private static final String TAG = "RepoInteractor";
    private RepoApi repoApi = null;
    private CodeFileApi codeFileApi = null;
    private Context mContext = null;

    public RepoInteractor(Context context) {
        mContext = context;
        repoApi = RetrofitCreator.getJsonRetrofitWithToken(mContext).create(RepoApi.class);
        codeFileApi = RetrofitCreator.getStringRetrofit(mContext).create(CodeFileApi.class);
    }

    public Observable<Response<GitRepository>> loadRepository(String url) {
        return repoApi.loadRepo(url);
    }

    public Observable<Response<List<GitRepository>>> loadOwnerRepos() {
        return repoApi.loadOwnerRepos();
    }

    public Observable<Response<List<GitBranch>>> loadRepositoryBranch(final String user, final String repository) {
        return repoApi.loadBranches(user, repository);
    }

    public Observable<Response<List<GitUser>>> loadContributors(String owner, String repository, String page) {
        return repoApi.loadContributors(owner, repository, page);
    }

    public Observable<Response<GitTree>> loadRepoTrees(String owner, String repo, String sha) {
        return repoApi.loadRepoTree(owner, repo, sha);
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

    /**
     * "Basic " + Base64.encode(username + ':' + password)
     * 可以使用 String basicAuth = Credentials.basic(username, password);
     */
    public Observable<String> loadReadmeHtml(String owner, String repo, String ref) {
        return codeFileApi.loadReadMeHtml(owner, repo, ref);
    }

    public Observable<Response<GitRepository>> fork(final String owner, final String repo) {
        return repoApi.fork(owner, repo);
    }

    public Observable<Response<GitEmpty>> hasStared(final String owner, final String repo) {
        return repoApi.hasStar(owner, repo);
    }

    public Observable<Response<GitEmpty>> unstar(String owner, String repo) {
        return repoApi.unStar(owner, repo);
    }

    public Observable<Response<GitEmpty>> star(String owner, String repo) {
        return repoApi.star(owner, repo);
    }
}
