package com.tamcapp.mobilebook.mobile.model;


import com.tamcapp.mobilebook.mobile.pojos.Session;
import com.tamcapp.mobilebook.mobile.pojos.Slot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sessions {
    
    private List s_sessions = null;

    public Sessions() {
        super();
    }


    public Session[] getSessions() {
          //This Method gets a list of the employees and their emails
          Session[] sessions = null;
          s_sessions = new ArrayList();
          s_sessions.add(new Session(0, "Luc Bors"   , "Oracle MAF Explained"            ,"This session explains the basics of " +
                                                                                               "ADF Mobile. Later in the session you " +
                                                                                               "will learn how to implement device " +
                                                                                               "interaction and security", "MAF"     , "room one", new Slot(getADate(2014,6,1,0,0),getADate(2014,6,1,9,0) ,getADate(2014,6,1,9,45)) )) ;
          s_sessions.add(new Session(1, "Presenter Two"   , "Deep Dive into AOracle MAF"       ,"bla bla", "Deep Dive"      , "room one", new Slot(getADate(2014,6,1,0,0),getADate(2014,6,1,10,0),getADate(2014,6,1,10,45)) )) ;
          s_sessions.add(new Session(2, "Presenter Three" , "Oracle MAF Best Practices"       ,"bla bla", "Best Practices" , "room one", new Slot(getADate(2014,6,1,0,0),getADate(2014,6,1,13,0),getADate(2014,6,1,13,45)) )) ;
          s_sessions.add(new Session(3, "Presenter Four"  , "Oracle MAF Real Life Experiences","bla bla", "Real Life"      , "room one", new Slot(getADate(2014,6,2,0,0),getADate(2014,6,2,9,0) ,getADate(2014,6,2,9,45)) )) ;
          s_sessions.add(new Session(4, "Presenter Five"  , "One more session on Oracle MAF"  ,"bla bla", "MAF"     , "room one", new Slot(getADate(2014,6,2,0,0),getADate(2014,6,2,10,0),getADate(2014,6,2,10,45)) )) ;
          sessions = (Session[]) s_sessions.toArray(new Session[s_sessions.size()]);
          return sessions;
        } 
    
    private Date getADate(int y,int m, int d,int h, int mi) {
        Calendar c1 = Calendar.getInstance();
        c1.set(y, m, d, h, mi);
        Date retDate = c1.getTime();
        return retDate;
      } 
}
