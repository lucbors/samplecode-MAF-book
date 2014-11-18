package com.tamcapp.mobilebook.mobile.model.dc;


import com.sun.util.logging.Level;

import com.tamcapp.mobilebook.mobile.pojos.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class SessionsSearcherDC {

    
    private static List s_searchResults=null;
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
  
    public SessionsSearcherDC() {
        super();
        if (s_searchResults == null) {
            s_searchResults = new ArrayList();
        }
   
    }
    
    public Session[] getSearchResults() {
         Session l[] = null;
      //  sortLabResults();
        l = ( Session[])s_searchResults.toArray(new  Session[s_searchResults.size()]);
    
        return l;
    }
    
    public void sortSearchResults(){
        
        Collections.sort(s_searchResults);
    }
    
    public void clearSearchResults() {
        s_searchResults.clear();
        providerChangeSupport.fireProviderRefresh("searchResults");
    }
    
    public void addSearchResult(Session l) {
        s_searchResults.add(l);
        providerChangeSupport.fireProviderCreate("searchResults", l.getId(), l);
    } 
  
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
    public void searchSessions(String searchString) {
  

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
            Trace.log(Utility.ApplicationLogger, Level.INFO, SessionsSearcherDC.class, "searchSessions",
                      ">>>>>> Before invokeDataControlMethod");

            // This calls the DC method and gives us the Return
            GenericType result =
                (GenericType)AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsDC", null, "searchSessions",
                                                                        pnames, params, ptypes);

            // The Return wraps the searchPatients Result, so get that out of the Result

            int x = result.getAttributeCount();

            for (int i = 0; i < x; i++) {


                GenericType infoResult = (GenericType)result.getAttribute(i);

                Session ps = new Session();
                    ps = (Session)GenericTypeBeanSerializationHelper.fromGenericType(Session.class, infoResult);

                addSearchResult(ps);
            }
            providerChangeSupport.fireProviderRefresh("searchResults");

            Trace.log(Utility.ApplicationLogger, Level.INFO, SessionsSearcherDC.class, "searchSessions",
                      ">>>>>> End invokeDataControlMethod");
        } catch (AdfInvocationException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, SessionsSearcherDC.class, "searchSessions",
                      ">>>>>>" + e.getMessage());

            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later", AdfException.WARNING);
            throw ex;
        } catch (Exception e2) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, SessionsSearcherDC.class, "searchSessions",
                      ">>>>>> Exception=" + e2.getMessage());
            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later", AdfException.WARNING);
            throw ex;
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
}
