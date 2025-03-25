package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;

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

/*
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
*/

    @FXML
    void BtnCreateNewAccountOnAction(ActionEvent event) {

    }

    @FXML
    void BtnForgotPwOnAction(ActionEvent event) {

    }

    @FXML
    void BtnSignInOnAction(ActionEvent event) {
        /*String email= TxtUserName.getText();
        String password=TxtPassword.getText();

        boolean isAvailable = userBO.searchUserByEmail(email);*/
    }

}
