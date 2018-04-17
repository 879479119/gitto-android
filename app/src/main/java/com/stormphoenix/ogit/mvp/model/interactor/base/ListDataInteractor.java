package com.stormphoenix.ogit.mvp.model.interactor.base;

import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by wanlei on 18-4-4.
 */

public interface ListDataInteractor<T> {
    Observable<Response<List<T>>> loadListData(int page);

    int dataType();
}
