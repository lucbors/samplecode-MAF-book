package com.tamcapp.mobilebook.mobile.model;


import com.tamcapp.mobilebook.mobile.model.places.LatLng;
import com.tamcapp.mobilebook.mobile.model.places.PlacesGeometry;
import com.tamcapp.mobilebook.mobile.model.places.PlacesResponse;
import com.tamcapp.mobilebook.mobile.model.places.PlacesResult;
import com.tamcapp.mobilebook.mobile.model.places.PlacesResultList;

import oracle.adfmf.json.JSONArray;
import oracle.adfmf.json.JSONException;
import oracle.adfmf.json.JSONObject;

public class PlacesHelper {
    public PlacesHelper() {
        super();
    }

    public static PlacesResponse transformObject(ServiceResult service) {
        PlacesResponse response = new PlacesResponse();
        response.setStatus(service.getStatus());

        PlacesResultList results = new PlacesResultList();
        response.setResults(results);

        JSONArray resultList = service.getResults();
        for (int i = 0; i < resultList.length(); i++) {
            try {
                PlacesResult placesResult = new PlacesResult();
                JSONObject result = resultList.getJSONObject(i);

                if (result.get("icon") != null) {
                    placesResult.setIcon((String)result.get("icon"));
                }


                if (result.get("name") != null) {
                    placesResult.setName((String)result.get("name"));

                }


                if (result.get("vicinity") != null) {
                    placesResult.setVicinity((String)result.get("vicinity"));
                }


                if (result.get("rating") != null) {
                    
                           // workaround for rating without decimal place (being Integer instead of Double)        
                    String stringDouble = result.get("rating")+"";
                    
                    if (stringDouble.indexOf(".")==-1){
                        stringDouble=stringDouble+".0";
                    }
                    Double ratingDouble=Double.valueOf(stringDouble);
                    placesResult.setRating(ratingDouble);
                                  
                   
                                
                        
                    
                }

                if (result.get("types") != null) {
                    JSONArray types = (JSONArray)result.get("types");
                    String placesType = "";
                    for (int p = 0; p < types.length(); p++) {
                        placesType += types.get(p) + ",";
                    }
                    placesResult.setTypes(placesType);
                }
                results.addPlacesResult(placesResult);
                if (result.get("geometry") != null) {
                    JSONObject geometry = (JSONObject)result.get("geometry");
                    PlacesGeometry geo = new PlacesGeometry();


                    if (geometry.get("location") != null) {
                        JSONObject location = (JSONObject)geometry.get("location");

                        LatLng latLng = new LatLng();
                        latLng.setLat(location.getDouble("lat"));
                        latLng.setLng(location.getDouble("lng"));
                        geo.setLocation(latLng);
                    }

                    placesResult.setGeometry(geo);
                }


    

            } catch (JSONException e) {
            }
        }
        return response;

    }
}
