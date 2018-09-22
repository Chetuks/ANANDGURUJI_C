package com.ananada.addme.neutrinos.anandaguruji.Model;

/**
 * Created by mahiti on 18/6/15.
 */
public class Speaker {

    String speaker_id;
    String speaker_name;
    String image_url;
    String designation;
    String company_name;
    String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpeaker_id() {
        return speaker_id;
    }

    public void setSpeaker_id(String speaker_id) {
        this.speaker_id = speaker_id;
    }

    public String getSpeaker_name() {
        return speaker_name;
    }

    public void setSpeaker_name(String speaker_name) {
        this.speaker_name = speaker_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
}
