package lk.ijse.gdse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminSidePaneController implements Initializable {

    @FXML
    private Button BtnFinacialReport;

    @FXML
    private Button BtnPatientTherapyHistory;

    @FXML
    private Button BtnTrackPayments;

    @FXML
    private AreaChart<?, ?> GraphTherapyPerform;

    @FXML
    private LineChart<?, ?> GraphTherapySession;

    @FXML
    private Pane PanePatient;

    @FXML
    private Pane PaneProgram;

    @FXML
    private Pane PaneTherapist;

    @FXML
    private Pane PaneUser;

    @FXML
    private CategoryAxis XTherapistPerform;

    @FXML
    private CategoryAxis XTherapySession;

    @FXML
    private NumberAxis YAxisTherapistPerform;

    @FXML
    private NumberAxis YTherapySession;

    @FXML
    private AnchorPane root;

    @FXML
    void BtnFinacialReportOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPatientTherapyHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void BtnTrackPaymentsOnAction(ActionEvent event) {

    }

    @FXML
    void PanePatientOnAction(MouseEvent event) {

    }

    @FXML
    void PaneProgramOnAction(MouseEvent event) {

    }

    @FXML
    void PaneTherapistOnAction(MouseEvent event) {

    }

    @FXML
    void PaneUserOnAction(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
