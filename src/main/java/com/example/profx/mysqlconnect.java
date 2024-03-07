package com.example.profx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class mysqlconnect {
    public static Connection connectDb() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/hotels_db", "root", "");
            return connect;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static ObservableList<Hotel> selectHotels(){
        ObservableList <Hotel> hotelList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM hotel";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Hotel hotelData;
            while(result.next()){
                hotelData = new Hotel(result.getInt("id_hotel"), result.getString("hotel_name"), result.getInt("rating"), result.getString("contact"), result.getString("address"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                hotelList.add(hotelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public static ObservableList<Hotel> selectAvailaibleHotels(){
        ObservableList <Hotel> hotelList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM hotel Where availaiblity = 1";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Hotel hotelData;
            while(result.next()){
                hotelData = new Hotel(result.getInt("id_hotel"), result.getString("hotel_name"), result.getInt("rating"), result.getString("contact"), result.getString("address"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                hotelList.add(hotelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public static ObservableList<Hotel> selectFitnessleHotels(){
        ObservableList <Hotel> hotelList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM hotel Where fitness = 1";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Hotel hotelData;
            while(result.next()){
                hotelData = new Hotel(result.getInt("id_hotel"), result.getString("hotel_name"), result.getInt("rating"), result.getString("contact"), result.getString("address"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                hotelList.add(hotelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public static ObservableList<Hotel> selectRestaurantleHotels(){
        ObservableList <Hotel> hotelList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM hotel Where restaurant = 1";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Hotel hotelData;
            while(result.next()){
                hotelData = new Hotel(result.getInt("id_hotel"), result.getString("hotel_name"), result.getInt("rating"), result.getString("contact"), result.getString("address"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                hotelList.add(hotelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public static ObservableList<Hotel> selectRoomServiceHotels(){
        ObservableList <Hotel> hotelList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM hotel Where roomservice = 1";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Hotel hotelData;
            while(result.next()){
                hotelData = new Hotel(result.getInt("id_hotel"), result.getString("hotel_name"), result.getInt("rating"), result.getString("contact"), result.getString("address"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                hotelList.add(hotelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public static ObservableList<Hotel> selectParkingHotels(){
        ObservableList <Hotel> hotelList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM hotel Where parking = 1";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Hotel hotelData;
            while(result.next()){
                hotelData = new Hotel(result.getInt("id_hotel"), result.getString("hotel_name"), result.getInt("rating"), result.getString("contact"), result.getString("address"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                hotelList.add(hotelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public static ObservableList<Hotel> selectWifiHotels(){
        ObservableList <Hotel> hotelList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM hotel Where wifi = 1";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Hotel hotelData;
            while(result.next()){
                hotelData = new Hotel(result.getInt("id_hotel"), result.getString("hotel_name"), result.getInt("rating"), result.getString("contact"), result.getString("address"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                hotelList.add(hotelData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelList;
    }
    public static ObservableList<Room> selectRoom(){
        ObservableList <Room> roomList = FXCollections.observableArrayList();//list of hotels
        String sql = " SELECT * FROM room Where id_hotel = 4";
        Connection connect = mysqlconnect.connectDb();//connect object created after the connection with my db
        try{
            PreparedStatement prepare = connect.prepareStatement(sql);
            ResultSet result = prepare.executeQuery();
            Room roomData;
            while(result.next()){
                roomData = new Room(result.getString("id"), result.getString("description"), result.getDouble("price"));//data returned from database is stored in the hotel object using contructur then added to list of hotels
                roomList.add(roomData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roomList;
    }

}