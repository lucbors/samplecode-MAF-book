package com.tamcapp.mobilebook.reg.mobile.listener;


import com.tamcapp.mobilebook.reg.mobile.model.RegistrationService;

import oracle.adfmf.feature.LifeCycleListener;
import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class RegistrationFeatureLifeCycleListener implements LifeCycleListener {
    public RegistrationFeatureLifeCycleListener() {
        super();
    }

    public void activate() { 
        String device = (String)AdfmfJavaUtilities.evaluateELExpression("#{applicationScope.deviceToken}");
        RegistrationService srv = new RegistrationService();
        String userName = srv.getTamcappUserByDevice(device);

        if (userName.equalsIgnoreCase("NotRegistered")){
           // do nothing, just continue activating the feature.    
        }
        else {
     //     deactivate by going to springboard
        AdfmfContainerUtilities.gotoSpringboard();
        }
    }

    public void deactivate() {
    }
}
