package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    void BtnCreateNewAccountOnAction(ActionEvent event) {

    }

    @FXML
    void BtnForgotPwOnAction(ActionEvent event) {

    }

    @FXML
    void BtnSignInOnAction(ActionEvent event) throws IOException {
        String email= TxtUserName.getText();
        String password=TxtPassword.getText();

        User user = userBO.searchUserByEmail(email);

        if (user != null) {
            if (user.getRole().equals(User.UserRole.ADMIN)) {
                boolean issPassRight  = PasswordUtil.verifyPassword(password,user.getPassword());
                if (issPassRight) {
                    navigateToAdminSidePane();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Wrong Password").show();
                }
            }else{
                boolean issPassRight  = PasswordUtil.verifyPassword(password,user.getPassword());
                if (issPassRight) {
                    navigatoToReceptionistSidePanel();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Wrong Password").show();
                }
            }
        }
        else {
            new Alert(Alert.AlertType.ERROR,"User Not Found").show();
        }
    }
    void navigateToAdminSidePane() throws IOException {
        AnchorPane mainNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/AdminSidePane.fxml")));

        Scene scene = new Scene(mainNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }
    void navigatoToReceptionistSidePanel() throws IOException {
        AnchorPane mainNode = FXMLLoader.load(Objects.requireNonNull(this.getClass().getResource("/view/ReceptionistSidePane.fxml")));
        Scene scene = new Scene(mainNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Home Page");
    }
}
