package com.example.profx;

public class Hotel{
    private int id;
    private String name;
    private int rating;
    private String contact;
    private String address;

    public Hotel(int id, String name, int rating, String contact, String address){

        this.name = name;
        this.rating = rating;
        this.contact = contact;
        this.address = address;
    }
    public int getInt(){
        return id;
    }
    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }
}
