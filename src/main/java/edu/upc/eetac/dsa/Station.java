package edu.upc.eetac.dsa;

import java.util.LinkedList;

public class Station {
    private String idStation;
    private String description;
    private int max;
    private double lat;
    private double lon;
    private int Bikes;
    private String user;

    public Station(String idStation, String description, int max, double lat, double lon) {
        this.idStation = idStation;
        this.description = description;
        this.max=max;
        this.lat=lat;
        this.lon=lon;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBikes() {
        return Bikes;
    }

    public void setBikes(int bikes) {
        Bikes = bikes;
    }


    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
