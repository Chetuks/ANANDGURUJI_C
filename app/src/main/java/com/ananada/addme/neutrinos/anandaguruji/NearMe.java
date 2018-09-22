package com.ananada.addme.neutrinos.anandaguruji;

/**
 * Created by mahiti on 20/03/18.
 */

public class NearMe {
    int id;
    String name;
    String image;

    public NearMe(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
