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
    void BtnLogOutOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/LoginPage.fxml");
    }

    @FXML
    void BtnPatientOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/PatientForm.fxml");
    }

    @FXML
    void BtnPaymentOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/PaymentForm.fxml");
    }

    @FXML
    void BtnRegistrationOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/PatientForm.fxml");
    }

    @FXML
    void BtnSessionAppoinmentOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/SessionAppoinmentForm.fxml");
    }

    @FXML
    void BtnTherapistOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/TherapistForm.fxml");
    }

    @FXML
    void BtnTherapyProgramsOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/TherapyProgramsForm.fxml");
    }

    @FXML
    void BtnUserOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/UserForm.fxml");
    }

}
