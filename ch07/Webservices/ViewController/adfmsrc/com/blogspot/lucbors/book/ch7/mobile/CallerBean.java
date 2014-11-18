package com.blogspot.lucbors.book.ch7.mobile;

import com.blogspot.lucbors.book.ch7.mobile.countryDC.CountriesJson;

import oracle.adfmf.amx.event.ActionEvent;

public class CallerBean {
    
    private static CountriesJson cj;
    
    public CallerBean() {
      
        cj = new CountriesJson();
    }
    
    

    
    /**
     * This method is called from the action attribute of the Patients listItem on the home screen
     * We use the action so that we can choose whether to navigate based on an error being returned.
     **/

    public void callService(ActionEvent actionEvent) {
        // Add event code here...

            cj.invokeRestService();

    }
                      
}
