package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.MemberDetails;
import com.ananada.addme.neutrinos.anandaguruji.Whats_new;

import java.util.ArrayList;

/**
 * Created by mahiti on 17/6/15.
 */
public class WhatsNewDetailFeed {

    String status;
    String message;
    Whats_new detail;


    String attendee_category;
   ArrayList<MemberDetails> list_comments;

    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public Whats_new getDetail() {
        return detail;
    }

    public void setDetail(Whats_new detail) {
        this.detail = detail;
    }

    public ArrayList<MemberDetails> getList_comments() {
        return list_comments;
    }

    public void setList_comments(ArrayList<MemberDetails> list_comments) {
        this.list_comments = list_comments;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getAttendee_category() {
        return attendee_category;
    }

    public void setAttendee_category(String attendee_category) {
        this.attendee_category = attendee_category;
    }

}
