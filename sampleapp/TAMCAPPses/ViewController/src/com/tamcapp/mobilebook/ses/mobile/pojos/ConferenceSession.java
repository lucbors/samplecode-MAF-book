package com.tamcapp.mobilebook.ses.mobile.pojos;

import java.util.Date;

public class ConferenceSession {

    int id;
    String presenterName;
    String title;
    String sessionAbstract;
    String topic;
    String room;
    Date sessiondate;
    Date starttime;
    Date endtime;
    Slot slot;

    public ConferenceSession() {
        super();
    }

    public ConferenceSession(int id, String presenterName, String title, String sessionAbstract, String topic, String room,
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

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
      
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setSessiondate(Date sessiondate) {
        this.sessiondate = sessiondate;
    }

    public Date getSessiondate() {
        return sessiondate;
    }
}
