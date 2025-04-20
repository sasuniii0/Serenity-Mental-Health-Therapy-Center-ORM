package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.entity.User;
import lk.ijse.gdse.util.PasswordUtil;

import java.io.IOException;

public class SignupFormController {

    @FXML
    private Button BtnLogIn;

    @FXML
    public Button BtnSignUp;

    @FXML
    private ComboBox<String> CmbAdminRece;

    @FXML
    private TextField TxtAdminCode;

    @FXML
    private TextField TxtAdminName;

    @FXML
    private TextField TxtEmail;

    @FXML
    public PasswordField TxtPassword;

    @FXML
    public PasswordField TxtPasswordReEnter;

    @FXML
    private TextField TxtUserName;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnLogInOnAction(ActionEvent event) {

    }

    @FXML
    void BtnSignUpOnAction(ActionEvent event) {

    }

    @FXML
    void CmbAdminReceOnAction(ActionEvent event) {

    }
    UserBO userBo = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize() throws IOException {
        loadUsers();
    }

    private void loadUsers() {
        ObservableList<String > obList = FXCollections.observableArrayList();
        try{
            obList.add("Admin");
            obList.add("Receptionist");
            CmbAdminRece.setItems(obList);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    public void onSignUpAction(ActionEvent actionEvent) {
        String TxtUserName = this.TxtUserName.getText();
        String TxtEmail = this.TxtEmail.getText();
        String TxtPassword = this.TxtPassword.getText();
        String reEnterPwd = this.TxtPasswordReEnter.getText();
        String role = this.CmbAdminRece.getValue().toString();
        String adMail=this.TxtAdminName.getText();
        String adCode=this.TxtAdminCode.getText();

        String hashedPass = PasswordUtil.hashPassword(reEnterPwd);
        User user = userBo.searchUserByAdminEmail(adMail);

/*
        switch (isValid())
*/



    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource(location))));
        stage.centerOnScreen();
    }

    public void lnkLoginOnAction(MouseEvent mouseEvent) throws IOException {
        setUi("/view/LoginForm.fxml");
    }

    private UserController usersFormController; // Reference to UsersFormController

    public void setUserController(UserController userController) {
        this.usersFormController = userController;
    }

    public void signupInitialize() {
        User userselected = usersFormController.userSelected;
        TxtUserName.setText(userselected.getEmail());
        TxtPassword.setText(userselected.getPassword());
        TxtAdminName.setText(userselected.getFirstName());
        TxtAdminCode.setText(userselected.getLastName());
        CmbAdminRece.setValue(String.valueOf(userselected.getRole()));
        TxtEmail.setText(userselected.getEmail());
        TxtPasswordReEnter.setText(userselected.getPassword());
        TxtPassword.setVisible(false);
        TxtPassword.setManaged(false);
        // Copy text to the visible TextField

    }
}
