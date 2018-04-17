package com.stormphoenix.ogit.mvp.model.api;

import com.stormphoenix.ogit.entity.github.GitNotification;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by wanlei on 18-3-17.
 */

public interface NotificationApi {
    @GET("notifications")
    Observable<Response<List<GitNotification>>> loadOwnerNotifications();
}







