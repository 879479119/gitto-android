package com.stormphoenix.ogit.mvp.ui.activities.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;
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

import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.dagger2.component.DaggerActivityComponent;
import com.stormphoenix.ogit.dagger2.module.ContextModule;
import com.stormphoenix.ogit.mvp.presenter.repository.RepositoryPresenter;
import com.stormphoenix.ogit.mvp.ui.activities.RepositoryActivity;
import com.stormphoenix.ogit.mvp.ui.activities.base.BaseActivity;
import com.stormphoenix.ogit.mvp.ui.dialog.ProgressDialogGenerator;
import com.stormphoenix.ogit.mvp.view.RepositoryView;
import com.stormphoenix.ogit.shares.HtmlImageGetter;
import com.stormphoenix.ogit.utils.ActivityUtils;
import com.stormphoenix.ogit.utils.Constants;
import com.stormphoenix.ogit.utils.PreferenceUtils;
import com.stormphoenix.ogit.utils.TextTools;
import com.stormphoenix.ogit.widget.KeyValueLabel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import us.feras.mdv.MarkdownView;

public abstract class HybridActivity extends BaseActivity {

    // TODO: 对于抽象类的理解有问题，还是在这个鬼地方找的id，要不只有用findViewById
//    @BindView(R.id.user_hybrid)
    LinearLayout container;

    public String getUrl(String user, String repo) { return ""; }

    protected LinearLayout getLayout() { return null; }

    @SuppressLint("SetJavaScriptEnabled")
    public void initWebView(String user, String repo) {

        WebView webView = new WebView(getApplicationContext());

        webView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));

        container = getLayout();
        container.addView(webView, 1);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setDomStorageEnabled(true);//设置适应Html5

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "show " + url);

                if (Constants.isHybridPage(url)) {
                    if (url.startsWith("http://test.com/repo")) {
                    }
                } else {

                }

                view.loadUrl(url);
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

        Log.i(TAG, url);

        webView.loadUrl(url);
    }
}
