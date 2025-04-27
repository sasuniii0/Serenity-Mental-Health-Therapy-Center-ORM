package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.PasswordUtil;

import java.io.IOException;
import java.util.Objects;

public class LoginFormController {

    @FXML
    private Button BtnCreateNewAccount;

    @FXML
    private Button BtnForgotPw;

    @FXML
    private Button BtnSignIn;

    @FXML
    private PasswordField TxtPassword;

    @FXML
    private TextField TxtUserName;

    @FXML
    private AnchorPane root;



    private final UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void BtnCreateNewAccountOnAction(ActionEvent event) throws IOException {
        loadForm("/view/SignupForm.fxml", "SignUp Page");
    }

    @FXML
    void BtnForgotPwOnAction(ActionEvent event) throws IOException {
        loadForm("/view/ForgotPw.fxml", "Forgot Password Page");
    }

    @FXML
    void BtnSignInOnAction(ActionEvent event) {
        try {
            String email = TxtUserName.getText().trim();
            String password = TxtPassword.getText().trim();

            if (email.isEmpty() || password.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter both email and password");
                return;
            }

            User user = userBO.searchUserByEmail(email);

            if (user == null) {
                showAlert(Alert.AlertType.ERROR, "Authentication Failed", "User not found");
                return;
            }

            if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
                showAlert(Alert.AlertType.ERROR, "Authentication Failed", "Wrong password");
                return;
            }

            loadDashboard(user);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred during login");
        }
    }

    private void loadDashboard(User user) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
        Parent rootNode = loader.load();

        DashBoardController dashboardController = loader.getController();
        dashboardController.setLoggedInUser(user);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(rootNode));
        stage.setTitle("Therapy Center - Dashboard");
        stage.centerOnScreen();
        stage.show();
    }

    private void loadForm(String fxmlPath, String title) throws IOException {
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(pane));
        stage.setTitle(title);
        stage.centerOnScreen();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void onEnterPressed(ActionEvent event) {
        BtnSignInOnAction(event);
    }

}