package com.ananada.addme.neutrinos.anandaguruji.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahiti on 14/6/15.
 */
public class EventGallery implements Serializable {

    String event_name;
    String event_id;
    String event_date;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    String image_url;
   List<String> list_images;

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getId() {
        return event_id;
    }

    public void setId(String id) {
        this.event_id = event_id;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public List<String> getList_images() {
        return list_images;
    }

    public void setList_images(List<String> list_images) {
        this.list_images = list_images;
    }
}
