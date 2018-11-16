package edu.upc.eetac.dsa;

import java.util.List;


public interface MyBike {

    /**
     * number of stations
     */
    int S = 10;

    /**
     * Add a new User
     */
    void addUser(String idUser, String name, String surname);


    /**
     * Add a new Station
     */
    void addStation(String idStation, String description, int max, double lat, double lon);


    /**
     * Add a new Bike into a Station

     */
    void addBike(String idBike, String description, double kms, String idStation);


    /**
     * Get the bikes of a station ordered by kilometers
     *
     * @param idStation identifier
     * @return list of bikes
     * @throws StationNotFoundException if the station doesn't exist
     */
    List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException;

    /**
     * get the first Bike of the station
     *
     * @param stationId identifier of the station
     * @param userId identifier of the user
     * @return the first bike of the station
     * @throws UserNotFoundException if the user doesn't exist
     * @throws StationNotFoundException if the station doesn't exist
     */
    Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException;

    /**
     * get the bikes used by the user
     *
     * @param userId identifier of the user
     * @return the list of bikes used by the user
     * @throws UserNotFoundException if the user doesn't exist
     */
    List<Bike> bikesByUser(String userId) throws UserNotFoundException;


    /**
     * get the number of users
     *
     * @return the number of users
     */
    int numUsers();

    /**
     * get the number of stations
     *
     * @return the number of stations
     */
    int numStations();

    /**
     * get the number of bikes in a station
     *
     * @param idStation identifier of the station
     * @return the number of bikes of the station
     * @throws StationNotFoundException if the station doesn't exist
     */
    int numBikes(String idStation) throws StationNotFoundException;

    /**
     * clear all the data structures
     */
    void clear();
}

