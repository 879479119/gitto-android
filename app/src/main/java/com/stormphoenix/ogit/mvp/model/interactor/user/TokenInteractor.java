package com.stormphoenix.ogit.mvp.model.interactor.user;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitEmpty;
import com.stormphoenix.ogit.entity.github.GitToken;
import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.entity.http.Base64;
import com.stormphoenix.ogit.mvp.model.api.TokenApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import java.util.Arrays;
import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-2-26.
 */

public class TokenInteractor {
    public final static String TOKEN_NOTE = "Graduation";
    public final static String[] SCOPES = {"write:org", "read:org", "gist", "public_repo", "repo", "user", "gist"};

    private TokenApi tokenApi = null;

    private Context mContext = null;

    public TokenInteractor(Context mContext) {
        tokenApi = RetrofitCreator.getRetrofitWithoutToken(mContext).create(TokenApi.class);
    }

    public Observable<Response<List<GitToken>>> listToken(String username, String password) {
        return tokenApi.listToken("Basic " + Base64.encode(username + ':' + password));
    }

    public Observable<Response<GitEmpty>> removeToken(String username, String password, String id) {
        return tokenApi.removeToken("Basic " + Base64.encode(username + ':' + password), id);
    }

    public Observable<Response<GitToken>> createToken(String username, String password) {
        GitToken token = new GitToken();
        token.setNote(TOKEN_NOTE);
        token.setScopes(Arrays.asList(SCOPES));
        return tokenApi.createToken(token, "Basic " + Base64.encode(username + ':' + password));
    }

    public Observable<Response<GitUser>> loadUser(String user) {
        return tokenApi.loadUser(user);
    }
}
