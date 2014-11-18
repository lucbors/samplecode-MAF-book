package com.tamcapp.mobilebook.mobile.model;


import com.sun.util.logging.Level;

import com.tamcapp.mobilebook.mobile.model.places.PlacesResult;
import com.tamcapp.mobilebook.mobile.model.places.PlacesResultList;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.dc.ws.rest.RestServiceAdapter;
import oracle.adfmf.framework.api.JSONBeanSerializationHelper;
import oracle.adfmf.framework.api.Model;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.java.beans.ProviderChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class GooglePlacesClient {
    private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);


    private String search = "";
    private String result = "empty";
    private PlacesResultList allPlacesResults = new PlacesResultList();

    private List s_foodPlaces = null;
    private List s_leisurePlaces = null;
    
    
    private List s_placesResults = null;
    
    public GooglePlacesClient() {
        super();
        
        if (s_placesResults == null) {
            s_placesResults = new ArrayList();
        }
        
        if (s_foodPlaces == null) {
            s_foodPlaces = new ArrayList();
        }
        
        if (s_leisurePlaces == null) {
            s_leisurePlaces = new ArrayList();
        }
        
    }
   
  
  
  
    public void setSearch(String search) {
        this.search = search;
    }

    public String getSearch() {
        return search;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void searchAction(String mapType) {

        Trace.log(Utility.ApplicationLogger, Level.INFO, GooglePlacesClient.class, "GooglePlacesClient", "start");

        if (mapType != null && mapType != "") {

            if (allPlacesResults != null) {
                allPlacesResults.purgeResults();
                clearPlacesResults();
                providerChangeSupport.fireProviderRefresh("allPlacesResults");
                
            }

            this.result = "called";
            RestServiceAdapter restServiceAdapter = Model.createRestServiceAdapter();

            // Clear any previously set request properties, if any
            restServiceAdapter.clearRequestProperties();

            // Set the connection name
            restServiceAdapter.setConnectionName("GooglePlacesUrlConn");

            // Specify the type of request
            restServiceAdapter.setRequestType(RestServiceAdapter.REQUEST_TYPE_GET);
            restServiceAdapter.addRequestProperty("Content-Type", "application/json");
            restServiceAdapter.addRequestProperty("Accept", "application/json; charset=UTF-8");
            // Specify the number of retries
            restServiceAdapter.setRetryLimit(0);

                  if (mapType==null||mapType==""){
                      mapType="food";
                  }
            // Set the URI which is defined after the endpoint in the connections.xml.
            // The request is the endpoint + the URI with query parameters
            //    restServiceAdapter.setRequestURI("?location=52.35985,4.88510&radius=1000&types="+mapType+"&sensor=false&key=AIzaSyBNlFUYnXXVt4Q3o-_KBMzhEB68_WT3LSs");

            restServiceAdapter.setRequestURI("?location=52.35985,4.88510&radius=1000&types="+mapType+"&sensor=false&key=AIzaSyBNlFUYnXXVt4Q3o-_KBMzhEB68_WT3LSs");


            // restServiceAdapter.getConnectionEndPoint("GooglePlacesUrlConn");

            String response = "not found";

            JSONBeanSerializationHelper jsonHelper = new JSONBeanSerializationHelper();

            try {
                // For GET request, there is no payload
                response = restServiceAdapter.send("");

                ServiceResult responseObject = (ServiceResult)jsonHelper.fromJSON(ServiceResult.class, response);
                if ("OK".equalsIgnoreCase(responseObject.getStatus())) {

             //       s_placesResults = (List)PlacesHelper.transformObject(responseObject).getResults();
             allPlacesResults = PlacesHelper.transformObject(responseObject).getResults();
                     
                }
                
             //   for (int i=0;i< s_placesResults.size();i++){
             //       
             //       addPlacesResult((PlacesResult)s_placesResults.get(i));
             //   }
                this.result = responseObject.getStatus();

                providerChangeSupport.fireProviderRefresh("allPlacesResults");
              
              
              //  /maps/api/place/nearbysearch/json/?location=52.35985,4.88510&amp;radius=1000&amp;types=food&amp;sensor=false&amp;key=AIzaSyBNlFUYnXXVt4Q3o-_KBMzhEB68_WT3LSs
              //  /maps/api/place/nearbysearch/json?location=52.35985,4.88510&radius=1000&types=food&sensor=false&key=AIzaSyBNlFUYnXXVt4Q3o-_KBMzhEB68_WT3LSs                                                                                                                             
              
                addToCache(allPlacesResults, mapType);

            } catch (Exception e) {
                e.printStackTrace();
                this.result = "error";
            }
        }
    }

    public void addToCache(PlacesResultList l, String type) {
        if (type.equalsIgnoreCase("food")) {

            for (int i = 0; i < l.gePlacesResultCount(); i++) {

               // foodPlaces.gePlacesResultCount();

                //         foodPlaces.add(l);
                //     }else  {
                //         leisurePlaces.add(l);
                //     }
            }
        }
    }
    //
    //    public void searchPoi(String patientId, String labTest) {
    //        String timeFrame = (String)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.contextBean.timeFrame}");
    //        String secondLabCode = (String)AdfmfJavaUtilities.evaluateELExpression("#{pageFlowScope.contextBean.secondLabTestCode}");
    //
    //        // check if we are still with same patient.
    //        // if not, clear cache
    //        if (!s_testResults.isEmpty()){
    //           LabResult cur =  (LabResult)s_testResults.get(0);
    //           Integer currentPat = cur.getPatientId();
    //           if (currentPat.compareTo(Integer.valueOf(patientId))!=0){
    //            // clear cache
    //            clearCache();
    //          }
    //        }
    //        // clear buffer with results
    //        clearLabResults();
    //        // call out Web if cache is empty
    //        if (cacheEmpty(labTest)){
    //            callWeb(patientId, labTest);
    //        }
    //        if (cacheEmpty(secondLabCode)){
    //            callWeb(patientId, secondLabCode);
    //        }
    //        // populate buffer for the 1 graph
    //        populateResults(labTest, timeFrame);
    //    }
    //


  

    public void setFoodPlaces(List s_foodPlaces) {
        List oldFoodPlaces = this.s_foodPlaces;
        this.s_foodPlaces = s_foodPlaces;
        propertyChangeSupport.firePropertyChange("foodPlaces", oldFoodPlaces, s_foodPlaces);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public List getFoodPlaces() {
        return s_foodPlaces;
    }

    public void setLeisurePlaces(List s_leisurePlaces) {
        List oldLeisurePlaces = this.s_leisurePlaces;
        this.s_leisurePlaces = s_leisurePlaces;
        propertyChangeSupport.firePropertyChange("leisurePlaces", oldLeisurePlaces, s_leisurePlaces);
    }

    public List getLeisurePlaces() {
        return s_leisurePlaces;
    }

    public void setPlacesResults(List s_placesResults) {
        List oldPlacesResults = this.s_placesResults;
        this.s_placesResults = s_placesResults;
        propertyChangeSupport.firePropertyChange("allPlacesResults", oldPlacesResults, s_placesResults);
    }

    public List getPlacesResults() {
        return s_placesResults;
    }
    
    
    public void addPlacesResult(PlacesResult l) {
            s_placesResults.add(l);
            providerChangeSupport.fireProviderCreate("allPlacesResults", l.getName(), l);
    }
    public void clearPlacesResults() {
        s_placesResults.clear();
        providerChangeSupport.fireProviderRefresh("allPlacesResults");
        
    }    

    
    
    public PlacesResult[] getAllPlacesResults() {
        //PlacesResult l[] = null;
        
        //l = (PlacesResult[])s_placesResults.toArray(new PlacesResult[s_placesResults.size()]);
        
       // return l;
        
        return allPlacesResults.getPlacesResults();
    }


    public void setProviderChangeSupport(ProviderChangeSupport providerChangeSupport) {
        ProviderChangeSupport oldProviderChangeSupport = this.providerChangeSupport;
        this.providerChangeSupport = providerChangeSupport;
        propertyChangeSupport.firePropertyChange("providerChangeSupport", oldProviderChangeSupport, providerChangeSupport);
    }

    public ProviderChangeSupport getProviderChangeSupport() {
        return providerChangeSupport;
    }
}
