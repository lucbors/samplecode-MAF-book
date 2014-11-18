package com.tamcapp.mobilebook.mobile.pojos.hotspots;

  public class Hotspot {
    private String area;
    private String info;
    private int areanumber;
    private String description;
    
    
    public Hotspot() {
        super();
    }
    
    
    public Hotspot(String area, String info, int areanumber, String description) {
        this.area = area;
        this.info = info;
        this.areanumber = areanumber;
        this.description = description;
       
    }    

    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setAreanumber(int areanumber) {
        this.areanumber = areanumber;
    }

    public int getAreanumber() {
        return areanumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
