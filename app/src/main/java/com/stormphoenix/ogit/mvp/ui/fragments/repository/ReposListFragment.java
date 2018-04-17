package com.stormphoenix.ogit.mvp.ui.fragments.repository;

import android.support.v7.widget.RecyclerView;

import com.stormphoenix.ogit.entity.github.GitRepository;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.adapters.GitReposAdapter;
import com.stormphoenix.ogit.adapters.base.BaseRecyclerAdapter;
import com.stormphoenix.ogit.mvp.presenter.base.BaseRepoListPresenter;
import com.stormphoenix.ogit.mvp.presenter.base.ListItemPresenter;
import com.stormphoenix.ogit.mvp.ui.fragments.base.ListWithPresenterFragment;

import java.util.ArrayList;

/**
 * Created by wanlei on 18316.
 * <p>
 * focus on displaying repositories, just pass the correct RepoPresenter to it,
 * and it can work automately.
 */

public class ReposListFragment extends ListWithPresenterFragment<GitRepository> {

    private BaseRepoListPresenter presenter;

    public static final ReposListFragment newInstance(BaseRepoListPresenter presenter) {
        ReposListFragment reposListFragment = new ReposListFragment();
        reposListFragment.setPresenter(presenter);
        return reposListFragment;
    }

    @Override
    public void initListItemView() {
        super.initListItemView();
        mAdapter.addOnViewClickListener(R.id.repository_card_wrapper, presenter);
    }

    @Override
    public BaseRecyclerAdapter<GitRepository, RecyclerView.ViewHolder> getAdapter() {
        mAdapter = new GitReposAdapter(getActivity(), new ArrayList<GitRepository>());
        return mAdapter;
    }

    @Override
    public ListItemPresenter getListItemPresetner() {
        return presenter;
    }

    public void setPresenter(BaseRepoListPresenter presenter) {
        this.presenter = presenter;
    }
}
