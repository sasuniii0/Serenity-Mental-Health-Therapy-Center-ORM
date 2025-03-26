package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.bo.custom.impl.UserBOImpl;
import lombok.Setter;

import java.io.IOException;
import java.util.Objects;

public class NewPwController {

    @FXML
    private Button BtnChange;

    @FXML
    private TextField TxtNewPassword;

    @FXML
    private AnchorPane root;

    @Setter
    private static String selectedEmail = "";
    UserBO userBO = new UserBOImpl();


    public void setUserData(String email) {
        this.selectedEmail = email;
        System.out.println("Selected email: " + selectedEmail);
    }

    @FXML
    void BtnChangeOnAction(ActionEvent event) {

    }

    public void onChangePasswordAction(ActionEvent actionEvent) throws IOException {
        String password = TxtNewPassword.getText();
        boolean isUpdate = userBO.updateUser(selectedEmail, password);
        if (isUpdate) {
            new Alert(Alert.AlertType.INFORMATION, "Password updated successfully").show();
            Parent load = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginPage.fxml")));
            root.getChildren().clear();
            root.getChildren().add(load);
        }else  {
            new Alert(Alert.AlertType.ERROR, "Password not updated").show();
        }
    }

    public String getSelectedEmail() {
        return selectedEmail;
    }

}
