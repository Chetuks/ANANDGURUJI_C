package com.ananada.addme.neutrinos.anandaguruji.Model;

/**
 * Created by mahiti on 20/7/15.
 */
public class HomeDetails {

    String url;
    Reminders remind;
    Events event;
    String st;

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    String msg;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Reminders getRemind() {
        return remind;
    }

    public void setRemind(Reminders remind) {
        this.remind = remind;
    }

    public Events getEvent() {
        return event;
    }

    public void setEvent(Events event) {
        this.event = event;
    }
}
