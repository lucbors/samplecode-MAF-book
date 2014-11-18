package com.tamcapp.mobilebook.mobile.model.places;

public class PlacesResult {
    
    private String vicinity;
    private Double rating;
    private String name;
    private String types;
    private String icon;
    private PlacesGeometry geometry;
    
    public PlacesResult() {
        super();
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getRating() {
        return rating;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTypes() {
        return types;
    }

    public void setGeometry(PlacesGeometry geometry) {
        this.geometry = geometry;
    }

    public PlacesGeometry getGeometry() {
        return geometry;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
