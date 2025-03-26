package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class VerifyAccountController {

    @FXML
    private Text BtnChange;

    @FXML
    private Text BtnChange1;

    @FXML
    private Button BtnVerify;

    @FXML
    private TextField TxtCode;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnChangeOnAction(MouseEvent event) {

    }
    private static String code = null;
    private static String selectedEmail = "";


    public static void setUserData(String verificationCode, String email) {
        code = verificationCode;
        selectedEmail = email;
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/" + location + ".fxml"))))); // Fixed path
        stage.centerOnScreen();
    }

    @FXML
    void BtnVerifyOnAction(ActionEvent event) throws IOException {

    }

    public void onVerifyAction(ActionEvent actionEvent) throws IOException {
        if (String.valueOf(code).equals(TxtCode.getText().trim())) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/ResetPwd.fxml"));
            Parent parent = fxmlLoader.load();

            // Fetch and set data to ResetPasswordController
            NewPwController controller = fxmlLoader.getController();
            controller.setUserData(selectedEmail);

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(parent));
            stage.centerOnScreen();
        } else {
            new Alert(Alert.AlertType.ERROR, "Wrong Verification Code").show();
        }
    }
}
