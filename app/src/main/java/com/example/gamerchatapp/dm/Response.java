package com.example.gamerchatapp.dm;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable {

    private Header header;
    private Body body;

    public Response() {

    }

    public Response(Header header, Body body) {
        this.header = header;
        this.body = body;
    }


    protected Response(Parcel in) {
        this.header = in.readParcelable(Header.class.getClassLoader());
        this.body = in.readParcelable(Body.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.header, flags);
        dest.writeParcelable(this.body, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Response [body=" + body + ", header=" + header + "]";
    }

}
