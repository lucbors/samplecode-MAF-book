package com.tamcapp.mobilebook.ses.mobile.listeners;


import com.tamcapp.mobilebook.ses.mobile.backgroundthreads.CheckNetworkStatusWorker;

import oracle.adfmf.feature.LifeCycleListener;


public class SessionFeatureLifeCycleListener implements LifeCycleListener {
    
    private CheckNetworkStatusWorker cnsw = new CheckNetworkStatusWorker();
    private Thread cnswt = new Thread(cnsw);
    
    
    public SessionFeatureLifeCycleListener() {
        super();
    }

    public void activate() {

   /* alleen als voorbeeld voor ch16 
        SecurityContext sc = (SecurityContext)AdfmfJavaUtilities.evaluateELExpression("#{securityContext}"); 

        String inRole = sc.isUserInRole("Speaker");
        String userName = sc.getUserName();
        String hasPrivilege = sc.hasPrivilege("somePrivilege");
        String isUserAuthenticated = sc.isAuthenticated();        
*/
       // System.out.println( "###############" + "in de activate");
        
        /* effe uitgezet 
        cnswt.start();
        
*/  
     //   Boolean notified = (Boolean)AdfmfJavaUtilities.evaluateELExpression(
     //   "#{applicationScope.notified}");


        //System.out.println( "###############" + notified.toString());
        
        
     //   Trace.log(Utility.ApplicationLogger, Level.SEVERE, SessionFeatureLifeCycleListener.class, "searchConferenceSessions",
      //            "###############" + notified.toString());
        
    //    if(notified.booleanValue()){
    //    AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
    //                                                              "adf.mf.api.amx.doNavigation",
    //                                                              new Object[] { "featureActivated" });
        
      //  } 
        
      
      


        
    }


   
    
    
    public void deactivate() {
        /*
       cnswt.interrupt();
*/
    }
}
