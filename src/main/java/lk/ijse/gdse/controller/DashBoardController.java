package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    private Button BtnReg;

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

    private User loggedInUser;

    public void setLoggedInUser(User user) {
        if (loggedInUser == null) {
            loggedInUser = new User();
            loggedInUser.setRole("admin"); // Default to admin view
        }

        initializeRoleBasedUI();
    }

    private void initializeRoleBasedUI() {
        if (loggedInUser != null) {
            // Load the appropriate side pane based on user role
            if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                loadSidePane("/view/AdminSidePane.fxml");
                setupAdminUI();
            } else {
                loadSidePane("/view/ReceptionistSidePane.fxml");
                setupReceptionistUI();
            }
        } else {
            // Default to admin view if no user is set (for testing)
            loadSidePane("/view/AdminSidePane.fxml");
        }
    }
    private void loadSidePane(String fxmlPath) {
        try {
            sideAncPane.getChildren().clear();
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
            sideAncPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Failed to load side panel!").show();
        }
    }

    private void setupAdminUI() {
        // Enable all buttons for admin
        BtnUser.setDisable(false);
        BtnTherapist.setDisable(false);
        BtnTherapyPrograms.setDisable(false);
        // ... enable other admin-only features
    }

    private void setupReceptionistUI() {
        // Disable admin-only features for receptionist
        BtnUser.setDisable(true);
        BtnTherapist.setDisable(true);
        // ... disable other admin-only buttons

        // Enable receptionist-specific features
        BtnPatient.setDisable(false);
        BtnReg.setDisable(false);
        BtnSessionAppoinment.setDisable(false);
    }


    @FXML
    void BtnHomeOnAction(ActionEvent event) {

    }

    @FXML
    void BtnLogOutOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPatientOnAction(ActionEvent event) {
        navigateTo("/view/Patient.fxml");
    }

    @FXML
    void BtnPaymentOnAction(ActionEvent event) {
        navigateTo("/view/Payment.fxml");
    }

    @FXML
    void BtnRegOnAction(ActionEvent event) {
        navigateTo("/view/Registration.fxml");
    }

    @FXML
    void BtnSessionAppoinmentOnAction(ActionEvent event) {
        navigateTo("/view/TherapySessionSchedule.fxml");
    }

    @FXML
    void BtnTherapistOnAction(ActionEvent event) {
        navigateTo("/view/Therapist.fxml");
    }

    @FXML
    void BtnTherapyProgramsOnAction(ActionEvent event) {
        navigateTo("/view/TherapyProgram.fxml");
    }

    @FXML
    void BtnUserOnAction(ActionEvent event) {
        navigateTo("/view/User.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void navigateTo(String fxmlPath){
        try{
            sideAncPane.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            sideAncPane.getChildren().add(load);
        }catch (IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }
}
