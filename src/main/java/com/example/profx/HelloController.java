package com.example.profx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.event.ActionEvent;
import java.io.*;

import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import com.example.profx.*;
public class HelloController implements Initializable {
    @FXML
    private WebView webview;
    @FXML
    private Label welcomeText;
    @FXML
    private Button switchPageButton;

    @FXML
    public void handleSwitchPageButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("room.fxml"));
            Parent roomPage = loader.load();

            // Get the controller for the second page
            RoomController RooomController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(roomPage));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private CheckBox availaibility ;
    @FXML
    public void handleAvailaibility(){
        if(availaibility.isSelected()) {
            listHotel = mysqlconnect.selectAvailaibleHotels();
            hotelTable.setItems(listHotel);
        }
        else{
            listHotel = mysqlconnect.selectHotels();
            hotelTable.setItems(listHotel);
        }
    }
    @FXML
    private CheckBox fitness ;
    @FXML
    public void handleFitness(){
        if(fitness.isSelected()) {
            listHotel = mysqlconnect.selectFitnessleHotels();
            hotelTable.setItems(listHotel);
        }
        else{
            listHotel = mysqlconnect.selectHotels();
            hotelTable.setItems(listHotel);
        }
    }
    @FXML
    private CheckBox restaurant ;
    @FXML
    public void handleRestaurant(){
        if(restaurant.isSelected()) {
            listHotel = mysqlconnect.selectRestaurantleHotels();
            hotelTable.setItems(listHotel);
        }
        else{
            listHotel = mysqlconnect.selectHotels();
            hotelTable.setItems(listHotel);
        }
    }
    @FXML
    private CheckBox roomservice ;
    @FXML
    public void handleRoomService(){
        if(roomservice.isSelected()) {
            listHotel = mysqlconnect.selectRoomServiceHotels();
            hotelTable.setItems(listHotel);
        }
        else{
            listHotel = mysqlconnect.selectHotels();
            hotelTable.setItems(listHotel);
        }
    }
    @FXML
    private CheckBox parking ;
    @FXML
    public void handleParking(){
        if(parking.isSelected()) {
            listHotel = mysqlconnect.selectParkingHotels();
            hotelTable.setItems(listHotel);
        }
        else{
            listHotel = mysqlconnect.selectHotels();
            hotelTable.setItems(listHotel);
        }
    }
    @FXML
    private CheckBox wifi ;
    @FXML
    public void handleWifi(){
        if(wifi.isSelected()) {
            listHotel = mysqlconnect.selectWifiHotels();
            hotelTable.setItems(listHotel);
        }
        else{
            listHotel = mysqlconnect.selectHotels();
            hotelTable.setItems(listHotel);
        }
    }
    @FXML
    private TableView<Hotel> hotelTable;
    @FXML
    private TableColumn<Hotel,String> NameCol;
    @FXML
    private TableColumn<Hotel,Integer> RatingCol;
    @FXML
    private TableColumn<Hotel,String> ContactCol;
    @FXML
    private TableColumn<Hotel,String> AddressCol;
    ObservableList<Hotel> listHotel;
    int index = -1;
    private Connection connect = null;
    private PreparedStatement prepare = null;
    private ResultSet result = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //create a webengine
        WebEngine webEngine = webview.getEngine();
        //load page
        webEngine.load("file:///D:/frontend/index.html");
        //send data

        try (FileReader reader = new FileReader("output.txt")) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
            StringBuilder script = new StringBuilder("var coordinates = [");
            System.out.println(jsonArray);
           /* for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                double lat = jsonObj.getDouble("latitude");
                double lon = jsonObj.getDouble("longitude");

                script.append("[").append(lat).append(", ").append(lon).append("]");
                if (i < jsonArray.length() - 1) {
                    script.append(", ");
                }
            }

            script.append("];");

            // Add JavaScript code to create markers using the coordinates
            script.append("for (var i = 0; i < coordinates.length; i++) {")
                    .append("    L.marker(coordinates[i]).addTo(map);")
                    .append("}");

            webEngine.executeScript(script.toString());*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        NameCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("name"));
        RatingCol.setCellValueFactory(new PropertyValueFactory<Hotel, Integer>("rating"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("contact"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<Hotel, String>("address"));

        listHotel = mysqlconnect.selectHotels();
        hotelTable.setItems(listHotel);
    }

}