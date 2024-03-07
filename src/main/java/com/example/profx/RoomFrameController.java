package com.example.profx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class RoomFrameController {
    @FXML
    private Label roomNumberLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private ImageView roomImageView;
    @FXML
    private DatePicker checkinDate;
    @FXML
    private DatePicker checkoutDate;
    @FXML
    private Button reserveButton;
    private String roomId;
    @FXML
    private void reserveRoom() {
        LocalDate reservationCheckinDate = checkinDate.getValue();
        LocalDate reservationCheckoutDate = checkoutDate.getValue();
        String reservationState = "pending";
        saveToReservationTable(reservationCheckinDate, reservationCheckoutDate, roomId, reservationState);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reservation.fxml"));
            Parent reservationPage = loader.load();

            // Get the controller for the second page
            ReservationController reservationController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(reservationPage));

            Stage currentStage = (Stage) roomNumberLabel.getScene().getWindow();
            currentStage.close();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToReservationTable(LocalDate checkinDate, LocalDate checkoutDate, String id_room, String reservationState) {
        try (Connection connection = mysqlconnect.connectDb()) {
            String sql = "INSERT INTO reservation (check_in_date, check_out_date, id_room,payment_state) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                java.sql.Date checkinSqlDate = java.sql.Date.valueOf(checkinDate);
                java.sql.Date checkoutSqlDate = java.sql.Date.valueOf(checkoutDate);

                statement.setDate(1, checkinSqlDate);
                statement.setDate(2, checkoutSqlDate);
                statement.setString(3, id_room);
                statement.setString(4, reservationState);
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Saved to reservation table");
                } else {
                    System.out.println("Failed to save to reservation table");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately, log or rethrow
        }
    }
    public void initializeData(Room selectedRoom) {
        roomNumberLabel.setText(selectedRoom.getRoomNumber());
        descriptionLabel.setText(selectedRoom.getDescription());
        priceLabel.setText(String.valueOf(selectedRoom.getPrice()));
        roomId = selectedRoom.getRoomNumber();
    }
}
