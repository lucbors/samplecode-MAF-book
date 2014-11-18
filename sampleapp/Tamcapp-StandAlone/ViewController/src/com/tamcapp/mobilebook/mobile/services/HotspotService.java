package com.tamcapp.mobilebook.mobile.services;


import com.tamcapp.mobilebook.mobile.pojos.hotspots.Hotspot;

import java.util.ArrayList;

public class HotspotService {
    

    private static Hotspot[] _floorPlanHotspots = null;
    private static HotspotService _service;
  
    public HotspotService() {
        super();
        initHotspots();
    }
    
    
    public static void initHotspots() {
        if ( _floorPlanHotspots == null ) {
            // santa detail 
            ArrayList spots = new ArrayList();

             spots.add((new Hotspot("roomOne",    "Mobile Sessions",   1, "In this room there are about 10 sessions on ADF Mobile"))); 
             spots.add((new Hotspot("roomTwo",    "Database Sessions", 2, "Database Sessions can be very interesting"))); 
             spots.add((new Hotspot("roomThree",  "APEX Sessions",     3, "APEX is doing a good job. Just is not my thing"))); 
             spots.add((new Hotspot("roomFour",   "PL SQL Sessions",   4, "PL SQL is way back for me"))); 
             spots.add((new Hotspot("roomFive",   "Other Sessions",    5, "Need I say more"))); 

            _floorPlanHotspots = (Hotspot[])spots.toArray(new Hotspot[spots.size()]);       
        }
    }
    
    public static HotspotService getInstance() {
        if (_service == null)
        {
            _service = new HotspotService();
        }
        return _service;
    }

    public static void setHotspots(Hotspot[] _floorPlanHotspots) {
        HotspotService._floorPlanHotspots = _floorPlanHotspots;
    }

    public static Hotspot[] getHotspots() {
        return _floorPlanHotspots;
    }
}