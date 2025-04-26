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
import lk.ijse.gdse.bo.custom.TherapyProgramManageBO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.dto.tm.TherapyProgramTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapyProgramManageController implements Initializable {

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnReset;

    @FXML
    private Button BtnUpdate;

    @FXML
    private TableColumn<TherapyProgramTM, String> ColDuration;

    @FXML
    private TableColumn<TherapyProgramTM, Double> ColFee;

    @FXML
    private TableColumn<TherapyProgramTM, String> ColId;

    @FXML
    private TableColumn<TherapyProgramTM, String> ColName;

    @FXML
    private TableView<TherapyProgramTM> TblPrograms;

    @FXML
    private Text TxtDate;

    @FXML
    private TextField TxtDuration;

    @FXML
    private TextField TxtFee;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtName;

    @FXML
    private AnchorPane root;

    TherapyProgramManageBO therapyProgramManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);

    @FXML
    void BtnAddOnAction(ActionEvent event) {

    }

    private void clearFields() {

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

    public void TblProgramsOnAction(MouseEvent mouseEvent) {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadAllPrograms() {

    }

    private void setCellValueFactory() {
        }
}
