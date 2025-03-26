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

public class TherapySessionScheduleController implements Initializable {

    @FXML
    private Button BtnAvailability;

    @FXML
    private Button BtnCancelAppoinment;

    @FXML
    private Button BtnMakeAppoinment;

    @FXML
    private Button BtnRescheduleAppoinment;

    @FXML
    private ComboBox<?> CmbPatient;

    @FXML
    private ComboBox<?> CmbTherapist;

    @FXML
    private ComboBox<?> CmbTherapyPrograms;

    @FXML
    private TableColumn<?, ?> ColAmount;

    @FXML
    private TableColumn<?, ?> ColDate;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColPatientId;

    @FXML
    private TableColumn<?, ?> ColStatus;

    @FXML
    private TableColumn<?, ?> ColTherapist;

    @FXML
    private TableColumn<?, ?> ColTherapyProgram;

    @FXML
    private TextField IdtPatientId;

    @FXML
    private TableView<?> TblSessionAppoinmnets;

    @FXML
    private TextField TxtAmount;

    @FXML
    private DatePicker TxtAppoinmentDate;

    @FXML
    private Text TxtDate;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtPatientName;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnAvailabilityOnAction(ActionEvent event) {

    }

    @FXML
    void BtnCancelAppoinmentOnAction(ActionEvent event) {

    }

    @FXML
    void BtnMakeAppoinmentOnAction(ActionEvent event) {

    }

    @FXML
    void BtnRescheduleAppoinmentOnAction(ActionEvent event) {

    }

    @FXML
    void CmbPatient(ActionEvent event) {

    }

    @FXML
    void CmbTherapistOnAction(ActionEvent event) {

    }

    @FXML
    void CmbTherapyProgramsOnAction(ActionEvent event) {

    }

    @FXML
    void TblSessionAppoinmnetsOnAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
