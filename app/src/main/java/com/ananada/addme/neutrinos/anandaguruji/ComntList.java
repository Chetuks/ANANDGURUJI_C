package com.ananada.addme.neutrinos.anandaguruji;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ComntList implements Parcelable {
    public ComntList() {
    }

    String UUID;
    String cmntText;

    protected ComntList(Parcel in) {
        UUID = in.readString();
        cmntText = in.readString();
    }

    public static final Creator<ComntList> CREATOR = new Creator<ComntList>() {
        @Override
        public ComntList createFromParcel(Parcel in) {
            return new ComntList(in);
        }

        @Override
        public ComntList[] newArray(int size) {
            return new ComntList[size];
        }
    };

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getCmntText() {
        return cmntText;
    }

    public void setCmntText(String cmntText) {
        this.cmntText = cmntText;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(UUID);
        dest.writeString(cmntText);
    }
}
