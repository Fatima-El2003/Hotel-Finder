package com.example.profx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationController {
    @FXML
    private TextField fullName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField zip;
    @FXML
    private TextField creditCardNumbr;
    @FXML
    private TextField nameOnCard;
    @FXML
    private TextField expirationDay;
    @FXML
    private TextField expirationMonth;
    @FXML
    private TextField cvv;
    @FXML
    private Button checkoutButton;
    boolean paymentSuccessful;
    @FXML
    private void handlePayment(){
        String clientFullName = fullName.getText();
        String clientEmail = email.getText();
        String clientPhone = phone.getText();
        String clientAddress = address.getText();
        String clientZipCode = zip.getText();
        String clientCity = city.getText();
        String payementCreditCard = creditCardNumbr.getText();
        String payementNameOnCard = nameOnCard.getText();
        String payementExpirationDay = expirationDay.getText();
        String payementExpirationMonth = expirationMonth.getText();
        String payementCvv = cvv.getText();
        if (clientFullName.isEmpty() || clientEmail.isEmpty() || clientPhone.isEmpty() ||
                clientAddress.isEmpty() || clientZipCode.isEmpty() || clientCity.isEmpty() ||
                payementCreditCard.isEmpty() || payementNameOnCard.isEmpty() ||
                payementExpirationDay.isEmpty() || payementExpirationMonth.isEmpty() || payementCvv.isEmpty()) {
            paymentSuccessful = false;
            // Display a warning message
            showAlert("Warning", "Please fill in all fields.");

        }
        else{
            //on stocke dans la base de données les donnees recuperes de text field.
            saveToClientTable(clientFullName, clientEmail, clientPhone, clientAddress, clientZipCode, clientCity);
            saveToPaymentTable(payementCreditCard, payementNameOnCard, payementExpirationDay, payementExpirationMonth, payementCvv);
            paymentSuccessful = true;
            showAlert("Success", "Payment successful!");

        }
    }
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Load the CSS file for styling
        Scene alertScene = alert.getDialogPane().getScene();
        alertScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        if (paymentSuccessful) {
            // Green check mark image
            //Image checkMarkImage = new Image(getClass().getResourceAsStream("/com/example/profx/images/check_mark.png"));
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/example/profx/images/check_mark.png")));
            imageView.setFitWidth(30);
            imageView.setFitHeight(30);
            alert.setGraphic(imageView);////it is normally setGraphic(imageView)
        } else {
            // Set the graphic to null for failure (remove default icon)
            alert.setGraphic(null);
        }
        alert.showAndWait();
    }
    public static void saveToClientTable(String fullName, String email, String phone, String address, String zipCode, String city) {
        try (Connection connection = mysqlconnect.connectDb()) {
            String sql = "INSERT INTO client (full_name, email, phone_number, address, zip_code) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, fullName);
                statement.setString(2, email);
                statement.setString(3, phone);
                statement.setString(4, address);
                statement.setString(5, zipCode);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Saved to client table");
                } else {
                    System.out.println("Failed to save to client table");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public static void  saveToPaymentTable(String CreditCard, String NameOnCard, String ExpirationDay, String ExpirationMonth, String Cvv) {
            try (Connection connection= mysqlconnect.connectDb()) {
                String sql = "INSERT INTO payment (credit_card, name_on_card, expiration_day, expiration_month, cvv) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, CreditCard);
                    statement.setString(2, NameOnCard);
                    statement.setString(3, ExpirationDay);
                    statement.setString(4, ExpirationMonth);
                    statement.setString(5, Cvv);

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Saved to payement table");
                    } else {
                        System.out.println("Failed to save to payement table");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


}
