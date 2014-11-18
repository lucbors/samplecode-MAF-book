package com.blogspot.lucbors.book.ch7.mobile.countryDC;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.Model;

public class CountriesJson {
    public CountriesJson() {
        super();
    }
    public static String invokeRestService(){
       RestServiceAdapter restService = Model.createRestServiceAdapter();
       restService.clearRequestProperties();
       restService.setConnectionName("CountryRestConnection");
       restService.setRequestType(RestServiceAdapter.REQUEST_TYPE_GET);
       restService.setRetryLimit(0);

       String requestUri = "countryInfoJSON?formatted=true&country=US&username=demo&style=full";

       restService.setRequestURI(requestUri);
       String response = "";
       try{
          response = restService.send("");
          return response;
    
       }
       catch (Exception e){
          // something went wrong
           e.printStackTrace();
       }
       
       return "";
    }

}
