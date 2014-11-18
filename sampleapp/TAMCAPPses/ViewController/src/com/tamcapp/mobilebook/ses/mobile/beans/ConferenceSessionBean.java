package com.tamcapp.mobilebook.ses.mobile.beans;


import com.tamcapp.mobilebook.ses.mobile.model.ConferenceSessions;

import javax.el.ValueExpression;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.amx.event.ValueChangeEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;


public class ConferenceSessionBean {
    public static ConferenceSessions cs;
    public String currentConferenceSession = "empty";
    public ConferenceSessionBean() {
         cs = new ConferenceSessions();  
    }

    public void editMySchedule(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void addToSchedule(ActionEvent actionEvent) {
        // Add event code here...
    
        
    }

    public void RateSession(ValueChangeEvent valueChangeEvent) {
        // Add event code here...     
        
      //  securityContext sc;
      //  String inRole = sc.isUserInRole("Speaker");
      //  String userName = sc.getUserName();
      //  String hasPrivilege = sc.hasPrivilege("somePrivilege");
      //  String isUserAuthenticated = sc.isAuthenticated();
        
    }
    
    public void handleNotification(String notificationSesId) {
        setCurrentConferenceSession(null);
       
        cs.searchConferenceSessionsById(notificationSesId);
        
    // added for pushnotification
        Boolean notified = (Boolean)AdfmfJavaUtilities.evaluateELExpression(
        "#{applicationScope.notified}");

        ValueExpression ve =  AdfmfJavaUtilities.getValueExpression("#{applicationScope.notified}", 
                                                 Boolean.class);

        if(notified.booleanValue()){
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), Boolean.FALSE);
      //      setCurrentConferenceSession(notificationSesId);     
        
              
        }

        setCurrentConferenceSession("-1");
//       ValueExpression expression = AdfmfJavaUtilities.getValueExpression("#{bindings.ConferenceSessionsIterator}", Object.class);
//
//        AmxIteratorBinding amxIterator = (AmxIteratorBinding) expression.getValue(AdfmfJavaUtilities.getAdfELContext());
//
//          BasicIterator basicIterator = amxIterator.getIterator();
//
//          System.out.println("§§§§§§§§§§§§§§§§§§§§§§§§§§"+ basicIterator.getCurrentRowKey().toString());


//         setCurrentConferenceSession((String)basicIterator.getCurrentRowKey());
//         
 // end added for pushnotification

        
    }
    

    public void setCurrentConferenceSession(String currentConferenceSession) {
        this.currentConferenceSession = currentConferenceSession;
    }

    public String getCurrentConferenceSession() {
        return currentConferenceSession;
    }
}
