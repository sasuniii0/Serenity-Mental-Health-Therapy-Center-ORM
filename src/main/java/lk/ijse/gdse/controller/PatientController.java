package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnHistory;

    @FXML
    private Button BtnRegisterPatient;

    @FXML
    private Button BtnReset;

    @FXML
    private Button BtnSearch;

    @FXML
    private Button BtnUpdate;

    @FXML
    private ComboBox<?> CmbTherapyProgram;

    @FXML
    private TableColumn<?, ?> ColAddress;

    @FXML
    private TableColumn<?, ?> ColContactNumber;

    @FXML
    private TableColumn<?, ?> ColDateOfBirth;

    @FXML
    private TableColumn<?, ?> ColEmail;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColNic;

    @FXML
    private TableColumn<?, ?> ColTherapyProgram;

    @FXML
    private TableColumn<?, ?> ColUpfrontPayment;

    @FXML
    private TableView<?> TblPatient;

    @FXML
    private TextField TxtAddress;

    @FXML
    private TextField TxtContactNumber;

    @FXML
    private Text TxtDate;

    @FXML
    private DatePicker TxtDateOfBirth;

    @FXML
    private TextField TxtEmail;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtName;

    @FXML
    private TextField TxtNic;

    @FXML
    private TextField TxtProgramFee;

    @FXML
    private TextField TxtSearch;

    @FXML
    private TextField TxtUpfrontPayment;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void BtnHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void BtnRegisterPatientOnAction(ActionEvent event) {

    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void CmbTherapyProgramOnAction(ActionEvent event) {

    }

    @FXML
    void TblPatientOnAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
