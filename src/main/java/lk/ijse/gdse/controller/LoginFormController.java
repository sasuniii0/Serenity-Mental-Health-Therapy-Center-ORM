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

    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void BtnCreateNewAccountOnAction(ActionEvent event) throws IOException {
        AnchorPane mainNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/SignupForm.fxml")));

        Scene scene = new Scene(mainNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("SignUp Page");
    }

    @FXML
    void BtnForgotPwOnAction(ActionEvent event) throws IOException {
        AnchorPane mainNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/ForgotPw.fxml")));

        Scene scene = new Scene(mainNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Forgot-password Page");
    }

    @FXML
    void BtnSignInOnAction(ActionEvent event) throws IOException {
        String email= TxtUserName.getText();
        String password=TxtPassword.getText();

        User user = userBO.searchUserByEmail(email);

        if (user != null) {
            boolean isPassRight = PasswordUtil.verifyPassword(password, user.getPassword());

            if (isPassRight) {
                // Load main Dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
                Parent rootNode = loader.load();

                // Get the Dashboard controller
                DashBoardController dashboardController = loader.getController();

                dashboardController.setLoggedInUser(user);

                // Load the correct side pane based on the user role
                if (user.getRole().equals(User.UserRole.Admin)) {
                    dashboardController.navigateTo("/view/AdminSidePane.fxml");
                } else {
                    dashboardController.navigateTo("/view/ReceptionistSidePane.fxml");
                }

                // Set scene
                Scene scene = new Scene(rootNode);
                Stage stage = (Stage) root.getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            } else {
                new Alert(Alert.AlertType.ERROR, "Wrong Password").show();
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR,"User Not Found").show();
        }
    }

    public void navigateTo(String fxml) throws IOException {
        try{
            root.getChildren().clear();
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxml)));
            root.getChildren().add(anchorPane);
        }catch(IOException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,"Failed to load the page");
        }

    }
}
