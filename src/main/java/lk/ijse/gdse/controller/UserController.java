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
import javafx.scene.text.Text;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.UserBO;
import lk.ijse.gdse.dto.UserDTO;
import lk.ijse.gdse.dto.tm.UserTM;

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
    private TableColumn<UserTM, String> ColFiirstName;

    @FXML
    private TableColumn<UserTM, String> ColId;

    @FXML
    private TableColumn<UserTM, String> ColLastName;

    @FXML
    private TableColumn<UserTM, String> ColPwd;

    @FXML
    private TableColumn<UserTM, String> ColRole;

    @FXML
    private TableView<UserTM> TblUsers;

    @FXML
    private Text TxtDate;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtFirstName;

    @FXML
    private Text TxtId;

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

    }

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void CmbRoleOnAction(ActionEvent event) {

    }

    public void TblUserOnAction(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllUsers();
    }

    private void loadAllUsers() {
        ObservableList<UserTM> obList = FXCollections.observableArrayList();
        try{
            List<UserDTO> userDTOS = userBO.getAllUser();
            for (UserDTO userDTO : userDTOS) {
                obList.add(new UserTM(userDTO.getId(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getFirstName(),userDTO.getLastName(),userDTO.getRole()));
            }
            TblUsers.setItems(obList);
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setCellValueFactory(){
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColFiirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        ColLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        ColRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        ColPwd.setCellValueFactory(new PropertyValueFactory<>("password"));
    }
}
