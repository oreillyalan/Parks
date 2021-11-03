package com.example.parks.data;

import com.example.parks.models.Attraction;

import java.util.List;

public interface AsyncResponse {
    void processAttractions(List<Attraction> attractions);
}
