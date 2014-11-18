package com.tamcapp.mobilebook.mobile.model.places;

import java.util.ArrayList;
import java.util.List;

import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;


public class PlacesResultList {

    private List placesResults;
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);

    public PlacesResultList() {
        if (placesResults == null) {
                   placesResults = new ArrayList();
               } 
    }

    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }
    

    public void addPlacesResult(PlacesResult result) {
        placesResults.add(result);
        providerChangeSupport.fireProviderCreate("placesResults", result.getName(), result);
    }

    public PlacesResult[] getPlacesResults() {
        PlacesResult plr[] = null;
        plr = (PlacesResult[])placesResults.toArray(new PlacesResult[placesResults.size()]);
        return plr;
    }

    public int gePlacesResultCount() {
        return placesResults.size();
    }
    
    public void purgeResults(){

    placesResults.clear();
        providerChangeSupport.fireProviderRefresh("placesResults");

    }
}
