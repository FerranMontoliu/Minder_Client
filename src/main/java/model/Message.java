package model;

import java.io.Serializable;

public class Message implements Serializable {
    private String source;
    private String text;
    private String destination;
    private String time;

    private Message(){}

    public Message (String user, String text) {
        this.source = user;
        this.text = text;
    }

    public String getSource() {
        return source;
    }

    public String getText () {
        return text;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString () {
        return "(" + time + ") "+ source + ": " + text;
    }
}

