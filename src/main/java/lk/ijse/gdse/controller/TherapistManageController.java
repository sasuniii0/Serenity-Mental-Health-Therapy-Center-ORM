package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class TherapistManageController {

    @FXML
    private Button BtnAdd;

    @FXML
    private Button BtnDelete;

    @FXML
    private Button BtnReset;

    @FXML
    private Button BtnUpdate;

    @FXML
    private LineChart<?, ?> ChartTherapist;

    @FXML
    private ComboBox<?> CmbTherapyProgram;

    @FXML
    private TableColumn<?, ?> ColEmail;

    @FXML
    private TableColumn<?, ?> ColId;

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColPhone;

    @FXML
    private TableColumn<?, ?> ColProgramId;

    @FXML
    private TableColumn<?, ?> ColTherapyProgarm;

    @FXML
    private TableView<?> TblTherapist;

    @FXML
    private Text TxtDate;

    @FXML
    private TextField TxtEmail;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtName;

    @FXML
    private TextField TxtPhone;

    @FXML
    private TextField TxtProgramId;

    @FXML
    private AnchorPane root;

    @FXML
    private CategoryAxis xaxis;

    @FXML
    private NumberAxis yaxis;

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
    void CmbTherapyProgramOnAction(ActionEvent event) {

    }

    public void TblTherapistOnAction(MouseEvent mouseEvent) {
    }
}
