package model;

import java.io.Serializable;

public class Message implements Serializable {
    private String user;
    private String text;
    private String destination;

    private Message(){}

    public Message (String user, String text) {
        this.user = user;
        this.text = text;
    }

    public String getUser () {
        return user;
    }

    public String getText () {
        return text;
    }

    @Override
    public String toString () {
        return user + ": " + text;
    }
}

