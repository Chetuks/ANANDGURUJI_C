package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Whats_new;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahiti on 17/6/15.
 */
public class WhatsNewFeed implements Serializable {

    String url;
    String status;
    List<Whats_new> list_whats_new;
    String message;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Whats_new> getList_whats_new() {
        return list_whats_new;
    }

    public void setList_whats_new(List<Whats_new> list_whats_new) {
        this.list_whats_new = list_whats_new;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
