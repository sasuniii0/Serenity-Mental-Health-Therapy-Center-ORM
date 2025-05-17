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
import lk.ijse.gdse.bo.custom.PatientManageBO;
import lk.ijse.gdse.bo.custom.PaymentManageBO;
import lk.ijse.gdse.bo.custom.TherapyProgramManageBO;
import lk.ijse.gdse.bo.custom.TherapySessionBO;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.dto.tm.PaymentTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {

    @FXML
    private Button BtnPay;

    @FXML
    private Button BtnPay1;

    @FXML
    private Button BtnPay2;

    @FXML
    private Button BtnPrint;

    @FXML
    private TableColumn<PaymentTM, Double> ColAmount;

    @FXML
    private TableColumn<PaymentTM, LocalDate> ColDate;

    @FXML
    private TableColumn<PaymentTM, String> ColDesc;

    @FXML
    private TableColumn<PaymentTM, String> ColId;

    @FXML
    private TableColumn<PaymentTM, String> ColPatientName;

    @FXML
    private TableColumn<PaymentTM, String> ColProgram;

    @FXML
    private TableColumn<PaymentTM, Double> ColRemaining;

    @FXML
    private TableColumn<PaymentTM, String> ColSessionId;

    @FXML
    private TableColumn<PaymentTM, String> ColStatus;

    @FXML
    private RadioButton RadComplete;

    @FXML
    private RadioButton RadOngoing;

    @FXML
    private TableView<PaymentTM> TblPayment;

    @FXML
    private Text TxtDate;

    @FXML
    private Text TxtDesc;

    @FXML
    private Text TxtFee;

    @FXML
    private Text TxtId;

    @FXML
    private Text TxtName;

    @FXML
    private Text TxtProgram;

    @FXML
    private TextField TxtRemainingAmount;

    @FXML
    private AnchorPane root;

    PaymentManageBO paymentManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
    TherapySessionBO therapySessionBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_SESSION);
    PatientManageBO patientManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    TherapyProgramManageBO therapyProgramManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);

    @FXML
    void BtnPayOnAction(ActionEvent event) {

    }

    @FXML
    void BtnPrintOnAction(ActionEvent event) {

    }

    private PaymentTM selectedPayment = null;


    @FXML
    void TblPaymentOnAction(MouseEvent event) {
        if (event.getClickCount() == 1) {
            selectedPayment = TblPayment.getSelectionModel().getSelectedItem();
            if (selectedPayment != null) {
                updateFormWithSelectedPayment();
                enablePaymentControls();
            } else {
                clearForm();
                disablePaymentControls();
            }
        }
    }

    private void updateFormWithSelectedPayment() {
        TxtId.setText(selectedPayment.getId());
        TxtName.setText(selectedPayment.getPatientName());
        TxtProgram.setText(selectedPayment.getProgram());
        TxtDesc.setText(selectedPayment.getDescription());
        TxtDate.setText(selectedPayment.getDate().toString());
        TxtFee.setText(String.format("%.2f", selectedPayment.getAmount()));
        TxtRemainingAmount.setText(String.format("%.2f", selectedPayment.getRemainingAmount()));

        if ("COMPLETE".equalsIgnoreCase(selectedPayment.getStatus())) {
            RadComplete.setSelected(true);
        } else {
            RadOngoing.setSelected(true);
        }
    }

    private void clearForm() {
        TxtId.setText("");
        TxtName.setText("");
        TxtProgram.setText("");
        TxtDesc.setText("");
        TxtDate.setText("");
        TxtFee.setText("");
        TxtRemainingAmount.setText("");
    }

    private void enablePaymentControls() {
        BtnPay1.setDisable(false);
        BtnPay2.setDisable(false);
        TxtRemainingAmount.setDisable(false);
    }

    private void disablePaymentControls() {
        BtnPay1.setDisable(true);
        BtnPay2.setDisable(true);
        TxtRemainingAmount.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        ColPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        ColProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        ColDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ColAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ColRemaining.setCellValueFactory(new PropertyValueFactory<>("remainingAmount"));
        ColStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        String defaultStyle = "-fx-border-color: #3498db; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtRemainingAmount.setStyle(defaultStyle);

        try {
            refreshPage();
            initializeStyles();
            setupRadioButtons();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load payment id").show();
        }

    }
    private void initializeStyles() {
        String defaultStyle = "-fx-border-color: #3498db; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";
        TxtRemainingAmount.setStyle(defaultStyle);
    }

    private void setupRadioButtons() {
        ToggleGroup statusGroup = new ToggleGroup();
        RadComplete.setToggleGroup(statusGroup);
        RadOngoing.setToggleGroup(statusGroup);
    }

    private void refreshPage() {
        loadTableData();

        BtnPay1.setDisable(true);
        BtnPay2.setDisable(true);

        TxtRemainingAmount.setText("");

        String defaultStyle = "-fx-border-color: #3498db; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtRemainingAmount.setStyle(defaultStyle);
    }

    private void loadTableData() {
        ArrayList<PaymentDTO> paymentDTOS = (ArrayList<PaymentDTO>) paymentManageBO.getAllPayments();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            TherapySessionDTO sessionDTO = therapySessionBO.getSessionById(paymentDTO.getSessionId());
            String patientName = null;
            String program = null;
            String desc = null;

            if (sessionDTO != null) {
                patientName = patientManageBO.getPatientNameById(sessionDTO.getPatientId());
                program = therapyProgramManageBO.getProgramNameById(sessionDTO.getProgramId());
                desc = sessionDTO.getDescription();
            }

            if (patientName == null) {
                patientName = "Unknown Patient";
            }

            PaymentTM paymentTM = new PaymentTM(
                    paymentDTO.getId(),
                    paymentDTO.getSessionId(),
                    patientName,
                    program,
                    desc,
                    paymentDTO.getDate(),
                    paymentDTO.getAmount(),
                    paymentDTO.getRemainingAmount(),
                    paymentDTO.getStatus()

            );
            paymentTMS.add(paymentTM);
        }
        TblPayment.setItems(paymentTMS);
    }
}
