package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.MemberBenifits;

import java.util.List;

/**
 * Created by mahiti on 23/6/15.
 */
public class MemberbenifitsFeed {

    String status;
    List<MemberBenifits> list_benifits;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MemberBenifits> getList_benifits() {
        return list_benifits;
    }

    public void setList_benifits(List<MemberBenifits> list_benifits) {
        this.list_benifits = list_benifits;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
