package com.stormphoenix.ogit.log.type;

import com.stormphoenix.ogit.log.subType.CardShow;
import com.stormphoenix.ogit.log.subType.PageShow;

public class View {

    private long activeTime;

    private PageShow pageShow;

    private CardShow cardShow;

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public PageShow getPageShow() {
        return pageShow;
    }

    public void setPageShow(PageShow pageShow) {
        this.pageShow = pageShow;
    }

    public CardShow getCardShow() {
        return cardShow;
    }

    public void setCardShow(CardShow cardShow) {
        this.cardShow = cardShow;
    }
}
