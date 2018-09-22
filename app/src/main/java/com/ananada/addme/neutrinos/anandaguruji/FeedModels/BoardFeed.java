package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.MemberDetails;

import java.util.List;

/**
 * Created by mahiti on 30/6/15.
 */
public class BoardFeed {

    String status;
    List<MemberDetails> list_memebers;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MemberDetails> getList_memebers() {
        return list_memebers;
    }

    public void setList_memebers(List<MemberDetails> list_memebers) {
        this.list_memebers = list_memebers;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
