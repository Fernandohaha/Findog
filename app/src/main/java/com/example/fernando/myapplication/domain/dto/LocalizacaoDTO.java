package com.example.fernando.myapplication.domain.dto;

/**
 * Created by Usuário on 11/13/2017.
 */

public class LocalizacaoDTO {

    int Id;
    double Longetitude;
    double Latitude;


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getLongetitude() {
        return Longetitude;
    }

    public void setLongetitude(double longetitude) {
        Longetitude = longetitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }
}
