package com.stormphoenix.ogit.mvp.view;

import android.widget.ImageView;

import com.stormphoenix.ogit.mvp.view.base.OwnerDetailsView;

/**
 * Created by wanlei on 18-3-5.
 */

public interface UserDetailsView extends OwnerDetailsView {
    void showMessage(String message);

    void setStaredCount(String count);

    void setIsFollow(boolean isFollow);
}
