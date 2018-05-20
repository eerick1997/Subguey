package com.example.erick.adooproject;

public class Events {
    private String desc_event;
    private int image_event;

    public Events() {
    }

    public Events(String desc_event, int image_event) {
        this.desc_event = desc_event;
        this.image_event = image_event;
    }

    public String getDesc_event() {
        return desc_event;
    }

    public void setDesc_event(String desc_event) {
        this.desc_event = desc_event;
    }

    public int getImage_event() {
        return image_event;
    }

    public void setImage_event(int image_event) {
        this.image_event = image_event;
    }
}

