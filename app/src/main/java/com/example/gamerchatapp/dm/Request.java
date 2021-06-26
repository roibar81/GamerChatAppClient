package com.example.gamerchatapp.dm;

public class Request {

    private Header header;
    private Body body;

    public Request(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

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
        return "Request{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
