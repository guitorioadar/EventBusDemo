package com.vaidoos.eventbusdemo.events;

public class ActivityToFragmentEvent {
    private String customMessage;

    public ActivityToFragmentEvent() {
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }
}
