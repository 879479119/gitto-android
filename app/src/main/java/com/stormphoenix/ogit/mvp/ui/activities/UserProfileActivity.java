package com.stormphoenix.ogit.mvp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.mvp.presenter.user.UserProfilePresenter;
import com.stormphoenix.ogit.mvp.ui.activities.base.OwnerProfileActivity;
import com.stormphoenix.ogit.mvp.view.UserDetailsView;
import com.stormphoenix.ogit.utils.Constants;
import com.stormphoenix.ogit.utils.ImageUtils;
import com.stormphoenix.ogit.utils.ViewUtils;

import javax.inject.Inject;

public class UserProfileActivity extends OwnerProfileActivity implements UserDetailsView {

    public static final Intent getIntent(Context context) {
        return new Intent(context, UserProfileActivity.class);
    }

    @Inject
    public UserProfilePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.onAttachView(this);
        mPresenter.onCreate(savedInstanceState);
        setupLabels();
        setFabListener();
    }

    @Override
    public String getUrl(String user, String repo) {
        return Constants.getUserURL(user);
    }

    private void setFabListener() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFollowed == IsFollowed.follewed) {
                    mPresenter.unFollow();
                } else {
                    mPresenter.follow();
                }
            }
        });
    }

    private void setupLabels() {
        mLabel3.setKeyName(getString(R.string.followings));
        mLabel1.setKeyName(getString(R.string.star));
        mLabel2.setKeyName(getString(R.string.followers));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setFollowersCount(String followers) {
        mLabel2.setValueName(followers);
    }

    @Override
    public void setFollowingCount(String followings) {
        mLabel3.setValueName(followings);
    }

    @Override
    public void stopProgress() {
    }

    @Override
    public void showProgress() {
    }

    @Override
    public void loadHeaderImage(String url) {
        ImageUtils.getInstance().displayImage(url, mImageAppBar);
        ImageUtils.getInstance().displayImage(url, circleHeadImage);
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String message) {
        ViewUtils.showMessage(mImageAppBar, message);
    }

    @Override
    public void setStaredCount(String count) {
        mLabel1.setValueName(count);
    }

    @Override
    public void setIsFollow(boolean isFollow) {
        if (isFollow) {
            isFollowed = IsFollowed.follewed;
            mFab.setImageResource(R.drawable.ic_person_delete_24dp);
        } else {
            isFollowed = IsFollowed.unfollewd;
            mFab.setImageResource(R.drawable.ic_person_add_white_24dp);
        }
    }

    @Override
    public void initializeInjector() {
        DaggerActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .build()
                .inject(this);
    }

//    @Override
//    protected void onBaseInfoClicked() {
//        super.onBaseInfoClicked();
//        mPresenter.startUserDetailsActivity();
//    }
}
