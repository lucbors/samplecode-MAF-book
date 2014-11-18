package com.tamcapp.mobilebook.mobile.pojos;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class Schedule {

    int id;
    String presenterName;
    String title;
    String sessionAbstract;
    String topic;
    String room;
    Date sessionStartDateTime;
    Date sessionEndDateTime;
    String displayDateNoTime;
    String displayDate;
    protected boolean showDelete = false;
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public Schedule() {
        super();
    }

    public Schedule(int id, String presenterName, String title, String sessionAbstract, String topic, String room
             , boolean showDelete   ,    Date sessionStartDateTime , Date sessionEndDateTime) {
        super();
        this.id = id;
        this.presenterName = presenterName;
        this.title = title;
        this.sessionAbstract = sessionAbstract;
        this.topic = topic;
        this.room = room;
        this.showDelete = showDelete;                                                      
        this.sessionStartDateTime = sessionStartDateTime;
        this.sessionEndDateTime = sessionEndDateTime;
       
        SimpleDateFormat sdf = new SimpleDateFormat(); 
        sdf = new SimpleDateFormat("dd MMM yyyy HH:mm",  Locale.US);
        
        setDisplayDate(sdf.format(sessionStartDateTime));
        
        
        SimpleDateFormat sdfNoTime = new SimpleDateFormat(); 
        sdfNoTime = new SimpleDateFormat("dd MMM yyyy",  Locale.US);
        
        setDisplayDateNoTime (sdfNoTime.format(sessionStartDateTime));  
        
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

   

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    

    public void setShowDelete(boolean showDelete) {
        boolean oldShowDelete = this.showDelete;
        this.showDelete = showDelete;
        propertyChangeSupport.firePropertyChange("showDelete", oldShowDelete, showDelete);
    }

    public boolean isShowDelete() {
        return showDelete;
    }

    public void setDisplayDateNoTime(String displayDateNoTime) {
        this.displayDateNoTime = displayDateNoTime;
    }

    public String getDisplayDateNoTime() {
        return displayDateNoTime;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setSessionStartDateTime(Date sessionStartDateTime) {
        this.sessionStartDateTime = sessionStartDateTime;
    }

    public Date getSessionStartDateTime() {
        return sessionStartDateTime;
    }

    public void setSessionEndDateTime(Date sessionEndDateTime) {
        this.sessionEndDateTime = sessionEndDateTime;
    }

    public Date getSessionEndDateTime() {
        return sessionEndDateTime;
    }
}
