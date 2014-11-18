package com.tamcapp.mobilebook.mobile.model.places;

public class PlacesResponse {
private String status;
private PlacesResultList results;

    public PlacesResponse() {

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setResults(PlacesResultList results) {
        this.results = results;
    }

    public PlacesResultList getResults() {
        return results;
    }
}
