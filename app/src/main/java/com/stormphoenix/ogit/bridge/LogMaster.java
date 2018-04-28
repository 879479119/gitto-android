package com.stormphoenix.ogit.bridge;

import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.stormphoenix.ogit.log.Log;
import com.stormphoenix.ogit.mvp.ui.activities.base.HybridActivity;

/**
 * 用于从webView中取得用户埋点信息并进行对应的格式化
 */

public class LogMaster {


    public static String TAG = LogMaster.class.getSimpleName();

    @JavascriptInterface
    public void setLog (String string) {
        Gson gson = new Gson();
        Log log;
        try {
            log = gson.fromJson(string, Log.class);
        } catch (Exception err) {
            log = new Log();
            android.util.Log.e(TAG, err.getMessage());
        }

        android.util.Log.i(TAG, log.getBase().getName());
    }
}
