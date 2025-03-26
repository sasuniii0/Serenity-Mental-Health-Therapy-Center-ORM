package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnConfirm;

    @FXML
    private Button BtnPayment;

    @FXML
    private Button BtnRegister;

    @FXML
    private Button BtnViewAll;

    @FXML
    private ComboBox<?> CmbTherapyPrograms;

    @FXML
    private TableColumn<?, ?> ColAdvance;

    @FXML
    private TableColumn<?, ?> ColFee;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColPatient;

    @FXML
    private TableColumn<?, ?> ColProgram;

    @FXML
    private TableView<?> TblRegistration;

    @FXML
    private Text TextDate;

    @FXML
    private TextField TxtBalance;

    @FXML
    private Text TxtDuration;

    @FXML
    private TextField TxtEmail;

    @FXML
    private Text TxtFee;

    @FXML
    private TextField TxtIdName;

    @FXML
    private Text TxtRegId;

    @FXML
    private TextField TxtUpfrontPayment;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnAddOnAction(ActionEvent event) {

    }

    @FXML
    void BtnConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void BtnRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void BtnViewAllOnAction(ActionEvent event) {

    }

    @FXML
    void CmbTherapyProgramsOnAction(ActionEvent event) {

    }

    @FXML
    void TblRegistrationOnAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
