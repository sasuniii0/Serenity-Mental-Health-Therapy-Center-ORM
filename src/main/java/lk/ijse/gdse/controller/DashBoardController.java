package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            loggedInUser.setRole("admin");
        }

        initializeRoleBasedUI();
    }

    private void initializeRoleBasedUI() {
        if (loggedInUser != null) {
            if (loggedInUser.getRole().equalsIgnoreCase("admin")) {
                loadSidePane("/view/AdminSidePane.fxml");
                setupAdminUI();
            } else {
                loadSidePane("/view/ReceptionistSidePane.fxml");
                setupReceptionistUI();
            }
        } else {
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
        BtnUser.setDisable(false);
        BtnTherapist.setDisable(false);
        BtnTherapyPrograms.setDisable(false);
    }

    private void setupReceptionistUI() {
        BtnUser.setDisable(true);
        BtnTherapist.setDisable(true);

        BtnPatient.setDisable(false);
        BtnReg.setDisable(false);
        BtnSessionAppoinment.setDisable(false);
    }


    @FXML
    void BtnHomeOnAction(ActionEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/dashboard_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage = (Stage) ((Button)event.getSource()).getScene().getWindow();

        stage.setScene(new Scene(rootNode));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void BtnLogOutOnAction(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout Confirmation");
            alert.setHeaderText("Are you sure you want to logout?");
            alert.setContentText("Any unsaved changes will be lost.");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                URL loginViewUrl = getClass().getResource("/view/LoginForm.fxml");
                if (loginViewUrl == null) {
                    throw new IOException("Login view FXML file not found");
                }

                Parent rootNode = FXMLLoader.load(loginViewUrl);

                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();

                Scene scene = new Scene(rootNode);
                stage.setScene(scene);
                stage.setTitle("Login");
                stage.centerOnScreen();

                clearSessionData();
            }
        } catch (IOException e) {
            showErrorAlert("Failed to load login screen", e.getMessage());
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, "Logout error", e);
        } catch (Exception e) {
            showErrorAlert("Logout failed", "An unexpected error occurred");
            Logger.getLogger(DashBoardController.class.getName()).log(Level.SEVERE, "Unexpected logout error", e);
        }
    }

    private void clearSessionData() {

    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
