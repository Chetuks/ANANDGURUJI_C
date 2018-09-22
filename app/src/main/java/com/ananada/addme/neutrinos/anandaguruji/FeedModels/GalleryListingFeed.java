package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.EventGallery;

import java.util.List;

/**
 * Created by mahiti on 19/6/15.
 */
public class GalleryListingFeed {

    String status;
    String message;
    String full_image;

    public String getFull_image() {
        return full_image;
    }

    public void setFull_image(String full_image) {
        this.full_image = full_image;
    }

    List<EventGallery> list_gallery;

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

    public List<EventGallery> getList_gallery() {
        return list_gallery;
    }

    public void setList_gallery(List<EventGallery> list_gallery) {
        this.list_gallery = list_gallery;
    }
}
