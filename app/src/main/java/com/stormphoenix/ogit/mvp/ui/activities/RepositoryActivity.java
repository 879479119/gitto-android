package com.stormphoenix.ogit.mvp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.mvp.presenter.repository.RepositoryPresenter;
import com.stormphoenix.ogit.mvp.ui.activities.base.HybridActivity;
import com.stormphoenix.ogit.mvp.ui.dialog.ProgressDialogGenerator;
import com.stormphoenix.ogit.mvp.view.RepositoryView;
import com.stormphoenix.ogit.utils.Constants;

import javax.inject.Inject;

import butterknife.BindView;

public class RepositoryActivity extends HybridActivity implements RepositoryView {
    @Inject
    public RepositoryPresenter mPresenter;

    @BindView(R.id.repo_hybrid)
    ViewGroup container;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private ProgressDialogGenerator dialogGenerator;
    private Menu mMenu;
    private boolean isStared = false;
    private boolean isForked = false;

    public static Intent getIntent(Context context) {
        return new Intent(context, RepositoryActivity.class);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestory();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_repo_details, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected LinearLayout getLayout() {
        return (LinearLayout) findViewById(R.id.repo_hybrid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initDialog();
//        initWebView("879479119", "Github-Mobile");
        mPresenter.onAttachView(this);
        mPresenter.onCreate(savedInstanceState);
    }

    private void initDialog() {
        dialogGenerator = new ProgressDialogGenerator(this);
        dialogGenerator.cancelable(false);
        dialogGenerator.circularProgress();
        dialogGenerator.content(getString(R.string.waiting));
        dialogGenerator.title(getString(R.string.loading));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_repo;
    }

    @Override
    public void initializeInjector() {
        DaggerActivityComponent.builder()
                .contextModule(new ContextModule(this))
                .build()
                .inject(this);
    }
//
//    @Override
//    public void setDescription(String description) {
//        mTextDescription.setText(description);
//    }
//
//    @Override
//    public void setStarCount(String s) {
//        mLabelStar.setValueName(s);
//    }
//
//    @Override
//    public void setBranch(String branch) {
//        mTextBranch.setText(branch);
//    }
//
//    @Override
//    public void setForkCount(String forkCount) {
//        mLabelFork.setValueName(forkCount);
//    }


    @Override
    public String getUrl(String user, String repo) {
        return Constants.getRepoURL(user, repo);
    }

    @Override
    public void setToolbarStatus(String repositoryName, String ownerName) {
        mToolbar.setTitle(repositoryName);
        mToolbar.setSubtitle(ownerName);
    }

    @Override
    public void finishView() {
        finish();
    }

//    @Override
//    public void loadReadmeHtml(String readmeText, String repoHtmlUrl, String defaultBranch) {
//        if (TextUtils.isEmpty(readmeText)) {
//            mTextReadme.loadMarkdown(getString(R.string.no_readme));
//            return;
//        } else {
//            mTextReadme.loadMarkdown(readmeText);
//        }
//    }

//    @Override
//    public void setWatchersCount(String watcher) {
//        mLabelWatcher.setValueName(watcher);
//    }

    @Override
    public void setIsForked(boolean isFork) {
        this.isForked = isFork;
        // 此处会报告 NullPointerException 异常，原因是 Presenter.setIsFork(boolean) 会先于 onCreateOptionsMenu() 调用
        MenuItem item = mMenu.findItem(R.id.action_fork);
        if (isFork) {
            item.setTitle(getString(R.string.forked));
        } else {
            item.setTitle(getString(R.string.fork));
        }
    }

    public void setIsStared(boolean isStared) {
        this.isStared = isStared;
        MenuItem item = mMenu.findItem(R.id.action_star);
        if (isStared) {
            item.setTitle(getString(R.string.unstar));
        } else {
            item.setTitle(getString(R.string.star));
        }
    }

    @Override
    public void startForking() {
        dialogGenerator.title(getString(R.string.forking));
    }

    @Override
    public void stopForking() {
        dialogGenerator.title(getString(R.string.loading));
    }

    @Override
    public void onBackPressed() {
        finishView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishView();
                return true;
            case R.id.action_fork:
                if (!isForked) {
                    mPresenter.fork();
                }
                return true;
            case R.id.action_star:
                if (isStared) {
                    mPresenter.unstar();
                } else {
                    mPresenter.star();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//
//    @OnClick({R.id.contributor_wrapper, R.id.code_wrapper, R.id.commit_wrapper})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.contributor_wrapper:
//                mPresenter.startContributorActivity();
//                break;
//            case R.id.code_wrapper:
//                mPresenter.startCodeActivity();
//                break;
//            case R.id.commit_wrapper:
//                mPresenter.startCommitsActivity();
//            default:
//                break;
//        }
//    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(mToolbar, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        dialogGenerator.show();
    }

    @Override
    public void hideProgress() {
        dialogGenerator.title(getString(R.string.loading));
        dialogGenerator.cancel();
    }
}
