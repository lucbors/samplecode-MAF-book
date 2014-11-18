package com.tamcapp.mobilebook.ses.mobile.pojos;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;


public class Slot {

private Date sessionDate;
private Date startTime;
private Date endTime;
private String displayDate;
private String displayDateNoTime;
private String displayStartTime;
private String displayEndTime;

    public Slot() {
        super();
    }

    public Slot(Date sessionDate, Date startTime, Date endTime){
        this.sessionDate = sessionDate;
        this.startTime = startTime;
        this.endTime = endTime;
        SimpleDateFormat sdf = new SimpleDateFormat(); 
        sdf = new SimpleDateFormat("dd MMM yyyy HH:mm",  Locale.US);
        
        setDisplayDate(sdf.format(sessionDate));
        
        
        SimpleDateFormat sdfNoTime = new SimpleDateFormat(); 
        sdfNoTime = new SimpleDateFormat("dd MMM yyyy",  Locale.US);
        
        setDisplayDateNoTime (sdfNoTime.format(sessionDate)); 
        
        SimpleDateFormat sdfTime = new SimpleDateFormat(); 
        sdfTime = new SimpleDateFormat("HH:mm",  Locale.US);
        setDisplayStartTime(sdfTime.format(startTime));
        
        setDisplayEndTime(sdfTime.format(endTime));
        
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
        
        SimpleDateFormat sdf = new SimpleDateFormat(); 
        sdf = new SimpleDateFormat("dd MMM yyyy HH:mm",  Locale.US);
        
        setDisplayDate(sdf.format(sessionDate));

    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }


    public void setDisplayDateNoTime(String displayDateNoTime) {
        this.displayDateNoTime = displayDateNoTime;
    }

    public String getDisplayDateNoTime() {
        return displayDateNoTime;
    }

    public void setDisplayStartTime(String displayStartTime) {
        this.displayStartTime = displayStartTime;
    }

    public String getDisplayStartTime() {
        return displayStartTime;
    }

    public void setDisplayEndTime(String displayEndTime) {
        this.displayEndTime = displayEndTime;
    }

    public String getDisplayEndTime() {
        return displayEndTime;
    }
}