package com.stormphoenix.ogit.mvp.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import com.stormphoenix.ogit.entity.github.GitTreeItem;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.mvp.presenter.repository.RepoTreePresenter;
import com.stormphoenix.ogit.mvp.ui.activities.base.BaseActivity;
import com.stormphoenix.ogit.mvp.ui.fragments.repository.FoldsFragment;
import com.stormphoenix.ogit.widget.BreadcrumbView;

import butterknife.BindView;

/**
 * 逻辑流程：
 * BreadcrumbView 是面包屑布局，它实现了BreadcrumbTreeController接口，
 * 通过此接口可以对BreadcrumbView进行控制。
 * <p>
 * BreadcrumbTreeActivity负责FoldFragment和BreadcrumbView之间的信息同步，
 * 当FoldFragment的信息变化时，可以通过BreadcrumbTreeController接口来操作
 * BreadcrumbView里面的内容。
 */
public class BreadcrumbTreeActivity extends BaseActivity {
    public static final String TAG = BreadcrumbTreeActivity.class.getSimpleName();

    public static final String TITLE = "title";
    public static final String SUB_TITLE = "subtitle";

    // 启动该Activity时传输给本Activity的数据集
    private Bundle dataBundle;
    // 声明该Activity显示内容的类型
    private int type;
    // Toolbar 设置的标题
    private String title;
    // Toolbar 设置的子标题
    private String subTitle;
    // 控制FoldsFragment逻辑交互的 Presenter
    RepoTreePresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.file_bread_crumb)
    BreadcrumbView<GitTreeItem> mBreadcrumbView;

    // BreadcurumbActivity 的fragment内容
    private FoldsFragment foldsFragment;

    public static Intent newIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, BreadcrumbTreeActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        setUpToolbar();
        // 设置fragment内容
        initFragment();
    }

    @Override
    public void onBackPressed() {
        int length = mBreadcrumbView.getBreadcrumbLength();
        Log.e(TAG, "onBackPressed: length = " + length);
        if (length == 1) {
            super.onBackPressed();
        } else {
            presenter.onItemSelect(length - 2, mBreadcrumbView.getBreadcrumb(length - 2));
        }
    }

    private void initFragment() {
        presenter = new RepoTreePresenter(this);
        mBreadcrumbView.setOnBreadSelectListener(presenter);
        presenter.setController(mBreadcrumbView);
        foldsFragment = FoldsFragment.newInstance(presenter);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, foldsFragment)
                .commit();
    }

    /**
     * 从 dataBundle 数据集中获取数据用于设置 Toolbar
     */
    private void setUpToolbar() {
        title = dataBundle.getString(TITLE);
        subTitle = dataBundle.getString(SUB_TITLE);

        mToolbar.setTitle(title);
        if (!TextUtils.isEmpty(subTitle)) {
            mToolbar.setSubtitle(subTitle);
        }
        // 设置为默认工具栏杆
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 解析传输给此Activity的数据
     */
    private void parseIntent() {
        dataBundle = getIntent().getExtras();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_file_tree;
    }

    /**
     * BreadcrumbTreeController 用于对BreadcrumbView的行为进行控制
     */
    public static interface BreadcrumbTreeController {
        /**
         * 从下标 index 处删除之后（包括index处）的所有的Breadcrumb内容
         *
         * @param index
         */
        void removeFrom(int index);

        /**
         * 在尾部添加一个BreadcrumbView
         *
         * @param breadcrumb
         */
        void addBreadcrumb(Breadcrumb breadcrumb);

        /**
         * 获取Breadcrumb所保存的数据
         * example: 如果BreadcrumbView保存的是 [app,src,main,java] 的数组
         * 则返回 app/src/main/java
         *
         * @return 返回数据构成一条路径
         */
        String getAbsolutePath();

        int getBreadcrumbLength();

        Breadcrumb getBreadcrumb(int index);
    }

    /**
     * 存储在BreadcrumbView里面的信息
     *
     * @param <T>
     */
    public static class Breadcrumb<T> {
        private String name;
        private T attachedInfo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public T getAttachedInfo() {
            return attachedInfo;
        }

        public void setAttachedInfo(T attachedInfo) {
            this.attachedInfo = attachedInfo;
        }
    }

    @Override
    public void initializeInjector() {
    }
}
