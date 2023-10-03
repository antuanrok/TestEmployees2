package com.example.testemployees2.converters;

import androidx.room.TypeConverter;

import com.example.testemployees2.pojo.BirthPlace;
import com.example.testemployees2.pojo.Person;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Converter {

 /* @TypeConverter
    public String listBirthPlaceToString(List<BirthPlace> places){
        return new Gson().toJson(places);
    };

    @TypeConverter
    public List<BirthPlace> stringTolistBirthPlace(String birthPlaceAsString){
        Gson gson = new Gson();
        ArrayList objects = gson.fromJson(birthPlaceAsString, ArrayList.class);
        ArrayList<BirthPlace> places = new ArrayList<>();
        for (Object o:
             objects) {
           // places.add(gson.fromJson(o.toString(), BirthPlace.class));
            BirthPlace birthPlace = new BirthPlace();
            birthPlace.setValue(gson.fromJson(o.toString(), BirthPlace.class).getValue());
            places.add(birthPlace);
           // places.add(gson.fromJson(o.toString(), BirthPlace.class));
        }
        return places;
    };*/


   @TypeConverter
        public List<BirthPlace> stringTolistBirthPlace(String birthPlaceAsString) {
        List<BirthPlace> birthPlaces = new ArrayList<>();
        String temp_s= birthPlaceAsString;
        String [] strings = temp_s.split(",");
        for (String str:
             strings) {
            BirthPlace birthPlace = new BirthPlace();
            birthPlace.setValue(str);
            birthPlaces.add(birthPlace);
        }
        return birthPlaces;
    }


  @TypeConverter
    public String listBirthPlaceToString(List<BirthPlace> birthPlace) {
        String t_birthPlace = null;
        if (birthPlace!=null&& birthPlace.size()!=0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (BirthPlace place :
                    birthPlace) {
                stringBuilder.append(place.getValue());
                stringBuilder.append(",");
            }
            if (stringBuilder.length() != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                t_birthPlace = stringBuilder.toString();
        }
        }
        return t_birthPlace;
    }

}
