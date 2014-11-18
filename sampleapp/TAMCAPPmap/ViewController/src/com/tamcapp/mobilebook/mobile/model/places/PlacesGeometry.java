package com.tamcapp.mobilebook.mobile.model.places;

public class PlacesGeometry {
    private LatLng location;
    
    public PlacesGeometry() {
        super();
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public LatLng getLocation() {
        return location;
    }
}
