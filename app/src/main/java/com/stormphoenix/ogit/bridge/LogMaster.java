package com.stormphoenix.ogit.bridge;

import android.content.Context;
import android.webkit.JavascriptInterface;

import com.google.gson.Gson;
import com.stormphoenix.ogit.entity.log.ABInfo;
import com.stormphoenix.ogit.entity.log.Response;
import com.stormphoenix.ogit.log.Log;
import com.stormphoenix.ogit.log.Vacant;
import com.stormphoenix.ogit.mvp.model.interactor.LogInteractor;
import com.stormphoenix.ogit.mvp.ui.activities.base.HybridActivity;
import com.stormphoenix.ogit.shares.rx.RxJavaCustomTransformer;
import com.stormphoenix.ogit.utils.ActivityUtils;
import com.stormphoenix.ogit.utils.PreferenceUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import rx.Observer;
import rx.Subscriber;

/**
 * 用于从webView中取得用户埋点信息并进行对应的格式化
 */

public class LogMaster {


    public static String TAG = LogMaster.class.getSimpleName();

//    private LogInteractor logInteractor;
    private Context mContext;
//    private LogStorage logStorage;
    private Tracker tracker;

    public LogMaster(Context context) {
        mContext = context;
//        this.logInteractor = new LogInteractor(context);
//        this.logStorage = new LogStorage(mContext);
    }

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

        log.getBase().setName(PreferenceUtils.getUsername(mContext));
        log.getBase().setSessionId(Tracker.getInstance().getSessionId());

        tracker.saveLogs(string);

        tracker.sendBatchLogs(string);

        android.util.Log.i(TAG, log.getBase().getName());
    }

}
