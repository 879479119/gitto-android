package com.stormphoenix.ogit.mvp.view;

import com.stormphoenix.ogit.mvp.view.base.BaseUIView;
import com.stormphoenix.ogit.mvp.view.base.OwnerDetailsView;
import com.stormphoenix.ogit.widget.ImageHorizontalKeyValueLabel;
import com.stormphoenix.ogit.widget.ImageVerticalKeyValueLabel;

/**
 * Created by wanlei on 18-3-14.
 */

public interface OrgDetailsView extends OwnerDetailsView {
    void setMembersCount(String memberCount);
}
