package com.example.gamerchatapp.dm;

public class Response {

    private Header header;
    private Body body;

    public Response(Header header, Body body) {
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
        return "Response{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }

}
