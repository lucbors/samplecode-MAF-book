package com.tamcapp.mobilebook.ses.mobile.backgroundthreads;


import com.sun.util.logging.Level;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;

public class CheckNetworkStatusWorker implements Runnable {
    public CheckNetworkStatusWorker() {
        super();
    }

    public void run() {

        for (int i = 0; i <= i; ++i) {
            try {

             //   AdfmfContainerUtilities.invokeContainerJavaScriptFunction("com.tamcapp.mobilebook.Sessions",
             //                                                             "application.checkConnection",
             //                                                             new Object[] { });

                AdfmfJavaUtilities.flushDataChangeEvent();
        
               Trace.log(Utility.ApplicationLogger, Level.SEVERE, CheckNetworkStatusWorker.class, "run",
                         "######### running ######" + i);
                    } catch (Throwable t) {
                System.err.println("Error in the background thread: " + t);
            }

            try {
                Thread.sleep(60000); /* sleep for 60 seconds */
            } catch (InterruptedException ex) {
                break;
            }
        }
    }


}
