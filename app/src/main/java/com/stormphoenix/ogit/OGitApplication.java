package com.stormphoenix.ogit;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.stormphoenix.ogit.bridge.Tracker;
import com.stormphoenix.ogit.utils.EncodingUtils;
import com.stormphoenix.ogit.utils.ImageUtils;
import com.stormphoenix.ogit.utils.PreferenceUtils;

/**
 * Created by wanlei on 18-2-25.
 */

public class OGitApplication extends Application {
    public static OGitApplication instance = null;
    // 是重新启动了应用吗？
    public boolean isFirstIn = true;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initImageLoader();

        String name = PreferenceUtils.getUsername(getApplicationContext());

        String sessionId = EncodingUtils.uuid(name);

        new Tracker(getApplicationContext(), sessionId, name);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initImageLoader() {
        ImageUtils.getInstance().init(this);
    }
}
