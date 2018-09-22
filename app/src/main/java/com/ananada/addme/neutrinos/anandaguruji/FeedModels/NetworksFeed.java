package com.ananada.addme.neutrinos.anandaguruji.FeedModels;


import com.ananada.addme.neutrinos.anandaguruji.Model.NetWorks;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahiti on 23/6/15.
 */
public class NetworksFeed implements Serializable {

    String status;
    String message;
    List<NetWorks> list_networks;

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

    public List<NetWorks> getList_networks() {
        return list_networks;
    }

    public void setList_networks(List<NetWorks> list_networks) {
        this.list_networks = list_networks;
    }
}
