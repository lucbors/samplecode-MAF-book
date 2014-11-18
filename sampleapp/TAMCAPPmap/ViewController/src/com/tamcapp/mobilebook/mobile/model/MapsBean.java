package com.tamcapp.mobilebook.mobile.beans;


import com.tamcapp.mobilebook.mobile.model.GooglePlacesClient;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;

public class MapsBean {
    private String currentMap;
    private String previousMap;

        private static GooglePlacesClient poi;
        private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public MapsBean() {

        poi = new GooglePlacesClient();

    }


  
    /**
     * This method is called from the action attribute of the Patients listItem on the home screen
     * We use the action so that we can choose whether to navigate based on an error being returned.
     **/

    public void switchMapMode(ActionEvent actionEvent) {
        // Add event code here...

        // only call service if we are using a 'new' mode
        if (!currentMap.equalsIgnoreCase(previousMap)) {
            poi.searchAction(currentMap);
            setPreviousMap(currentMap);
        }

    }

    public void setCurrentMap(String currentMap) {
        String oldCurrentMap = this.currentMap;
        this.currentMap = currentMap;
        propertyChangeSupport.firePropertyChange("currentMap", oldCurrentMap, currentMap);
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

    public String getCurrentMap() {
        return currentMap;
    }

    public void setPreviousMap(String previousMap) {
        String oldPreviousMap = this.previousMap;
        this.previousMap = previousMap;
        propertyChangeSupport.firePropertyChange("previousMap", oldPreviousMap, previousMap);
    }

    public String getPreviousMap() {
        return previousMap;
    }
}

