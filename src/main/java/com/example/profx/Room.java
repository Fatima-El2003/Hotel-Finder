package com.example.profx;

// Room.java
public class Room {
    private String roomId;
    private String description;
    private double price;


    public Room(String roomNumber, String description, double price){
        this.roomId = roomNumber;
        this.description = description;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getRoomNumber() {
        return roomId;
    }

}
