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

    private LogInteractor logInteractor;
    private Context mContext;

    public LogMaster(Context context) {
        mContext = context;
        this.logInteractor = new LogInteractor(context);
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

        sendBatchLogs(string);

        android.util.Log.i(TAG, log.getBase().getName());
    }

    public void sendBatchLogs (String logs) {

        logInteractor.batchLog(logs)
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe((Observer<retrofit2.Response<Vacant>>) new Subscriber<retrofit2.Response<Vacant>>() {
                    @Override
                    public void onCompleted() {
                        android.util.Log.i(TAG, "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(retrofit2.Response<Vacant> response) {
                        if (response.isSuccessful()) {
                            android.util.Log.i(TAG, "onNext: OK sent message");
                        } else {
                            android.util.Log.e(TAG, "onNext: Errrrrrrrrrrrrror");
                        }
                    }
                });
    }
}
