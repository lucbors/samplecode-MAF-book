package com.tamcapp.mobilebook.att.utils;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class TamcappUtils {
    public TamcappUtils() {
        super();
    }

    public static void doNavigation(String navCase) {
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction(AdfmfJavaUtilities.getFeatureName(),
                                                                  "adf.mf.api.amx.doNavigation",
                                                                  new Object[] { navCase });
    }
}
