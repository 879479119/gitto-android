package com.stormphoenix.ogit.mvp.presenter.organization;

import android.content.Context;
import android.text.TextUtils;

import com.stormphoenix.ogit.entity.github.GitEvent;
import com.stormphoenix.ogit.cache.FileCache;
import com.stormphoenix.ogit.mvp.model.interactor.OrgInteractor;
import com.stormphoenix.ogit.mvp.presenter.base.EventsPresenter;
import com.stormphoenix.ogit.utils.PreferenceUtils;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-2-25.
 */

public class OrgEventsPresenter extends EventsPresenter {
    /**
     * Interactor 用于提交网络请求获取数据
     **/
    private OrgInteractor orgInteractor;
    private String orgName;

    @Inject
    public OrgEventsPresenter(Context context) {
        super(context);
        orgInteractor = new OrgInteractor(mContext);
    }

    @Override
    protected List<GitEvent> transformBody(List<GitEvent> body) {
        return body;
    }

    @Override
    public void startOwnerProfileActivity() {

    }

    @Override
    protected FileCache.CacheType getCacheType() {
        return FileCache.CacheType.ORG_EVENTS;
    }

    @Override
    public void startRepoDetailsActivity() {

    }

    @Override
    protected Observable<Response<List<GitEvent>>> load(int page) {
        if (TextUtils.isEmpty(orgName)) {
            return null;
        }
        // 加载最新收到的事件
        return orgInteractor.loadOwnerOrgEvents(PreferenceUtils.getUsername(mContext), orgName, page);
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
