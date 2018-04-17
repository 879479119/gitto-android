package com.stormphoenix.ogit.mvp.presenter.base;

import android.content.Context;

import com.stormphoenix.ogit.mvp.contract.BaseContract;
import com.stormphoenix.ogit.mvp.presenter.BasePresenter;
import com.stormphoenix.ogit.widget.ImageHorizontalKeyValueLabel;
import com.stormphoenix.ogit.widget.ImageVerticalKeyValueLabel;

/**
 * Created by wanlei on 18-3-19.
 */

public class OwnerProfilePresenter<V extends BaseContract.View> extends BasePresenter<V> {
    protected Context mContext;

    public OwnerProfilePresenter(Context context) {
        super();
        mContext = context;
    }

    protected ImageVerticalKeyValueLabel createBaseLabel(String key, String value, int resId) {
        ImageVerticalKeyValueLabel label = new ImageVerticalKeyValueLabel(mContext);
        label.setKeyName(key);
        label.setValueName(value);
        label.setImageDrawableResourceId(resId);
        return label;
    }

    protected ImageHorizontalKeyValueLabel createDynamicLabel(String key, String value, int resId) {
        ImageHorizontalKeyValueLabel label = new ImageHorizontalKeyValueLabel(mContext);
        label.setValueName(value);
        label.setKeyName(key);
        label.setImageDrawableResourceId(resId);
        return label;
    }

}
