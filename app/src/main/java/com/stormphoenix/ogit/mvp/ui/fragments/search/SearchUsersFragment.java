package com.stormphoenix.ogit.mvp.ui.fragments.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.stormphoenix.ogit.entity.github.GitUser;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.adapters.GitUserAdapter;
import com.stormphoenix.ogit.adapters.base.BaseRecyclerAdapter;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.mvp.presenter.search.SearchPresenter;
import com.stormphoenix.ogit.mvp.presenter.search.SearchUsersPresenter;
import com.stormphoenix.ogit.mvp.ui.activities.UserProfileActivity;
import com.stormphoenix.ogit.utils.ActivityUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by wanlei on 18-3-14.
 */

public class SearchUsersFragment extends SearchFragment<GitUser> {

    @Inject
    public SearchUsersPresenter mPresenter = null;

    /**
     * 搜索的关键字，从SearchActvity中获取
     */
    private String keyword;

    @Override
    public void initializeInjector() {
        DaggerActivityComponent.builder()
                .contextModule(new ContextModule(getActivity()))
                .build()
                .inject(this);
    }

    public static SearchUsersFragment getInstance() {
        SearchUsersFragment fragment = new SearchUsersFragment();
        return fragment;
    }

    @Override
    protected SearchPresenter<GitUser> getSearchPresenter() {
        return mPresenter;
    }

    @Override
    public BaseRecyclerAdapter<GitUser, RecyclerView.ViewHolder> getAdapter() {
        mAdapter = new GitUserAdapter(getActivity(), new ArrayList<>());
        mAdapter.addOnViewClickListener(R.id.owner_wrapper, new BaseRecyclerAdapter.OnInternalViewClickListener<GitUser>() {
            @Override
            public void onClick(View parentV, View v, Integer position, GitUser values) {
                EventBus.getDefault().postSticky(values);
                ActivityUtils.startActivity(getActivity(), UserProfileActivity.getIntent(getActivity()));
            }

            @Override
            public boolean onLongClick(View parentV, View v, Integer position, GitUser values) {
                return false;
            }
        });
        return mAdapter;
    }
}
