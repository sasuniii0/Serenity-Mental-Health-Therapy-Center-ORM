package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.entity.User;
import lombok.Setter;

import java.io.IOException;
import java.util.Objects;

public class DashBoardController {

    @FXML
    private Button BtnHome;

    @FXML
    private Button BtnLogOut;

    @FXML
    private Button BtnPatient;

    @FXML
    private Button BtnPayment;

    @FXML
    private Button BtnRegistration;

    @FXML
    private Button BtnSessionAppoinment;

    @FXML
    private Button BtnTherapist;

    @FXML
    private Button BtnTherapyPrograms;

    @FXML
    private Button BtnUser;

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane sideAncPane;

    @FXML
    private Pane sidePane;

    // Method to receive logged-in user data
    @Setter
    private User loggedInUser;  // Store the logged-in user

    public void navigateTo(String fxmlPath) throws IOException {

        try{
            sideAncPane.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            sideAncPane.getChildren().add(anchorPane);
        }catch(IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load the page");
        }
    }

    @FXML
    void BtnHomeOnAction(ActionEvent event) throws IOException {
        if (loggedInUser != null) {
            System.out.println("Logged-in user: " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
            System.out.println("Role: " + loggedInUser.getRole());

            if (loggedInUser.getRole() == User.UserRole.ADMIN) {
                navigateTo("/view/AdminSidePane.fxml");
            } else {
                navigateTo("/view/ReceptionistSidePane.fxml");
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "No user data found!").show();
        }
    }

    @FXML
    void BtnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPatientOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void BtnRegistrationOnAction(ActionEvent event) {

    }

    @FXML
    void BtnSessionAppoinmentOnAction(ActionEvent event) {

    }

    @FXML
    void BtnTherapistOnAction(ActionEvent event) {

    }

    @FXML
    void BtnTherapyProgramsOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUserOnAction(ActionEvent event) {

    }

}
