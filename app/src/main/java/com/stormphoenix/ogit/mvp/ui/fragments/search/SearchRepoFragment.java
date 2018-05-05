package com.stormphoenix.ogit.mvp.ui.fragments.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.stormphoenix.ogit.bridge.Tracker;
import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.adapters.GitReposAdapter;
import com.stormphoenix.ogit.adapters.base.BaseRecyclerAdapter;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.mvp.presenter.search.SearchPresenter;
import com.stormphoenix.ogit.mvp.presenter.search.SearchRepoPresenter;
import com.stormphoenix.ogit.mvp.ui.activities.RepositoryActivity;
import com.stormphoenix.ogit.utils.ActivityUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by wanlei on 18-3-12.
 * <p>
 * 用户显示搜索得到的Repository信息
 */

public class SearchRepoFragment extends SearchFragment<GitRepository> {

    @Inject
    public SearchRepoPresenter mPresenter = null;

    public static SearchRepoFragment getInstance() {
        SearchRepoFragment fragment = new SearchRepoFragment();
        return fragment;
    }

    @Override
    public void initListItemView() {
        super.initListItemView();
        mAdapter.addOnViewClickListener(R.id.repository_card_wrapper, new BaseRecyclerAdapter.OnInternalViewClickListener() {
            @Override
            public void onClick(View parentV, View v, Integer position, Object values) {
                Gson gson = new Gson();
                EventBus.getDefault().postSticky(values);
                Tracker.getInstance().trackPageShow("app://search", "app://repo?" + gson.toJson(values));
                ActivityUtils.startActivity(getActivity(), RepositoryActivity.getIntent(getActivity()));
            }

            @Override
            public boolean onLongClick(View parentV, View v, Integer position, Object values) {
                return false;
            }
        });
    }

    @Override
    public void initializeInjector() {
        DaggerActivityComponent.builder()
                .contextModule(new ContextModule(getActivity()))
                .build()
                .inject(this);
    }

    @Override
    public BaseRecyclerAdapter<GitRepository, RecyclerView.ViewHolder> getAdapter() {
        mAdapter = new GitReposAdapter(getActivity(), new ArrayList<>());
        return mAdapter;
    }

    @Override
    protected SearchPresenter<GitRepository> getSearchPresenter() {
        return mPresenter;
    }
}
