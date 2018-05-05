package com.stormphoenix.ogit.log.type;

import com.stormphoenix.ogit.log.Detail;
import com.stormphoenix.ogit.log.subType.ClickItem;
import com.stormphoenix.ogit.log.subType.EnterApp;
import com.stormphoenix.ogit.log.subType.LeaveApp;
import com.stormphoenix.ogit.log.subType.PauseApp;
import com.stormphoenix.ogit.log.subType.SearchItem;

public class Event {

    private ClickItem clickItem;

    private SearchItem searchItem;

    private EnterApp enterApp;

    private PauseApp pauseApp;

    private LeaveApp leaveApp;

    public ClickItem getClickItem() {
        return clickItem;
    }

    public void setClickItem(ClickItem clickItem) {
        this.clickItem = clickItem;
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(SearchItem searchItem) {
        this.searchItem = searchItem;
    }

    public EnterApp getEnterApp() {
        return enterApp;
    }

    public void setEnterApp(EnterApp enterApp) {
        this.enterApp = enterApp;
    }

    public PauseApp getPauseApp() {
        return pauseApp;
    }

    public void setPauseApp(PauseApp pauseApp) {
        this.pauseApp = pauseApp;
    }

    public LeaveApp getLeaveApp() {
        return leaveApp;
    }

    public void setLeaveApp(LeaveApp leaveApp) {
        this.leaveApp = leaveApp;
    }
}
