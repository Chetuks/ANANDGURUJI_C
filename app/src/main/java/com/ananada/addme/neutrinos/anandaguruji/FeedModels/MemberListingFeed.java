package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.MemberDetails;

import java.util.List;

/**
 * Created by mahiti on 21/6/15.
 */
public class MemberListingFeed {

    String message;
    String status;
    List<MemberDetails> list_spouse;
    List<MemberDetails> list_all;
    List<MemberDetails> list_new;

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

    public List<MemberDetails> getList_spouse() {
        return list_spouse;
    }

    public void setList_spouse(List<MemberDetails> list_spouse) {
        this.list_spouse = list_spouse;
    }

    public List<MemberDetails> getList_all() {
        return list_all;
    }

    public void setList_all(List<MemberDetails> list_all) {
        this.list_all = list_all;
    }

    public List<MemberDetails> getList_new() {
        return list_new;
    }

    public void setList_new(List<MemberDetails> list_new) {
        this.list_new = list_new;
    }
}
