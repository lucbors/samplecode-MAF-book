package com.tamcapp.mobilebook.mobile.beans;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;

public class SpringboardBean {
    public SpringboardBean() {
        super();
    }
    public void logoff(ActionEvent actionEvent) {
        AdfmfJavaUtilities.logout();

}
}