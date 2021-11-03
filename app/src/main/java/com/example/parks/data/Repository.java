package com.example.parks.data;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.parks.MapsActivity;
import com.example.parks.controller.AppController;
import com.example.parks.models.Attraction;
import com.example.parks.utils.Utils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    static List<Attraction> attractionList = new ArrayList<>();

    public static void getAttractions(final AsyncResponse callback){
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, Utils.PARKS_URL, null, response -> {
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Attraction attraction = new Attraction();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            jsonObject.toString();
                            attraction.setFullName(jsonObject.getString("name"));
                            attraction.setPhoneNumber(jsonObject.getString("telephone"));
                            attraction.setUrl(jsonObject.getString("url"));
                            JSONObject getGeo = jsonObject.getJSONObject("geo");
                            attraction.setLatitude(getGeo.getDouble("latitude"));
                            attraction.setLongitude(getGeo.getDouble("longitude"));
                            JSONObject getAddress = jsonObject.getJSONObject("address");
                            attraction.setCounty(getAddress.getString("addressRegion"));
                            JSONObject getImageUrl = jsonObject.getJSONObject("image");
                            attraction.setImage(getImageUrl.getString("url"));



                            attractionList.add(attraction);

                        }
                        if (null != callback){callback.processAttractions(attractionList);}

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },error -> {
                    error.printStackTrace();
                });
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }
}
