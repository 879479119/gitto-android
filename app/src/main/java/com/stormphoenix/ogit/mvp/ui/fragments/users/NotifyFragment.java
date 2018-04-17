package com.stormphoenix.ogit.mvp.ui.fragments.users;

import android.support.v7.widget.RecyclerView;

import com.stormphoenix.ogit.entity.github.GitNotification;
import com.stormphoenix.ogit.adapters.GitNotificationsAdapter;
import com.stormphoenix.ogit.adapters.base.BaseRecyclerAdapter;
import com.stormphoenix.ogit.mvp.presenter.base.ListItemPresenter;
import com.stormphoenix.ogit.mvp.presenter.user.NotifyPresenter;
import com.stormphoenix.ogit.mvp.ui.fragments.base.ListWithPresenterFragment;

import java.util.ArrayList;

/**
 * Created by wanlei on 18-3-18.
 */

public class NotifyFragment extends ListWithPresenterFragment<GitNotification> {
    private NotifyPresenter presenter;

    public static NotifyFragment newInstance(NotifyPresenter presenter) {
        NotifyFragment fragment = new NotifyFragment();
        fragment.setPresenter(presenter);
        return fragment;
    }

    @Override
    public BaseRecyclerAdapter<GitNotification, RecyclerView.ViewHolder> getAdapter() {
        mAdapter = new GitNotificationsAdapter(getActivity(), new ArrayList<>());
        return mAdapter;
    }

    @Override
    public ListItemPresenter getListItemPresetner() {
        return presenter;
    }

    public void setPresenter(NotifyPresenter presenter) {
        this.presenter = presenter;
    }
}
