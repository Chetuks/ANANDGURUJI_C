package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.Attendee;

import java.util.List;

/**
 * Created by mahiti on 18/6/15.
 */
public class AttendeesFeed {

    String message;
    String status;
    List<Attendee> list_attendees;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Attendee> getList_attendees() {
        return list_attendees;
    }

    public void setList_attendees(List<Attendee> list_attendees) {
        this.list_attendees = list_attendees;
    }
}
