package com.ananada.addme.neutrinos.anandaguruji.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mahiti on 22/6/15.
 */
public class ListImagesViews implements Serializable {

    ArrayList<ListImages> list;
    String url;
    String date;
    String name;

    public ArrayList<ListImages> getList() {
        return list;
    }

    public void setList(ArrayList<ListImages> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
