package com.stormphoenix.ogit.mvp.ui.activities.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stormphoenix.ogit.R;
//import com.stormphoenix.ogit.bridge.BaseBridge;
import com.stormphoenix.ogit.bridge.LogMaster;
import com.stormphoenix.ogit.bridge.Tracker;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.entity.github.GitTrendRepository;
import com.stormphoenix.ogit.entity.log.ABInfo;
import com.stormphoenix.ogit.entity.log.Response;
import com.stormphoenix.ogit.mvp.presenter.repository.RepositoryPresenter;
import com.stormphoenix.ogit.mvp.ui.activities.RepositoryActivity;
import com.stormphoenix.ogit.mvp.ui.activities.UserProfileActivity;
import com.stormphoenix.ogit.mvp.ui.activities.base.BaseActivity;
import com.stormphoenix.ogit.mvp.ui.dialog.ProgressDialogGenerator;
import com.stormphoenix.ogit.mvp.view.RepositoryView;
import com.stormphoenix.ogit.shares.HtmlImageGetter;
import com.stormphoenix.ogit.utils.ActivityUtils;
import com.stormphoenix.ogit.utils.Constants;
import com.stormphoenix.ogit.utils.PreferenceUtils;
import com.stormphoenix.ogit.utils.TextTools;
import com.stormphoenix.ogit.widget.KeyValueLabel;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import us.feras.mdv.MarkdownView;

public abstract class HybridActivity extends BaseActivity {

    private static final String TAG = "HybridActivity";
//    @BindView(R.id.user_hybrid)
    LinearLayout container;

    private WebView webView;

    public String getUrl(String user, String repo) { return ""; }

    protected LinearLayout getLayout() { return null; }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView(String user, String repo) {

        webView = new WebView(getApplicationContext());

        webView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        container = getLayout();
        container.addView(webView, 1);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDomStorageEnabled(true);//设置适应Html5
        webSettings.setDisplayZoomControls(false);

        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSaveFormData(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "source " + view.getUrl());

                int index = view.getUrl().indexOf('?') == -1 ? view.getUrl().length() : view.getUrl().indexOf('?');
                int index2 = url.indexOf('?') == -1 ? url.length() : url.indexOf('?');

                if (Constants.isHybridPage(url)) {
                    if (url.startsWith("http://test.com/repo")) {
                        Log.i(TAG, "shouldOverrideUrlLoading: " + url);
                        Tracker.getInstance().trackPageShow("app://repo?" + view.getUrl().substring(0, index), "app://user?" + url.substring(0, index2));
                        ActivityUtils.startActivity(HybridActivity.this, RepositoryActivity.getIntent(HybridActivity.this));
                        return false;
                    }
                    if (url.startsWith("http://test.com/user")) {
                        Log.i(TAG, "shouldOverrideUrlLoading: " + url);
                        Tracker.getInstance().trackPageShow("app://user?" + view.getUrl().substring(0, index), "app://repo?" + url.substring(0, index2));
                        ActivityUtils.startActivity(HybridActivity.this, UserProfileActivity.getIntent(HybridActivity.this));
                        return false;
                    }
                } else {
                    Log.i(TAG, "最好是跳转到一个单独的webview去");
                    return false;
                }

//                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.proceed();
            }
        });


        webView.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                Toast.makeText(getApplicationContext(), title, Toast.LENGTH_LONG).show();
            }
        });

        String token = PreferenceUtils.getToken(getApplicationContext());

        String url = getUrl(user, repo);

        // 添加上页面启动时需要的app和ab测试的相关参数

        url = addSearchPart(url);

        Log.i(TAG, url);

        webView.loadUrl(url);

//        addBridge(new BaseBridge(this));
        addBridge(new LogMaster(getApplicationContext()));
    }

    public void runJavaScript(final String function, final String... argv) {
        webView.post(() -> {
            String arguments = "'" + TextUtils.join("','", argv) + "'";
            String javascript = "javascript:" + function + "(" + arguments + ");";
            webView.loadUrl(javascript);
        });
    }

    @SuppressLint("JavascriptInterface")
    public void addBridge(Object object) {
        webView.addJavascriptInterface(object, object.getClass().getSimpleName());
    }

    private String addSearchPart(String url) {
        String config = PreferenceUtils.getAbtest(getApplicationContext());

//        Gson gson = new Gson();
//        ABInfo response = gson.fromJson(config, ABInfo.class);
        Log.i(TAG, "addSearchPart: " + config);
        return url + "?"
                + "app=" + 1
                + "&version=" + 2
                + "&platform=" + 3
                + "&testArray=" + config;
    }
}
