package com.stormphoenix.ogit.log;

import com.stormphoenix.ogit.log.type.Event;
import com.stormphoenix.ogit.log.type.Ping;
import com.stormphoenix.ogit.log.type.View;

public class Detail {

    public Detail(Event event) {
        this.event = event;
    }

    public Detail(Error error) {
        this.error = error;
    }

    public Detail(Ping ping) {
        this.ping = ping;
    }

    public Detail(View view) {
        this.view = view;
    }

    private Event event;

    private Error error;

    private Ping ping;

    private View view;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Ping getPing() {
        return ping;
    }

    public void setPing(Ping ping) {
        this.ping = ping;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }
}
