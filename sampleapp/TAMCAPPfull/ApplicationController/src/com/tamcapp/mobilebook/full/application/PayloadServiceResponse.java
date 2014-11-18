package com.tamcapp.mobilebook.full.application;

public class PayloadServiceResponse {
    
    
    private double from;
    private String collapse_key;
    private String customMessage;
    private String sound;
    private String alert;
    private String FeatureName;
    private String SessionId;
    
    
    
    public PayloadServiceResponse() {
        super();
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getFrom() {
        return from;
    }

    public void setCollapse_key(String collapse_key) {
        this.collapse_key = collapse_key;
    }

    public String getCollapse_key() {
        return collapse_key;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getAlert() {
        return alert;
    }

    public void setFeatureName(String FeatureName) {
        this.FeatureName = FeatureName;
    }

    public String getFeatureName() {
        return FeatureName;
    }

    public void setSessionId(String SessionId) {
        this.SessionId = SessionId;
    }

    public String getSessionId() {
        return SessionId;
    }
}
