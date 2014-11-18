package com.tamcapp.mobilebook.mobile.model;


import com.tamcapp.mobilebook.mobile.pojos.hotspots.Hotspot;
import com.tamcapp.mobilebook.mobile.services.HotspotService;


public class FloorPlanHotspots {
    
    private HotspotService _service = null;
    private Hotspot[] _floorPlanHotspots;
  
    public FloorPlanHotspots() {
        super();
        _service = HotspotService.getInstance();
    }
    
    
    public Hotspot[] getHotspots() {
        if ( _floorPlanHotspots == null ) {
            _floorPlanHotspots = _service.getHotspots();
        }
        return _floorPlanHotspots;
    }

}