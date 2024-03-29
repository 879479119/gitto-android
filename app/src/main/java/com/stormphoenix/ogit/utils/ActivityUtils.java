package com.stormphoenix.ogit.utils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by wanlei on 18-3-6.
 */

public class ActivityUtils {
    public static void startActivity(Context context, Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (context instanceof Activity) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
                context.startActivity(intent, options.toBundle());
            } else {
                context.startActivity(intent);
            }
        } else {
            context.startActivity(intent);
        }
    }

    public static void startActivityForResult(Activity context, Intent intent, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (context instanceof Activity) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) context);
                context.startActivityForResult(intent, requestCode, options.toBundle());
            } else {
                context.startActivityForResult(intent, requestCode);
            }
        } else {
            context.startActivityForResult(intent, requestCode);
        }
    }
}
