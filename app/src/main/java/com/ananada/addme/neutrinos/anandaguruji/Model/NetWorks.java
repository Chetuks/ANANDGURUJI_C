package com.ananada.addme.neutrinos.anandaguruji.Model;

import java.io.Serializable;

/**
 * Created by mahiti on 23/6/15.
 */
public class NetWorks implements Serializable {

    String id;
    String name;
    String image;
    String image_url;
    String status;
    int getMemberStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getGetMemberStatus() {
        return getMemberStatus;
    }

    public void setGetMemberStatus(int getMemberStatus) {
        this.getMemberStatus = getMemberStatus;
    }
}
