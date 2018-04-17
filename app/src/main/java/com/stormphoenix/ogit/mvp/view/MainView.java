package com.stormphoenix.ogit.mvp.view;

import com.stormphoenix.ogit.entity.github.GitNotification;
import com.stormphoenix.ogit.mvp.view.base.MessageView;

import java.util.List;

/**
 * Created by wanlei on 18-2-25.
 */

public interface MainView extends MessageView {
    void saveNotificationMessage(List<GitNotification> notifications);
}
