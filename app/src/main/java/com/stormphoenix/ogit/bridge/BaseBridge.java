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
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.stormphoenix.ogit.mvp.ui.activities.base.HybridActivity;


/**
 * @author mthli @ Zhihu Inc.
 * @since 08-15-2016
 */
public class BaseBridge {

    public static String TAG = BaseBridge.class.getSimpleName();

    private HybridActivity activity;

    public BaseBridge(HybridActivity activity) {
        this.activity = activity;
    }



}
