package com.tamcapp.mobilebook.mobile.pojos;

public class Session {

    int id;
    String presenterName;
    String title;
    String sessionAbstract;
    String topic;
    String room;
        Slot slot;

    public Session() {
        super();
    }

    public Session(int id, String presenterName, String title, String sessionAbstract, String topic, String room,
                   Slot slot) {
        super();
        this.id = id;
        this.presenterName = presenterName;
        this.title = title;
        this.sessionAbstract = sessionAbstract;
        this.topic = topic;
        this.room = room;
        this.slot = slot;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
    }

    public String getPresenterName() {
        return presenterName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setSessionAbstract(String sessionAbstract) {
        this.sessionAbstract = sessionAbstract;
    }

    public String getSessionAbstract() {
        return sessionAbstract;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }
}
