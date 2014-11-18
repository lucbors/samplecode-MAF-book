package com.tamcapp.mobilebook.mobile.model;


import com.tamcapp.mobilebook.mobile.pojos.PointOfInterest;

import java.util.ArrayList;
import java.util.List;

public class PointsOfInterest {
    private List s_pois = null;
    public PointsOfInterest() {
        super();
    }
    
    public PointOfInterest[] getPois() {
          //This Method gets a list of the employees and their emails
          PointOfInterest[] pois = null;
          s_pois = new ArrayList();
          s_pois.add(new PointOfInterest(0, "Paleis op de Dam"   , 52.37323, 4.89166, "" )) ;
            s_pois.add(new PointOfInterest(0, "Old church"   , 52.37435, 4.89807, "" )) ;
            s_pois.add(new PointOfInterest(0, "Central Station"   , 52.37724, 4.90070, "" )) ;
            s_pois.add(new PointOfInterest(0, "Artis Zoo"   , 52.36718, 4.91274, "" )) ;
            s_pois.add(new PointOfInterest(0, "Vondelpark"   , 52.35836, 4.86892, "" )) ;
        
            s_pois.add(new PointOfInterest(0, "Rijksmuseum"   , 52.35985, 4.88510, "" )) ;
            s_pois.add(new PointOfInterest(0, "Van Gogh Museum"   , 52.35842, 4.88130, "" )) ;
            
            
           pois = (PointOfInterest[]) s_pois.toArray(new PointOfInterest[s_pois.size()]);
          return pois;
        } 
}

