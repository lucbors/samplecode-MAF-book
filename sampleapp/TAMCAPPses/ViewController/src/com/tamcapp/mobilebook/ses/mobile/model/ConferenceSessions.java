package com.tamcapp.mobilebook.ses.mobile.model;


import com.sun.util.logging.Level;

import com.tamcapp.mobilebook.ses.mobile.pojos.ConferenceSession;
import com.tamcapp.mobilebook.ses.mobile.pojos.Slot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class ConferenceSessions {
    
    private List s_searchResults = null;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    private String previousSearchString;

    public ConferenceSessions() {
        super();
        if (s_searchResults == null) {
            s_searchResults = new ArrayList();
        }    }

/* Hardcoded Session  
    public ConferenceSession[] getConferenceSessions() {
          //This Method gets a list of the employees and their emails
          ConferenceSession[] sessions = null;
          s_searchResults = new ArrayList();
          s_searchResults.add(new ConferenceSession(0, "Luc Bors"   , "ADF Mobile Explained"            ,"This session explains the basics of " +
                                                                                               "ADF Mobile. Later in the session you " +
                                                                                               "will learn how to implement device " +
                                                                                               "interaction and security", "ADF Mobile"     , "room one", new Slot(getADate(2013,6,1,0,0),getADate(2013,6,1,9,0) ,getADate(2013,6,1,9,45)) )) ;
          s_searchResults.add(new ConferenceSession(1, "Presenter Two"   , "Deep Dive into ADF Mobile"       ,"bla bla", "Deep Dive"      , "room one", new Slot(getADate(2013,6,1,0,0),getADate(2013,6,1,10,0),getADate(2013,6,1,10,45)) )) ;
          s_searchResults.add(new ConferenceSession(2, "Presenter Three" , "ADF Mobile Best Practices"       ,"bla bla", "Best Practices" , "room one", new Slot(getADate(2013,6,1,0,0),getADate(2013,6,1,13,0),getADate(2013,6,1,13,45)) )) ;
          s_searchResults.add(new ConferenceSession(3, "Presenter Four"  , "ADF Mobile Real Life Experiences","bla bla", "Real Life"      , "room one", new Slot(getADate(2013,6,2,0,0),getADate(2013,6,2,9,0) ,getADate(2013,6,2,9,45)) )) ;
          s_searchResults.add(new ConferenceSession(4, "Presenter Five"  , "One more session on ADF Mobile"  ,"bla bla", "ADF Mobile"     , "room one", new Slot(getADate(2013,6,2,0,0),getADate(2013,6,2,10,0),getADate(2013,6,2,10,45)) )) ;
          sessions = (ConferenceSession[]) s_searchResults.toArray(new ConferenceSession[s_searchResults.size()]);
          return sessions;
        } 
end of hardcoded session */
    
    private Date getADate(int y,int m, int d,int h, int mi) {
        Calendar c1 = Calendar.getInstance();
        c1.set(y, m, d, h, mi);
        Date retDate = c1.getTime();
        return retDate;
      } 
/*  prog sessions */
    public ConferenceSession[] getConferenceSessions() {
         ConferenceSession l[] = null;
        l = ( ConferenceSession[])s_searchResults.toArray(new  ConferenceSession[s_searchResults.size()]);
        return l;
    }
/* */
    
    public void sortSearchResults(){
        
        Collections.sort(s_searchResults);
    }
    
    public void clearSearchResults() {
        s_searchResults.clear();
        providerChangeSupport.fireProviderRefresh("conferenceSessions");
    }
    
    public void addSearchResult(ConferenceSession l) {
        s_searchResults.add(l);
        providerChangeSupport.fireProviderCreate("conferenceSessions", l.getId(), l);
    } 
    
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l
                                                           
                                                           );
    }
  
    public void addToSchedule(int id){
       // First check to current Network status by calling the javascript function  
           AdfmfContainerUtilities.invokeContainerJavaScriptFunction(    
                "com.tamcapp.mobilebook.Sessions"
              , "application.checkConnection"
              , new Object[] { });
         String netWorkStatus = getNetWorkStatus();

/*
        ScheduledSessions s = getCurrentSession();    
        // now depending on the networkstatus call out to the webservice
         if (netWorkStatus.equalsIgnoreCase("true")){
            // call Webservice
            GenericType result =
              (GenericType)AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsDC",
                     null, "saveMyScheduledSession", pnames, params, ptypes);
            // and after that
            // also save locally with synchronized indicator = "Y"
           s.setSyncrhonized("Y");
           AddSessionToDB(s);
        }
         else{    
            //  save locally with synchronized indicator = "N".
            s.setSyncrhonized("N");
            AddSessionToDB(s);
          }
*/    }  

    public void setStatusAndSave(String networkStatus) {
       ValueExpression ve =
         AdfmfJavaUtilities.getValueExpression("#{applicationScope.networkStatus}", 
                                                  String.class);
         ve.setValue(AdfmfJavaUtilities.getAdfELContext(), networkStatus);
  
         Trace.log(Utility.ApplicationLogger, Level.SEVERE, ConferenceSessions.class, "setStatusAndSave",
                   "######### running ######" + networkStatus + "####" + ve.getValue(AdfmfJavaUtilities.getAdfELContext()));
     }

  
    public String getNetWorkStatus() {
        ValueExpression networkStatusVal =
            AdfmfJavaUtilities.getValueExpression("#{applicationScope.networkStatus}", String.class);
        return (String)networkStatusVal.getValue(AdfmfJavaUtilities.getAdfELContext());
    }
  
    public boolean needSearch(String currentSearchString){
        if (s_searchResults.isEmpty()){ 
           return true;
        }
        else { 
          if (!currentSearchString.equalsIgnoreCase(previousSearchString)){
               return true;               
          }
        }
        return false;
    }
  
    public void searchConferenceSessions(String searchString) {
    
        if (needSearch(searchString)){
            setPreviousSearchString(searchString);

    //    if (searchString.length() > 2){  
        
        // clear searchresults before starting a new search
        clearSearchResults();
              
        List pnames = new ArrayList();
        List params = new ArrayList();
        List ptypes = new ArrayList();


        pnames.add("findCriteria");
        ptypes.add(String.class);
        params.add(null);

        pnames.add("b_searchString");
        ptypes.add(String.class);
        params.add(searchString);

        pnames.add("findControl");
        ptypes.add(String.class);
        params.add(null);


        try {
            // This calls the DC method and gives us the Return
            GenericType result =
                (GenericType)AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsSesDc", null, "searchSessions",
                                                                        pnames, params, ptypes);

            // The Return wraps the searchPatients Result, so get that out of the Result

            int x = result.getAttributeCount();

            for (int i = 0; i < x; i++) {


                GenericType infoResult = (GenericType)result.getAttribute(i);

                ConferenceSession cs = new ConferenceSession();
                    cs = (ConferenceSession)GenericTypeBeanSerializationHelper.fromGenericType(ConferenceSession.class, infoResult);
                    cs.setSlot(new Slot (cs.getSessiondate(),cs.getStarttime(), cs.getEndtime()));
                addSearchResult(cs);
            }

            providerChangeSupport.fireProviderRefresh("conferenceSessions");


            
  
  
            Trace.log(Utility.ApplicationLogger, Level.INFO, ConferenceSessions.class, "searchConferenceSessions",
                      ">>>>>> End invokeDataControlMethod");
        } catch (AdfInvocationException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, ConferenceSessions.class, "searchConferenceSessions",
                      ">>>>>>" + e.getMessage());

            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later", AdfException.WARNING);
            throw ex;
        } catch (Exception e2) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, ConferenceSessions.class, "searchSessions",
                      ">>>>>> Exception=" + e2.getMessage());
            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later", AdfException.WARNING);
            throw ex;
        }
        }
    //    }
    //else
    //        {
    //Launch the PhoneGap alert dialog as there is no option to do the same with an ADF Mobile amx popup
    //            AdfmfContainerUtilities.invokeContainerJavaScriptFunction("ca.bcpra.mobile.frontend.Patient",
    //                                                              "navigator.notification.alert",
    //                                                              new Object[] {"Please enter at least 3 characters","",
    //                                                                            "Patient Search Criteria", "Ok"});
    //        }


    }






    public void searchConferenceSessionsById(String searchString) {
    
        // clear searchresults before starting a new search
        clearSearchResults();
              
        List pnames = new ArrayList();
        List params = new ArrayList();
        List ptypes = new ArrayList();


        pnames.add("findCriteria");
        ptypes.add(String.class);
        params.add(null);

        pnames.add("b_id");
        ptypes.add(String.class);
        params.add(searchString);

        pnames.add("findControl");
        ptypes.add(String.class);
        params.add(null);


        try {
            // This calls the DC method and gives us the Return
            GenericType result =
                (GenericType)AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsSesDc", null, "findSessionsById",
                                                                        pnames, params, ptypes);

            // The Return wraps the searchPatients Result, so get that out of the Result

            int x = result.getAttributeCount();

            for (int i = 0; i < x; i++) {


                GenericType infoResult = (GenericType)result.getAttribute(i);

                ConferenceSession cs = new ConferenceSession();
                    cs = (ConferenceSession)GenericTypeBeanSerializationHelper.fromGenericType(ConferenceSession.class, infoResult);

                addSearchResult(cs);
                System.out.println("found " + cs.getId());
            }

            providerChangeSupport.fireProviderRefresh("conferenceSessions");


            
    
    
            Trace.log(Utility.ApplicationLogger, Level.INFO, ConferenceSessions.class, "searchConferenceSessions",
                      ">>>>>> End invokeDataControlMethod");
        } catch (AdfInvocationException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, ConferenceSessions.class, "searchConferenceSessions",
                      ">>>>>>" + e.getMessage());

            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later" + e.getMessage(), AdfException.WARNING);
            throw ex;
        } catch (Exception e2) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, ConferenceSessions.class, "searchSessions",
                      ">>>>>> Exception=" + e2.getMessage());
            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later"+ e2.getMessage(), AdfException.WARNING);
            throw ex;
        }
        }
    
    

    public void setPreviousSearchString(String previousSearchString) {
        this.previousSearchString = previousSearchString;
    }

    public String getPreviousSearchString() {
        return previousSearchString;
    }
}
