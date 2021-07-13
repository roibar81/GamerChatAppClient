package com.example.gamerchatapp.dm;

import android.os.Parcel;
import android.os.Parcelable;

public class Header implements Parcelable {
    private String action;

    public Header() {
        this.action = "";
    }

    public Header(String action) {
        this.action = action;
    }

    protected Header(Parcel in) {
        action = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(action);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Header> CREATOR = new Creator<Header>() {
        @Override
        public Header createFromParcel(Parcel in) {
            return new Header(in);
        }

        @Override
        public Header[] newArray(int size) {
            return new Header[size];
        }
    };

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Header [action=" + action + "]";
    }

}
