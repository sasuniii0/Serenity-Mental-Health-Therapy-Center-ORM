package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;
import lk.ijse.gdse.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnReset;

    @FXML
    private Button BtnUpdate;

    @FXML
    private ComboBox<String> CmbRole;

    @FXML
    private TableColumn<UserTM, String> ColEmail;

    @FXML
    private TableColumn<UserTM, String> ColFirstName;

    @FXML
    private TableColumn<UserTM, String> ColId;

    @FXML
    private TableColumn<UserTM, String> ColLastName;

    @FXML
    private TableColumn<UserTM, String> ColPw;

    @FXML
    private TableColumn<UserTM, String> ColRole;

    @FXML
    private TableView<UserTM> TblUsers;

    @FXML
    private Label txtDate;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtFirstName;

    @FXML
    private Label txtId;

    @FXML
    private TextField TxtLastName;

    @FXML
    private PasswordField TxtPassword;

    @FXML
    private PasswordField TxtReEnterPassword;

    @FXML
    private AnchorPane root;

    UserBO userBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void BtnAddOnAction(ActionEvent event) {
        String id = txtId.getText();
        String email = TxtEmail.getText();
        String firstName = TxtFirstName.getText();
        String lastName = TxtLastName.getText();
        String password = TxtPassword.getText();
        String reEnterPassword = TxtReEnterPassword.getText();
        String role = CmbRole.getValue();
        try {
            UserDTO userDTO = new UserDTO(id, email, firstName, lastName, password, role);
            boolean isAdded = userBO.addUser(userDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "User Added Successfully").show();
                loadAllUsers();
                clearFields();
                userBO.generateNextUserId();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to add user").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String email = TxtEmail.getText();
        try {
            boolean isDeleted = userBO.deleteUser(email);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "User Deleted Successfully").show();
                loadAllUsers();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to delete user").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearFields() {
        TxtEmail.clear();
        TxtFirstName.clear();
        TxtLastName.clear();
        TxtPassword.clear();
        TxtReEnterPassword.clear();
    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String email = TxtEmail.getText();
        String firstName = TxtFirstName.getText();
        String lastName = TxtLastName.getText();
        String password = TxtPassword.getText();
        String role = CmbRole.getValue();
        try {
            UserDTO userDTO = new UserDTO(id, email, firstName, lastName, password, role);
            boolean isAdded = userBO.updateUser(userDTO);
            if (isAdded) {
                new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully").show();
                loadAllUsers();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Update user").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    User userSelected;

    @FXML
    void CmbRoleOnAction(ActionEvent event) {
        String role = CmbRole.getValue();
        if (role.equals("Admin")) {
            TxtPassword.setDisable(false);
            TxtReEnterPassword.setDisable(false);
        } else {
            TxtPassword.setDisable(true);
            TxtReEnterPassword.setDisable(true);
        }
    }

    public void TblUserOnAction(MouseEvent mouseEvent) {
        String id = TblUsers.getSelectionModel().getSelectedItem().getEmail();
        try {
            userSelected = userBO.searchUserByEmail(id);
            if (userSelected != null) {
                TxtEmail.setText(userSelected.getEmail());
                TxtFirstName.setText(userSelected.getFirstName());
                TxtLastName.setText(userSelected.getLastName());
                TxtPassword.setText(userSelected.getPassword());
                CmbRole.setValue(String.valueOf(userSelected.getRole()));
            } else {
                new Alert(Alert.AlertType.INFORMATION, "User not found!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllUsers();
        loadRoles();

        String id;
        try {
            id = userBO.generateNextUserId();
            System.out.println("Generated ID: " + userBO.generateNextUserId());
            txtId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate ID").show();
        }
    }

    private void loadRoles() {
        ObservableList<String> roles = FXCollections.observableArrayList(
                "Admin",
                "Receptionist"
        );
        CmbRole.setItems(roles);
    }

    private void loadAllUsers() {
        ObservableList<UserTM> obList = FXCollections.observableArrayList();

        try {
            List<UserDTO> userDTOS = userBO.getAllUser();

            for (UserDTO userDTO : userDTOS) {
                System.out.println(userDTO.getEmail()); // Debug print

                obList.add(new UserTM(
                        userDTO.getId(),
                        userDTO.getEmail(),
                        userDTO.getPassword(),
                        userDTO.getFirstName(),
                        userDTO.getLastName(),
                        userDTO.getRole()
                ));
            }
            TblUsers.setItems(obList);
        } catch (Exception e) {
            e.printStackTrace(); // For dev/debugging
            new Alert(Alert.AlertType.ERROR, "Failed to load user data: " + e.getMessage()).show();
        }
    }


    private void setCellValueFactory(){
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColPw.setCellValueFactory(new PropertyValueFactory<>("password"));
        ColFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ColLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

}
