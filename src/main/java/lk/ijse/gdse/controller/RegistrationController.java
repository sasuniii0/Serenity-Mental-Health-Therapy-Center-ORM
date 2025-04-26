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
    private Button BtnDelete;

    @FXML
    private Button BtnPayment;

    @FXML
    private Button BtnRegister;

    @FXML
    private Button BtnReset;

    @FXML
    private Button BtnUpdate;

    @FXML
    private Button BtnViewAll;

    @FXML
    private ComboBox<?> CmbPatient;

    @FXML
    private ComboBox<?> CmbTherapyPrograms;

    @FXML
    private TableColumn<?, ?> ColAdvance;

    @FXML
    private TableColumn<?, ?> ColDate;

    @FXML
    private TableColumn<?, ?> ColFee;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColPatient;

    @FXML
    private TableColumn<?, ?> ColPatientId;

    @FXML
    private TableColumn<?, ?> ColProgram;

    @FXML
    private TableView<?> TblRegistration;

    @FXML
    private Text TextDate;

    @FXML
    private TextField TxtBalance;

    @FXML
    private TextField TxtIdName;

    @FXML
    private Text TxtProId;

    @FXML
    private Text TxtRegId;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void BtnRegisterOnAction(ActionEvent event) {

    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void BtnViewAllOnAction(ActionEvent event) {

    }

    @FXML
    void CmbPatientOnAction(ActionEvent event) {

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
