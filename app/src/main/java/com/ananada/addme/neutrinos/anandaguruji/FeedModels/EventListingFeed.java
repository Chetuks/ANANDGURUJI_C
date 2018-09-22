package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.Events;

import java.util.List;

/**
 * Created by mahiti on 18/6/15.
 */
public class EventListingFeed {

    String status;
    String message;
    List<Events> list_events;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Events> getList_events() {
        return list_events;
    }

    public void setList_events(List<Events> list_events) {
        this.list_events = list_events;
    }


}
