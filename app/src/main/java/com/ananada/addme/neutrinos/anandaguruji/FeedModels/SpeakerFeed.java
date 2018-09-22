package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.Speaker;

import java.util.List;

/**
 * Created by mahiti on 18/6/15.
 */
public class SpeakerFeed {

    String message;
    String status;
    List<Speaker> list_speaker;

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

    public List<Speaker> getList_speaker() {
        return list_speaker;
    }

    public void setList_speaker(List<Speaker> list_speaker) {
        this.list_speaker = list_speaker;
    }
}
