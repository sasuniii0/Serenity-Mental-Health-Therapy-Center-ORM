package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;
import lk.ijse.gdse.entity.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController implements Initializable {

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnUpdate;

    @FXML
    private ComboBox<User.Role> CmbRole;

    @FXML
    private TableColumn<UserTM,String> ColEmail;

    @FXML
    private TableColumn<UserTM,String> ColFirstName;

    @FXML
    private TableColumn<UserTM,String> ColId;

    @FXML
    private TableColumn<UserTM,String> ColLastName;

    @FXML
    private TableColumn<UserTM,String> ColPw;

    @FXML
    private TableColumn<UserTM,String> ColRole;

    @FXML
    private TableColumn<UserTM,String> ColUserName;

    @FXML
    private TableView<UserTM> TblUsers;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtFirstName;

    @FXML
    private TextField TxtLastName;

    @FXML
    private PasswordField TxtPassword;

    @FXML
    private PasswordField TxtReEnterPassword;

    @FXML
    private TextField TxtUserName;

    @FXML
    private AnchorPane root;

    @FXML
    private Label txtDate;

    @FXML
    private Label txtId;


    private final UserBO userBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    private final ObservableList<UserTM> userList = FXCollections.observableArrayList();

    @FXML
    void BtnAddOnAction(ActionEvent event) {
        if (!validateFields()) {
            new Alert(Alert.AlertType.WARNING, "Please fill all fields correctly").show();
            return;
        }

        try {
            UserDTO userDTO = new UserDTO(
                    txtId.getText(),
                    TxtUserName.getText(),
                    TxtEmail.getText(),
                    TxtPassword.getText(),
                    TxtFirstName.getText(),
                    TxtLastName.getText(),
                    CmbRole.getValue()
            );

            boolean isSaved = userBO.saveUser(userDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "User saved successfully").show();
                clearFields();
                loadTableData();
                generateNextId();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save user: " + e.getMessage()).show();
        }
    }

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        UserTM selectedUser = TblUsers.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user first").show();
            return;
        }

        try {
            boolean isDeleted = userBO.deleteUser(selectedUser.getId());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "User deleted successfully").show();
                clearFields();
                loadTableData();
                generateNextId();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete user: " + e.getMessage()).show();
        }
    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        UserTM selectedUser = TblUsers.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a user first").show();
            return;
        }

        if (!validateFields()) {
            return;
        }

        try {
            UserDTO userDTO = new UserDTO(
                    selectedUser.getId(),
                    TxtFirstName.getText(),
                    TxtLastName.getText(),
                    TxtEmail.getText(),
                    TxtUserName.getText(),
                    TxtPassword.getText(),
                    CmbRole.getValue()
            );

            boolean isUpdated = userBO.updateUser(userDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "User updated successfully").show();
                clearFields();
                loadTableData();

            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update user: " + e.getMessage()).show();
        }
    }

    @FXML
    void CmbRoleOnAction(ActionEvent event) {

    }


    /*public void loadAllUsers() {
        ObservableList<UserTM> obList = FXCollections.observableArrayList();
        try {
            List<UserDTO> userList = userBO.getAllUser();
            for (UserDTO userDTO : userList) {
                UserTM tm = new UserTM(
                        userDTO.getUsername(),
                        userDTO.getPassword(),
                        userDTO.getRole(),
                        userDTO.getEmail(),
                        userDTO.getFirstName(),
                        userDTO.getLastName(),
                        userDTO.getId()

                );

                obList.add(tm);
            }

            TblUsers.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }*/


    private void generateNextId() {
        try {
            String nextId = userBO.generateNextUserId();
            txtId.setText(nextId);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate ID: " + e.getMessage()).show();
        }
    }

    private boolean validateFields() {
        if (TxtFirstName.getText().isBlank() ||
                TxtLastName.getText().isBlank() ||
                TxtEmail.getText().isBlank() ||
                TxtUserName.getText().isBlank() ||
                TxtPassword.getText().isBlank() ||
                CmbRole.getValue() == null) {
            return false;
        }
        String defaultStyle = "-fx-border-color: #3498db; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";


        if (!TxtPassword.getText().equals(TxtReEnterPassword.getText())) {
            new Alert(Alert.AlertType.WARNING, "Passwords do not match").show();
            return false;
        }

        return true;
    }

    private void clearFields() {
        TxtFirstName.clear();
        TxtLastName.clear();
        TxtEmail.clear();
        TxtUserName.clear();
        TxtPassword.clear();
        TxtReEnterPassword.clear();
        CmbRole.getSelectionModel().clearSelection();
        loadTableData();
        loadTableData();

        String defaultStyle = "-fx-border-color: #3498db; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtFirstName.setStyle(defaultStyle);
        TxtLastName.setStyle(defaultStyle);
        TxtEmail.setStyle(defaultStyle);
        TxtUserName.setStyle(defaultStyle);
        TxtPassword.setStyle(defaultStyle);
        TxtReEnterPassword.setStyle(defaultStyle);


    }

    private void loadTableData() {
        ObservableList<UserTM> obList = FXCollections.observableArrayList();
        try{
            List<UserDTO> userList = userBO.getAllUser();
            for (UserDTO userDTO : userList) {
                UserTM tm = new UserTM(
                        userDTO.getUsername(),
                        userDTO.getPassword(),
                        userDTO.getRole(),
                        userDTO.getEmail(),
                        userDTO.getFirstName(),
                        userDTO.getLastName(),
                        userDTO.getId()

                );
                obList.add(tm);
            }
            TblUsers.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    @FXML
    void TblUserOnAction(MouseEvent event) {
        try {
            UserTM selectedUser = TblUsers.getSelectionModel().getSelectedItem();

            if (selectedUser == null) {
                return;
            }

            if (event.getClickCount() != 1) {
                return;
            }

            txtId.setText(selectedUser.getId());
            TxtFirstName.setText(selectedUser.getFirstName());
            TxtLastName.setText(selectedUser.getLastName());
            TxtEmail.setText(selectedUser.getEmail());
            TxtUserName.setText(selectedUser.getUserName());

            String password = selectedUser.getPassword();
            TxtPassword.setText(password);
            TxtReEnterPassword.setText(password);

            try {
                User.Role role = User.Role.valueOf(selectedUser.getRole());
                CmbRole.setValue(role);
            } catch (IllegalArgumentException e) {
                CmbRole.setValue(null);
                Logger.getLogger(getClass().getName())
                        .log(Level.WARNING, "Invalid role value: " + selectedUser.getRole(), e);
            }

        } catch (Exception e) {
            Logger.getLogger(getClass().getName())
                    .log(Level.SEVERE, "Error handling user selection", e);
            showAlert(Alert.AlertType.ERROR, "Error",
                    "Failed to load user data: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        ColPw.setCellValueFactory(new PropertyValueFactory<>("password"));
        ColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ColLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        // Set up table selection listener
        TblUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFieldsFromUserTM(newValue);
            }
        });

        loadRoles();
        generateNextId();
        loadTableData();
    }
    private void setFieldsFromUserTM(UserTM userTM) {
        txtId.setText(userTM.getId());
        TxtUserName.setText(userTM.getUserName());
        TxtPassword.setText(userTM.getPassword());
        TxtReEnterPassword.setText(userTM.getPassword());
        TxtEmail.setText(userTM.getEmail());
        TxtFirstName.setText(userTM.getFirstName());
        TxtLastName.setText(userTM.getLastName());

        try {
            CmbRole.setValue(User.Role.valueOf(userTM.getRole()));
        } catch (IllegalArgumentException e) {
            CmbRole.setValue(null);
            Logger.getLogger(getClass().getName())
                    .log(Level.WARNING, "Invalid role value: " + userTM.getRole(), e);
        }
    }

    private void loadRoles(){
        CmbRole.getItems().setAll(User.Role.ADMIN, User.Role.RECEPTIONIST);
    }
}
