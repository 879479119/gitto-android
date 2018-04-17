package com.stormphoenix.ogit.dagger2.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wanlei on 18-2-26.
 */

@Module
public class ContextModule {
    private Activity mActivity;

    public ContextModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Context provideContext() {
        return mActivity;
    }
}
