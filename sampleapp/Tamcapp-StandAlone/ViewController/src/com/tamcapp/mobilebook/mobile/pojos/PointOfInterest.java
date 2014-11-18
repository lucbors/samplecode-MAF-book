package com.tamcapp.mobilebook.mobile.pojos;

public class PointOfInterest {

    int id;
    String name;
    double lat;
    double lng;
    String link;

    public PointOfInterest() {
        super();
    }

    public PointOfInterest(int id, String name, double lat, double lng, String link) {
      super();
      this.id = id;
      this.name = name;
      this.lat = lat;
      this.lng = lng;
      this.link = link;

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
