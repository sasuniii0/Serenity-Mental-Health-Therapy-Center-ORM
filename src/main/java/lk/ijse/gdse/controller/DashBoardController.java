package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.entity.User;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {

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

    public void navigateTo(String fxmlPath) {
        try{
            sideAncPane.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource(fxmlPath));
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to log out?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"))));
            stage.centerOnScreen();
        }
    }

    @FXML
    void BtnPaymentOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    void BtnRegistrationOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/Patient.fxml");
    }

    @FXML
    void BtnSessionAppoinmentOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/TherapySessionSchedule.fxml");
    }

    @FXML
    void BtnTherapistOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/Therapist.fxml");
    }

    @FXML
    void BtnTherapyProgramsOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/TherapyProgram.fxml");
    }

    @FXML
    void BtnUserOnAction(ActionEvent event) throws IOException {
        navigateTo("/view/User.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
