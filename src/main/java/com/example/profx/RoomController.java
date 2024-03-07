package com.example.profx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.example.profx.*;
import static java.util.Arrays.*;
public class RoomController {
    @FXML
    private StackPane roomsAnchorePane;

    @FXML
    public void initialize() {
        if (roomsAnchorePane == null) {
            throw new IllegalStateException("AnchorPane not injected properly. Check your FXML file.");
        }
        // Fetch room data from the database (replace this with your data retrieval logic)
        ObservableList<Room> roomList =  mysqlconnect.selectRoom();

        StackPane.setAlignment(roomsAnchorePane, Pos.TOP_LEFT);

        double roomFrameWidth = 200.0; // Adjust as needed
        double spacing = 10.0; // Adjust as needed

        // Create RoomFrame for each room and add it to the StackPane
        double currentX = spacing;
        // Create RoomFrame for each room and add it to the FlowPane
        for (Room room : roomList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("roomFrame.fxml"));
                Parent roomFrame = loader.load();

                RoomFrameController frameController = loader.getController();
                frameController.initializeData(room);

                StackPane.setAlignment(roomFrame, Pos.TOP_LEFT);
                StackPane.setMargin(roomFrame, new Insets(0, 0, 0, currentX));

                currentX += roomFrameWidth + spacing;
                roomsAnchorePane.getChildren().add(roomFrame);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
