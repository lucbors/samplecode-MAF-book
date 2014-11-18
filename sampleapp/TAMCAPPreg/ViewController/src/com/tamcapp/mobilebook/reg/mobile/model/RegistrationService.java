package com.tamcapp.mobilebook.reg.mobile.model;


import com.sun.util.logging.Level;

import com.tamcapp.mobilebook.reg.mobile.pojo.RegisteredUser;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.api.GenericTypeBeanSerializationHelper;
import oracle.adfmf.framework.exception.AdfException;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.util.GenericType;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class RegistrationService {

    public RegistrationService() {
        super();
    }


    public void register(String appId, String devToken, String devType, String email, String userName) {


        Trace.log(Utility.ApplicationLogger, Level.SEVERE, RegistrationService.class, "register",
                  appId+ "  " + devToken + " "+ devType + " " + email + " " + userName);

        RegisteredUser regUser = new RegisteredUser();

        regUser.setApplicationId(appId);
        regUser.setDeviceToken(devToken);
        regUser.setDeviceType(devType);
        regUser.setEmail(email);
        regUser.setUserName(userName);

        List pnames = new ArrayList();
        List params = new ArrayList();
        List ptypes = new ArrayList();

        GenericType gtRegUser =
            GenericTypeBeanSerializationHelper.toGenericType("TamcappWsRegDc.Types.createTamcappUsers.tamcappUsers",
                                                             regUser);

       
        Trace.log(Utility.ApplicationLogger, Level.SEVERE, RegistrationService.class, "register", gtRegUser.toString());
                  
        pnames.add("tamcappUsers");
        params.add(gtRegUser);
        ptypes.add(Object.class);


        try {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, RegistrationService.class, "register",
                      ">>>>>> Before invokeDataControlMethod");

            // This calls the DC method and gives us the Return
            GenericType result =
                (GenericType)AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsRegDc", null, "createTamcappUsers",
                                                                        pnames, params, ptypes);


        } catch (AdfInvocationException ex) {
            // If the web service is not available throw a nice exception
            if (AdfInvocationException.CATEGORY_WEBSERVICE.compareTo(ex.getErrorCategory()) == 0) {
                throw new RuntimeException("Error with the server. Please try later.");
            }
        }
    }
    
    public String getTamcappUserByDevice(String device){
        
        String userName="NotRegistered";
        
        List pnames = new ArrayList();
        List params = new ArrayList();
        List ptypes = new ArrayList();


        pnames.add("findCriteria");
        ptypes.add(String.class);
        params.add(null);

        pnames.add("b_device");
        ptypes.add(String.class);
        params.add(device);

        pnames.add("findControl");
        ptypes.add(String.class);
        params.add(null);


        try {
            // This calls the DC method and gives us the Return
            GenericType result =
                (GenericType)AdfmfJavaUtilities.invokeDataControlMethod("TamcappWsRegDc", null, "getTamcappUsersByDevice",
                                                                        pnames, params, ptypes);

            // The Return wraps the searchPatients Result, so get that out of the Result

            int x = result.getAttributeCount();

            for (int i = 0; i < x; i++) {


                GenericType deviceResult = (GenericType)result.getAttribute(i);

                RegisteredUser ru = new RegisteredUser();
                    ru = (RegisteredUser)GenericTypeBeanSerializationHelper.fromGenericType(RegisteredUser.class, deviceResult);

              userName=ru.getUserName();
            }

        
        
            Trace.log(Utility.ApplicationLogger, Level.INFO, RegistrationService.class, "getTamcappUserByDevice",
                      ">>>>>> End invokeDataControlMethod");
        } catch (AdfInvocationException e) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, RegistrationService.class, "getTamcappUserByDevice",
                      ">>>>>>" + e.getMessage());

            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later" + e.getMessage(), AdfException.WARNING);
            throw ex;
        } catch (Exception e2) {
            Trace.log(Utility.ApplicationLogger, Level.SEVERE, RegistrationService.class, "getTamcappUserByDevice",
                      ">>>>>> Exception=" + e2.getMessage());
            AdfException ex = new AdfException("Error Invoking Web Service.  Please try later"+ e2.getMessage(), AdfException.WARNING);
            throw ex;
        }

        
        return userName;
    }
}
