package com.stormphoenix.ogit.mvp.model.interactor;

import android.content.Context;

import com.stormphoenix.ogit.entity.github.GitEvent;
import com.stormphoenix.ogit.entity.github.GitOrganization;
import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.mvp.model.api.OrganizationApi;
import com.stormphoenix.ogit.shares.rx.creator.RetrofitCreator;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-3-11.
 * <p>
 * 获取组织信息
 */

public class OrgInteractor {
    private OrganizationApi api = null;
    private Context mContext = null;

    public OrgInteractor(Context context) {
        mContext = context;
        api = RetrofitCreator.getJsonRetrofitWithToken(mContext).create(OrganizationApi.class);
    }

    public Observable<Response<List<GitOrganization>>> loadOwnerOrgs(int page) {
        return api.loadOwnerOrgs(page);
    }

    public Observable<Response<GitOrganization>> loadOrganization(String org) {
        return api.loadOrganization(org);
    }

    public Observable<Response<List<GitUser>>> loadMembersCount(String org) {
        return api.loadMembersCount(org);
    }

    public Observable<Response<List<GitUser>>> loadMembers(String org, int pageId) {
        return api.loadMembers(org, pageId);
    }

    public Observable<Response<List<GitEvent>>> loadOwnerOrgEvents(String username, String org, int page) {
        return api.loadOwnerOrgEvents(username, org, page);
    }
}
