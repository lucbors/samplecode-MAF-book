package com.tamcapp.mobilebook.att.mobile.model;


import com.sun.util.logging.Level;

import com.tamcapp.mobilebook.att.mobile.pojos.PersonDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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


public class AttendeesDetail {


    private static List s_searchResults=null;
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
  
    public AttendeesDetail() {
        super();
        if (s_searchResults == null) {
            s_searchResults = new ArrayList();
        }

    }

    
    
    public PersonDetail[] getAttendeesDetail() {
         PersonDetail l[] = null;
        l = ( PersonDetail[])s_searchResults.toArray(new  PersonDetail[s_searchResults.size()]);
    
        return l;
    }
    
    public void sortSearchResults(){
        
        Collections.sort(s_searchResults);
    }
    
    public void clearSearchResults() {
        s_searchResults.clear();
        providerChangeSupport.fireProviderRefresh("attendeesDetail");
    }
    
    public void addSearchResult(PersonDetail l) {
        s_searchResults.add(l);
        providerChangeSupport.fireProviderCreate("attendeesDetail", l.getId(), l);
    } 
  
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

    
    public void searchAttendeesDetail(Long currentAttendee) {
  

     // clear searchresults before starting a new search
        clearSearchResults();
              
        List pnames = new ArrayList();
        List params = new ArrayList();
        List ptypes = new ArrayList();


        pnames.add("id");
        ptypes.add(String.class);
        params.add(currentAttendee);

      

        try {
            Trace.log(Utility.ApplicationLogger, Level.INFO, Attendees.class, "searchAttendeesDetail",
                      ">>>>>> Before invokeDataControlMethod");

            // This calls the DC method and gives us the Return
            GenericType result =
                (GenericType)AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsAttDc", null, "getAttendees",
                                                                        pnames, params, ptypes);

            // The Return wraps the searchPatients Result, so get that out of the Result

            int x = result.getAttributeCount();

            for (int i = 0; i < x; i++) {


                GenericType infoResult = (GenericType)result.getAttribute(i);

                PersonDetail ps = new PersonDetail();
                    ps = (PersonDetail)GenericTypeBeanSerializationHelper.fromGenericType(PersonDetail.class, infoResult);

                addSearchResult(ps);
            }
            providerChangeSupport.fireProviderRefresh("attendeesDetail");

            Trace.log(Utility.ApplicationLogger, Level.INFO, Attendees.class, "searchAttendeesDetail",
                      ">>>>>> End invokeDataControlMethod");
        } catch (AdfInvocationException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, Attendees.class, "searchAttendeesDetail",
                      ">>>>>>" + e.getMessage());

            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later", AdfException.WARNING);
            throw ex;
        } catch (Exception e2) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, Attendees.class, "searchAttendees",
                      ">>>>>> Exception=" + e2.getMessage());
            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later", AdfException.WARNING);
            throw ex;
        }

    }
    
    
    
    public void uploadImage(Object imageSource) {
                            
 //      String attendeeId = (String)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.attendeesBean.currentAttendee}"); 
        
       String attendeeId = (String)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.attendeesBean.currentAttendee}"); 
 
       Integer id = Integer.valueOf(attendeeId);    
                
       ArrayList parameterNames = new ArrayList();
       ArrayList parameterValue = new ArrayList();
       ArrayList parameterTypes = new ArrayList();

        //access WS arguments
        
        
        
        parameterNames.add("arg0");//arg0 == file content
        parameterNames.add("arg1");//arg1 == file type e.g. jpg
        parameterNames.add("arg2");//arg2 == attendeeId.
                
        parameterValue.add(imageSource);
        parameterValue.add("png");
        parameterValue.add(id);
       
        parameterTypes.add(Object.class);
        parameterTypes.add(String.class);
        parameterTypes.add(Integer.class);
        
        try {
            AdfmfJavaUtilities.invokeDataControlMethod("ImageWsDc", null, "saveImage",parameterNames, parameterValue,parameterTypes);
        } catch (AdfInvocationException e) {
            //Launch the PhoneGap alert dialog as there is no option to do the same with an ADF Mobile amx popup
   
        }
        
        // Image upload; All is well; Now also update the photo in the current attendee in Memory
        PersonDetail prs = (PersonDetail)s_searchResults.get(0);
        prs.setPicture(imageSource.toString());

        //Launch the PhoneGap alert dialog as there is no option to do the same with an ADF Mobile amx popup
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction("com.tamcapp.mobilebook.att.Attendees",
                                                                  "navigator.notification.alert",
                                                                  new Object[] {"Image uploaded: \n ------------- \n\n"+
                                                                                "Image Uploaded to Server", "Ok"});
    }
    
    public void saveAttendee(){      
        // we know that there is only one attendee in because we invoking this from the details page
        PersonDetail attendee = (PersonDetail)s_searchResults.get(0);
        // Set attribute values 
        List namesList = new ArrayList(1);
        List paramsList = new ArrayList(1);
        List typesList = new ArrayList(1);

        GenericType gtAttendee = GenericTypeBeanSerializationHelper.toGenericType("TamcappWsAttDc.Types.updateAttendees.attendees", attendee);

        namesList.add("attendees");
        paramsList.add(gtAttendee);
        typesList.add(Object.class);

        try {
            AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsAttDc", null, "updateAttendees", namesList, paramsList, typesList);
        } catch (AdfInvocationException ex) {
           
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, Attendees.class, "updateAttendees",
                      ">>>>>>" + ex.getMessage());

            AdfException e = new AdfException("Error Invoking Web Service.  Please try later", AdfException.WARNING);
            throw e;    
        }
        
    }
    
}