package com.tamcapp.mobilebook.mobile.beans;

import oracle.adfmf.amx.event.ActionEvent;

public class MapsBean {
    private String currentMap;
    private String previousMap;

//    public static GooglePlacesClient poi;
        
    public MapsBean() {
    
  //          poi = new GooglePlacesClient();
   
        }


    public void setCurrentMap(String currentMap) {
        this.currentMap = currentMap;
    }

    public String getCurrentMap() {
        return currentMap;
    }

    /**
     * This method is called from the action attribute of the Patients listItem on the home screen
     * We use the action so that we can choose whether to navigate based on an error being returned.
     **/

    public void switchMapMode(ActionEvent actionEvent) {
        // Add event code here...
        
          // only call service if we are using a 'new' mode
            if (!currentMap.equalsIgnoreCase(previousMap)){
               //  poi.searchAction(currentMap);
                 setPreviousMap(currentMap);
            }
            
        }

    public void setPreviousMap(String previousMap) {
        this.previousMap = previousMap;
    }

    public String getPreviousMap() {
        return previousMap;
    }
}

