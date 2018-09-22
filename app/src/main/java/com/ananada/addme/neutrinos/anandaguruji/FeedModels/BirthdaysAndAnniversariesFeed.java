package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.MemberDetails;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahiti on 21/6/15.
 */
public class BirthdaysAndAnniversariesFeed implements Serializable {

    String status;
    String message;
    List<MemberDetails> list_birthday;
    List<MemberDetails> list_anniversaries;

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

    public List<MemberDetails> getList_birthday() {
        return list_birthday;
    }

    public void setList_birthday(List<MemberDetails> list_birthday) {
        this.list_birthday = list_birthday;
    }

    public List<MemberDetails> getList_anniversaries() {
        return list_anniversaries;
    }

    public void setList_anniversaries(List<MemberDetails> list_anniversaries) {
        this.list_anniversaries = list_anniversaries;
    }
}
