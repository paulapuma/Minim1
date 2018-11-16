package edu.upc.eetac.dsa;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String idUser;
    private String name;
    private String surname;
    private int Bikes;

    /*public User() {

    }
    */

    public User(String idUser, String name, String surname) {
        this.idUser=idUser;
        this.name = name;
        this.surname=surname;
        /*
        this.listOrder = new ArrayList<>();
        */

    }

    public int getBikes() {
        return Bikes;
    }

    public void setBikes(int bikes) {
        Bikes = bikes;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    /*
    public List<Order> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<Order> listOrder) {
        this.listOrder = listOrder;
    }
    public void addOrder(Order o){
        this.listOrder.add(o);
    }
    */
}
