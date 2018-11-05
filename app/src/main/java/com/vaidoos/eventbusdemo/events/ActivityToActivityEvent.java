package com.vaidoos.eventbusdemo.events;

public class ActivityToActivityEvent {

    public String message;

    public ActivityToActivityEvent() {
    }

    public ActivityToActivityEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
