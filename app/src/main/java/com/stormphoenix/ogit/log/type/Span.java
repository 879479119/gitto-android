package com.stormphoenix.ogit.log.type;
import com.stormphoenix.ogit.log.subType.View;

public class Span {

    private View view;

    public Span(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
