package com.stormphoenix.ogit.mvp.ui.activities.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.stormphoenix.ogit.R;
import com.stormphoenix.ogit.bridge.Tracker;
import com.stormphoenix.ogit.dagger2.InjectorInitializer;

import java.util.Date;

import butterknife.ButterKnife;

/**
 * Created by wanlei on 18-2-25.
 */

public abstract class BaseActivity extends AppCompatActivity implements InjectorInitializer {
    public String TAG = this.getClass().getSimpleName();

    private long createTime;
    private boolean isForeground;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
                if (isForeground) {
                    createTime = System.currentTimeMillis();
                }
                Log.e(TAG, "亮屏啦，，");
            }
            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {

                final long launchTiming = System.currentTimeMillis() - createTime;

                Log.i(TAG, "heile: Base " + launchTiming);

                Log.e(TAG, "黑屏啦，，");
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(getLayoutId());
        initializeInjector();
        ButterKnife.bind(this);
        setUpEnterTransitionAnim();


        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver, filter);
    }

    /**
     * 设置系统状态栏的样式
     */
    private void setStatusBar() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//此FLAG可使状态栏透明，且当前视图在绘制时，从屏幕顶端开始即top = 0开始绘制，这也是实现沉浸效果的基础
//            this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//可不加
//        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);
        tintManager.setStatusBarTintEnabled(true);
    }

    private void setUpEnterTransitionAnim() {
    }

    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        isForeground = false;
        super.onDestroy();
        final long launchTiming = System.currentTimeMillis() - this.createTime;

        Log.i(TAG, "onDestroy: Base " + launchTiming);
        unregisterReceiver(receiver);
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
        final long launchTiming = System.currentTimeMillis() - this.createTime;

        Tracker.getInstance().trackNativePageSpan(TAG, launchTiming, new Date(), new Date());
        Log.i(TAG, "onPause: Base " + launchTiming);
    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
        createTime = System.currentTimeMillis();
        Log.i(TAG, "onResume: Base");
    }

    @Override
    protected void onStart() {
        isForeground = true;
        super.onStart();
        Log.i(TAG, "onStart: Base");
        createTime = System.currentTimeMillis();
    }
}
