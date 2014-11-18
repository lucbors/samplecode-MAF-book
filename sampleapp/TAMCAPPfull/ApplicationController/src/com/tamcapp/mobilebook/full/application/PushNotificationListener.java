package com.tamcapp.mobilebook.full.application;


import com.sun.util.logging.Level;

import javax.el.ValueExpression;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.event.Event;
import oracle.adfmf.framework.event.EventListener;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class PushNotificationListener implements EventListener {

    public PushNotificationListener() {

        super();

    }


    public void onMessage(Event event) {

        AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
        JSONBeanSerializationHelper jsonHelper = new JSONBeanSerializationHelper();
        try {
            PayloadServiceResponse serviceResponse =
                (PayloadServiceResponse)jsonHelper.fromJSON(PayloadServiceResponse.class, event.getPayload());


            String message = serviceResponse.getCustomMessage();

            System.out.println("#####  ####" + message + "#######  #####");

            System.out.println("#####  ####" + serviceResponse.toString() + "#######  #####");

            System.out.println("#####  ####" + event.getPayload() + "#######  #####");


            System.out.println("#####  ####" + serviceResponse.getSessionId() + "#######  #####");

            System.out.println("#####  ####" + serviceResponse.getFeatureName() + "#######  #####");


            ValueExpression notificationPayloadBinding =
                AdfmfJavaUtilities.getValueExpression("#{applicationScope.notificationSessionId}", String.class);

            ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.notified}", Boolean.class);

            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), Boolean.TRUE);
            notificationPayloadBinding.setValue(AdfmfJavaUtilities.getAdfELContext(), serviceResponse.getSessionId());

            // also, lets decrease the application icon badge by one
            int currentBadge = AdfmfContainerUtilities.getApplicationIconBadgeNumber();
            if (currentBadge > 0) {
                AdfmfContainerUtilities.setApplicationIconBadgeNumber(currentBadge - 1);
            }

            //AdfmfContainerUtilities.resetFeature("com.tamcapp.mobilebook.ses.ConferenceSessions");
            AdfmfContainerUtilities.gotoFeature("com.tamcapp.mobilebook.ses.ConferenceSessions");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void onError(AdfException adfException) {

        //Invoked when any error is encountered in the Push Notification Lifecycle (not working at the time of blog publication

        System.out.println(" x*************************** " + adfException.getMessage() + " ******************** ");


    }


    public void onOpen(String token) {

        Trace.log(Utility.ApplicationLogger, Level.INFO, PushNotificationListener.class, "onOpen",
                  "*************************************** " + token + " ***********************");
        


        ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{applicationScope.deviceToken}", String.class);
        if (token != null) {
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), token);
        } else {
            ve.setValue(AdfmfJavaUtilities.getAdfELContext(), "dummy Token");

        }

    }

}


