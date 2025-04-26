package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignupFormController implements Initializable {

    @FXML
    private Button btnSignUp;

    @FXML
    private ComboBox<User.Role> cmbRole;

    @FXML
    private Text lnkLogin;

    @FXML
    private AnchorPane root;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private TextField txtEmail;

    @FXML
    private Text id;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField TxtFirstName;

    @FXML
    private TextField TxtLastName;


    private final UserBO userBO =BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void lnkLoginOnAction(MouseEvent event) {
        Parent rootNode = null;
        try {
            rootNode = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/login_form.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.centerOnScreen();
    }

    @FXML
    void onSignUpAction(ActionEvent event) {
       String userid =id.getText();
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();
        String confirmPassword = txtConfirmPassword.getText();
        String email = txtEmail.getText().trim();
        String firstName = TxtFirstName.getText().trim();
        String lName = TxtLastName.getText().trim();
        User.Role role = (User.Role) cmbRole.getValue();

        String namePattern = "^[A-Za-z ]+$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: white; -fx-background-color: transparent;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: white; -fx-background-color: transparent;";


        if (firstName.isEmpty() || !firstName.matches(namePattern)) {
            TxtFirstName.setStyle(errorStyle);
            errorMessage.append("- First name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtFirstName.setStyle(defaultStyle);
        }
        if (lName.isEmpty() || !lName.matches(namePattern)) {
            TxtLastName.setStyle(errorStyle);
            errorMessage.append("- Last name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtLastName.setStyle(defaultStyle);
        }

        if (email.isEmpty() || !email.matches(emailPattern)) {
            txtEmail.setStyle(errorStyle);
            errorMessage.append("- Email is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            txtEmail.setStyle(defaultStyle);
        }

        if (username.isEmpty()) {
            txtUsername.setStyle(errorStyle);
            errorMessage.append("- User name is empty\n");
            hasErrors = true;

        }else{
            txtUsername.setStyle(defaultStyle);
        }
        if (password.isEmpty()) {
            txtPassword.setStyle(errorStyle);
            errorMessage.append("- Password field is empty\n");
            hasErrors = true;

        }else{
            txtPassword.setStyle(defaultStyle);
        }
        if (confirmPassword.isEmpty()) {
            txtConfirmPassword.setStyle(errorStyle);
            errorMessage.append("- Confirm password field is empty\n");
            hasErrors = true;

        }else{
            txtConfirmPassword.setStyle(defaultStyle);
        }

        if(!password.equals(confirmPassword)){
            txtConfirmPassword.setStyle(errorStyle);
            errorMessage.append("- Confirm password does not match to the password\n");
            hasErrors = true;
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());

        boolean isSaved = userBO.saveUser(new UserDTO(String.valueOf(id),firstName,lName,email,username,hashedPassword,role));
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "User signup successfully!").show();
            navigateTo("/view/LoginForm.fxml");
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed").show();
        }

    }

    public void navigateTo(String fxmlPath) {
        try {
            root.getChildren().clear();
            AnchorPane load = FXMLLoader.load(getClass().getResource(fxmlPath));
            root.getChildren().add(load);
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load page!").show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtFirstName.setStyle(defaultStyle);
        TxtLastName.setStyle(defaultStyle);
        txtEmail.setStyle(defaultStyle);
        txtUsername.setStyle(defaultStyle);
        txtPassword.setStyle(defaultStyle);
        txtConfirmPassword.setStyle(defaultStyle);

        try{
            refreshPage();

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load users id").show();
        }
        cmbRole.getItems().addAll(User.Role.values());
    }
    private void refreshPage() {
        loadNextUserId();

        TxtFirstName.setText("");
        TxtLastName.setText("");
        txtEmail.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        cmbRole.setValue(User.Role.ADMIN);

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtFirstName.setStyle(defaultStyle);
        TxtLastName.setStyle(defaultStyle);
        txtEmail.setStyle(defaultStyle);
        txtUsername.setStyle(defaultStyle);
        txtPassword.setStyle(defaultStyle);
        txtConfirmPassword.setStyle(defaultStyle);
    }
    private void loadNextUserId() {
        String nextUserId = userBO.generateNextUserId();
        id.setText(nextUserId);
    }
}
