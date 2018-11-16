package edu.upc.eetac.dsa;

import org.apache.log4j.Logger;
import sun.security.util.Length;

import java.util.*;

public class MyBikeimpl implements MyBike {
    // singleton
    final static Logger logger = Logger.getLogger(MyBikeimpl.class.getName());
    private static MyBikeimpl instance;
    private ArrayList<Station> stations;
    private HashMap<String, User> userList;
    private LinkedList<Bike> bikesOfStation;
    private LinkedList<Bike> bikesOfUser;




    public void addUser(String idUser, String name, String surname)
    {
        logger.info("Creating an User");
        this.userList.put(idUser,new User(idUser, name, surname));
        logger.info("created User : "+name+surname);
    }

    public void addStation(String idStation, String description, int max, double lat, double lon)
    {
        logger.info("Creating a Station");
        this.stations.add(new Station(idStation,description,max,lat,lon));
        logger.info("created Station : "+idStation);
    }

    public ArrayList<Station> getStations() {
        return stations;
    }
    public List<User> getUserList() {
        List<User> aux = new ArrayList<>();
        aux.addAll(userList.values());
        return aux;
    }
    public HashMap<String, User> getUserList2() {
        return this.userList;
    }

    public void addBike(String idBike, String description, double kms, String idStation)
    {
        logger.info("Adding a bike");
        /*List<Station> stations=getStations();*/
        for (Station st:stations)
        {
            if (st.getIdStation().equals(idStation)) {
                if (
                        st.getMax() == Length(st.getBikes()))
                    logger.info("The Station is full");
                else
                    st.setBikes(new Bike(idBike,description,kms,idStation));
                    logger.info("created Bike : " + idBike);
            }
            else
                logger.info("Station not found");


        }

    }

    private MyBikeimpl(){
        userList = new HashMap<>();
        stations = new ArrayList<>();
        bikesOfUser = new LinkedList<>();
        bikesOfStation = new LinkedList<>();
    }
    public static MyBikeimpl getInstance(){
        if(instance==null){
            instance = new MyBikeimpl();
        }
        return instance;
    }
    public ArrayList<Bike> bikesByStationOrderByKms(String idStation)
    {
        logger.info("Sorting Bikes by Station and Kilometres....");
        ArrayList<Station> st = getStations();
        for (Station s:st) {
            if (s.getIdStation().equals(idStation)) {
                ArrayList<Bike> result = new ArrayList<>();
                for (Bike p : bikesOfStation) {
                    result.add(p);
                }
                Collections.sort(result, (p1, p2) -> (int) (p1.getKms() - p2.getKms()));
                return result;
                logger.info("Sorted!");
            } else {
                logger.info("Station not found");
            }


        }

    }

    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException
    {
        logger.info("Getting Bike");
        for (Station s:stations) {
            if (s.getIdStation().equals(stationId)) {
                if (s.getUser().equals(userId))
                {
                    return bikesOfStation(stationId)
                }
                else logger.info("User not found");

            }
            logger.info("Station not found");
            }

    }
    public List<Bike> bikesByUser(String userId) throws UserNotFoundException
    {
        List<User> users = getUserList();
        for (User u:users){
        if (u.equals(userId))
        {
            return u.getBikes();
        }
        else logger.info("User not found");
    }
    public int numUsers()
    {
        logger.info("Getting number of Users");
        int numUs= getUserList2().size();
        logger.info("Number of Users:"+numUs);
        return numUs;

    }
    public int numStations()
    {
        logger.info("Getting number of Stations");
        int numSt=getStations().size();
        logger.info("Number of Stations:"+numSt);
        return numSt;

    }
    public int numBikes(String idStation)
    {
        logger.info("Getting number of Bikes");
        for (Station s:stations) {
            if (s.getIdStation().equals(idStation)) {
                return s.getBikes();
                logger.info("Number of Bikes:" + s.getBikes());
            }
            else logger.info("Station not found");
        }
    }


    public void clear() {
        this.stations.clear();
        this.bikesOfUser.clear();
        this.bikesOfStation.clear();
        this.userList.clear();
        logger.info("DB cleared");
    }
}
