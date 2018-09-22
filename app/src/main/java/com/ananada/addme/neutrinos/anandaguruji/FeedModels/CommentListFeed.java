package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.MemberDetails;

import java.util.List;

/**
 * Created by mahiti on 18/6/15.
 */
public class CommentListFeed {

    String status;
    String message;

    List<MemberDetails> list_comments;

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

    public List<MemberDetails> getList_comments() {
        return list_comments;
    }

    public void setList_comments(List<MemberDetails> list_comments) {
        this.list_comments = list_comments;
    }
}
