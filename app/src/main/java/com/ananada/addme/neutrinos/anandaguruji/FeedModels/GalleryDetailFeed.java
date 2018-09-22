package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.ListImages;

import java.util.List;

/**
 * Created by mahiti on 19/6/15.
 */
public class GalleryDetailFeed {
    String status;
    String message;
    List<ListImages> list;

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

    public List<ListImages> getList() {
        return list;
    }

    public void setList(List<ListImages> list) {
        this.list = list;
    }
}
