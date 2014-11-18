package com.tamcapp.mobilebook.mobile.model;


import com.tamcapp.mobilebook.mobile.pojos.Schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;


public class ScheduledSessions {
    
    private List s_scheduledSessions = null;
    private int scheduledSessionCount = -1;
    private int deleteSessionNo = 0;
    private int currentSession = 0;
    
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }


    public ScheduledSessions() {
        super();
    }


    public Schedule[] getScheduledSessions() {
          //This Method gets a list of the employees and their emails
          Schedule[] scheduledSessions = null;
          s_scheduledSessions = new ArrayList();
          s_scheduledSessions.add(new Schedule(0, "Presenter One"   , "Oracle MAF Explained"            ,"bla bla", "MAF"     , "room one",false, getADate(2014,6,1,9,0),getADate(2014,6,1,9,45)  )) ;
          s_scheduledSessions.add(new Schedule(1, "Presenter Two"   , "Deep Dive into Oracle MAF "       ,"bla bla", "Deep Dive"      , "room one",false,  getADate(2014,6,1,10,0),getADate(2014,6,1,10,45) )) ;
          s_scheduledSessions.add(new Schedule(2, "Presenter Three" , "Oracle MAF  Best Practices"       ,"bla bla", "Best Practices" , "room one",false,  getADate(2014,6,1,11,0),getADate(2014,6,1,11,45) )) ;
          s_scheduledSessions.add(new Schedule(3, "Presenter Four"  , "Oracle MAF  Real Life Experiences","bla bla", "Real Life"      , "room one",false,  getADate(2014,6,2,9,0),getADate(2014,6,2,9,45)  )) ;
          s_scheduledSessions.add(new Schedule(4, "Presenter Five"  , "One more session on Oracle MAF"  ,"bla bla", "MAF"     , "room one",false,  getADate(2014,6,2,10,15),getADate(2014,6,2,11,0) )) ;
          s_scheduledSessions.add(new Schedule(5, "Presenter Three" , "Best Practices for building an Oracle MAF  APP"       ,"bla bla", "Best Practices" , "room one",false,  getADate(2014,6,3,9,0),getADate(2014,6,3,10,30) )) ;
          s_scheduledSessions.add(new Schedule(6, "Presenter Four"  , "Pitfalls with Oracle MAF ","bla bla", "Real Life"      , "room one",false,  getADate(2014,6,3,10,45),getADate(2014,6,3,11,30)  )) ;
          s_scheduledSessions.add(new Schedule(7, "Presenter Five"  , "Final session on Oracle MAF"  ,"bla bla", "MAF"     , "room one",false,  getADate(2014,6,1,13,0),getADate(2014,6,1,14,0) )) ;
            
        
          scheduledSessions = (Schedule[]) s_scheduledSessions.toArray(new Schedule[s_scheduledSessions.size()]);
          return scheduledSessions;
        } 
    
    private Date getADate(int y,int m, int d,int h, int mi) {
        Calendar c1 = Calendar.getInstance();
        c1.set(y, m, d, h, mi);
        Date retDate = c1.getTime();
        return retDate;
      } 
    
    public void ShowDeleteButton(int id) {
        Schedule s = null;
        int count = getScheduledSessionCount();
        for (int x = 0; x < count; x++) {
            s = (Schedule)s_scheduledSessions.get(x);
            if (s.getId() == id) {
                s.setShowDelete(true);
            } else if (s.isShowDelete()) {
                s.setShowDelete(false);
            }
        }
    }

    // This method clears all delete buttons.  We do this when a delete button exists but someone taps on another row

    public void ClearDeleteButton() {
        Schedule s = null;
        int count = getScheduledSessionCount();
        for (int x = 0; x < count; x++) {
            s = (Schedule)s_scheduledSessions.get(x);
            if (s.isShowDelete()) {
                s.setShowDelete(false);
            }
        }
    }
    public int getScheduledSessionCount() {
        return s_scheduledSessions.size();
    }


    public void setScheduledSessionCount(int scheduledSessionCount) {
        int oldScheduledSessionCount = this.scheduledSessionCount;
        this.scheduledSessionCount = scheduledSessionCount;
        propertyChangeSupport.firePropertyChange("scheduledSessionCount", oldScheduledSessionCount, scheduledSessionCount);
    }

    
    
    
    

    public void setDeleteSessionNo(int deleteSessionNo) {
        int oldDeleteSessionNo = this.deleteSessionNo;
        this.deleteSessionNo = deleteSessionNo;
        propertyChangeSupport.firePropertyChange("deleteSessionNo", oldDeleteSessionNo, deleteSessionNo);
    }

    public int getDeleteSessionNo() {
        return deleteSessionNo;
    }

    public void setCurrentSession(int currentSession) {
        int oldCurrentSession = this.currentSession;
        this.currentSession = currentSession;
        propertyChangeSupport.firePropertyChange("currentSession", oldCurrentSession, currentSession);
    }

    public int getCurrentSession() {
        return currentSession;
    }
}
