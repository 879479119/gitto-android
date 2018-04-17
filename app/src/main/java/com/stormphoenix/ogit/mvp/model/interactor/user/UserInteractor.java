package com.stormphoenix.ogit.mvp.model.interactor.user;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitEmpty;
import com.stormphoenix.ogit.entity.github.GitEvent;
import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.mvp.model.api.UserApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-2-26.
 */

public class UserInteractor {
    private UserApi userApi = null;
    private Context mContext = null;

    public UserInteractor(Context context) {
        mContext = context;
        userApi = RetrofitCreator.getJsonRetrofitWithToken(mContext).create(UserApi.class);
    }

    /**
     * 加载接受到的事件
     *
     * @param username
     * @param page
     * @return
     */
    public Observable<Response<List<GitEvent>>> loadReceiveEvents(final String username, final int page) {
        return userApi.loadGitEvents(username, String.valueOf(page));
    }

    public Observable<Response<List<GitRepository>>> loadStarredRepository(String user, int page) {
        return userApi.starredRepository(user, String.valueOf(page));
    }

    public Observable<List<GitRepository>> loadUserRepository(final String user, final int page) {
        return userApi.userRepository(user, String.valueOf(page));
    }

    public Observable<Response<GitUser>> loadUser(final String user) {
        return userApi.loadUser(user);
    }

    public Observable<Response<List<GitRepository>>> loadStaredCount(String user) {
        return userApi.loadStaredCount(user);
    }

    public Observable<Response<GitEmpty>> follow(final String user) {
        return userApi.follow(user);
    }

    public Observable<Response<GitEmpty>> hasFollowed(final String user) {
        return userApi.hasFollow(user);
    }

    public Observable<Response<GitEmpty>> unFollow(final String user) {
        return userApi.unFollow(user);
    }

    public Observable<Response<List<GitEvent>>> performedEvents(final String username, final int page) {
        return userApi.performedEvents(username, String.valueOf(page));
    }

    public Observable<Response<List<GitUser>>> loadFollowers(String username, String page) {
        return userApi.loadFollowers(username, page);
    }

    public Observable<Response<List<GitUser>>> loadFollowings(String username, String page) {
        return userApi.loadFollowings(username, page);
    }
}
