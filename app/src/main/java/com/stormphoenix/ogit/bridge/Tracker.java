/*
 * Copyright 2016 Zhihu Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.stormphoenix.ogit.bridge;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stormphoenix.ogit.log.AB;
import com.stormphoenix.ogit.log.Base;
import com.stormphoenix.ogit.log.Detail;
import com.stormphoenix.ogit.log.Vacant;
import com.stormphoenix.ogit.log.subType.EnterApp;
import com.stormphoenix.ogit.log.subType.PageShow;
import com.stormphoenix.ogit.log.subType.SearchItem;
import com.stormphoenix.ogit.log.type.Event;
import com.stormphoenix.ogit.log.type.Span;
import com.stormphoenix.ogit.log.type.View;
import com.stormphoenix.ogit.mvp.model.interactor.LogInteractor;
import com.stormphoenix.ogit.mvp.ui.activities.base.HybridActivity;
import com.stormphoenix.ogit.shares.rx.RxJavaCustomTransformer;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import retrofit2.Response;
import rx.Observer;
import rx.Subscriber;


/**
 * @author mthli @ Zhihu Inc.
 * @since 08-15-2016
 */
public class Tracker {

    public static String TAG = Tracker.class.getSimpleName();

    private LogInteractor logInteractor;
//    private Context mContext;
    private LogStorage logStorage;

    private String sessionId;
    private String name;
    private Base base;
    private Gson gson;

    public Tracker(Context context, String sessionID, String name) {
        instance = this;
//        mContext = context;
        this.logInteractor = new LogInteractor(context);
        this.logStorage = new LogStorage(context);
        this.sessionId = sessionID;
        this.name = name;
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    public String getSessionId() {
        return sessionId;
    }

    static private Tracker instance=null;

    public static Tracker getInstance(){
        return instance;
    }

    public void saveLogs(String string) {
        android.util.Log.i(TAG, "setLog: " + logStorage.load());
        logStorage.save(string);
    }

    public void sendBatchLogs (String logs) {

        logInteractor.batchLog(logs)
                .compose(RxJavaCustomTransformer.defaultSchedulers())
                .subscribe((Observer<Response<Vacant>>) new Subscriber<Response<Vacant>>() {
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

    private Base buildBase(int type, int subType, String sessionId, String name, String url) {
        return base = new Base(type, subType, 1, 1, 1, sessionId, name, new Date(), url);
    }

    private com.stormphoenix.ogit.log.Log getBasicLog () {

        com.stormphoenix.ogit.log.Log log = new com.stormphoenix.ogit.log.Log();
        // 暂且不要AB测试的参数，android的点暂不参与测试
        log.setAbList(new ArrayList<>());
        return log;
    }


    public void trackEnterApp (String url, long time) {
        com.stormphoenix.ogit.log.Log log = getBasicLog();

        log.setBase(buildBase(1, 4, sessionId, name, url));

        EnterApp enterApp = new EnterApp(new Date(), EnterApp.SOURCE.USER, time, false);
        Event event = new Event();
        event.setEnterApp(enterApp);
        log.setDetail(new Detail(event));

        sendBatchLogs(gson.toJson(log));
    }

    public void trackSearch (String url, String text) {
        com.stormphoenix.ogit.log.Log log = getBasicLog();

        log.setBase(buildBase(1, 1, sessionId, name, url));

        SearchItem searchItem = new SearchItem(text, SearchItem.TYPE.REPO);
        Event event = new Event();
        event.setSearchItem(searchItem);
        log.setDetail(new Detail(event));

        sendBatchLogs(gson.toJson(log));
    }

    public void trackPageShow (String sourceURL, String url) {
        com.stormphoenix.ogit.log.Log log = getBasicLog();

        log.setBase(buildBase(3, 10, sessionId, name, url));

        PageShow pageShow = new PageShow(sourceURL, url);
        View view = new View(pageShow);
        log.setDetail(new Detail(view));

        sendBatchLogs(gson.toJson(log));
    }

    public void trackNativePageSpan (String url, long time, Date startTime, Date endTime) {
        com.stormphoenix.ogit.log.Log log = getBasicLog();

        log.setBase(buildBase(2, 8, sessionId, name, url));

        com.stormphoenix.ogit.log.subType.View view = new com.stormphoenix.ogit.log.subType.View(time, url, startTime, endTime);
        Span span = new Span(view);
        log.setDetail(new Detail(span));

        sendBatchLogs(gson.toJson(log));
    }
}
