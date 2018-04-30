package com.stormphoenix.ogit.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by wanlei on 18-2-26.
 */

public class PreferenceUtils {
    // 用户头像地址
    public static final String AVATAR_URL = "AVATAR_URL";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "pasword";
    public static final String ABTEST = "abtest";
    public static final String TOKEN = "token";
    public static final String IS_LOGIN = "is_login";
    public static final String WRAP = "wrap";


    public static final String CLIENT_ID = "29d1a620d41d6f07587a";
    public static final String CLIENT_SECRET = "5ff43c7722a37e272fa20db77e1ca1de74c9ea38";

    public static boolean isLogin(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(IS_LOGIN, false);
    }

    public static String getString(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(key, null);
    }


    public static String getAbtest(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(ABTEST, null);
    }

    public static String getToken(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(TOKEN, null);
    }

    public static String getUsername(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(USERNAME, null);
    }

    public static String getPassword(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(PASSWORD, null);
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(key, value).commit();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putBoolean(key, value).commit();
    }

    /**
     * Get code browsing preferences
     *
     * @param context
     * @return preferences
     */
    public static SharedPreferences getCodePreferences(final Context context) {
        return context.getSharedPreferences("code", MODE_PRIVATE);
    }
}
