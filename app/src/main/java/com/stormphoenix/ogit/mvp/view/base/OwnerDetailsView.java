package com.stormphoenix.ogit.mvp.view.base;

import com.stormphoenix.ogit.widget.ImageHorizontalKeyValueLabel;
import com.stormphoenix.ogit.widget.ImageVerticalKeyValueLabel;

/**
 * Created by wanlei on 18-3-14.
 */

public interface OwnerDetailsView extends BaseUIView {
    void addBaseLabel(ImageVerticalKeyValueLabel label);

//    void addDynamicLabel(ImageHorizontalKeyValueLabel label);

    void setFollowersCount(String followersCount);

    void setFollowingCount(String followeringCount);

    void setUpToolbar(String title);

    void setOwnerDescription(String description);

    void stopProgress();

    void showProgress();

    void loadHeaderImage(String url);

    void initWebView(String user, String repo);
}
