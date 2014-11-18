package com.tamcapp.mobilebook.mobile.model;

import oracle.adfmf.json.JSONArray;

public class ServiceResult {

    private String status;
    private String debug_info;
    private String html_attributions;
    private String next_page_token;
    private JSONArray results;
    
    public ServiceResult() {
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setResults(JSONArray results) {
        this.results = results;
    }

    public JSONArray getResults() {
        return results;
    }
}
