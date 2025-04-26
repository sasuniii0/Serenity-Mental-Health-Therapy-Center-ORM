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
import lk.ijse.gdse.bo.custom.*;
import lk.ijse.gdse.dto.PaymentDTO;
import lk.ijse.gdse.dto.TherapySessionDTO;
import lk.ijse.gdse.dto.tm.TherapySessionTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SessionController implements Initializable {

    @FXML
    private Button BtnCancelAppoinment;

    @FXML
    private Button BtnMakeAppoinment;

    @FXML
    private Button BtnRescheduleAppoinment;

    @FXML
    private ComboBox<String> CmbPatient;

    @FXML
    private ComboBox<String> CmbStatus;

    @FXML
    private ComboBox<String> CmbTherapist;

    @FXML
    private ComboBox<String> CmbTherapyPrograms;

    @FXML
    private TableColumn<TherapySessionTM, LocalDate> ColDate;

    @FXML
    private TableColumn<TherapySessionTM, String> ColId;

    @FXML
    private TableColumn<TherapySessionTM, String> ColPatient;

    @FXML
    private TableColumn<TherapySessionTM, String> ColPatientId;

    @FXML
    private TableColumn<TherapySessionTM, String> ColProId;

    @FXML
    private TableColumn<TherapySessionTM, LocalDate> ColSessionDate;

    @FXML
    private TableColumn<TherapySessionTM, String> ColStatus;

    @FXML
    private TableColumn<TherapySessionTM, String> ColTherapist;

    @FXML
    private TableColumn<TherapySessionTM, String> ColTherapistId;

    @FXML
    private TableColumn<TherapySessionTM, String> ColTherapyProgram;

    @FXML
    private TextField IdtPatientId;

    @FXML
    private TableView<TherapySessionTM> TblSessionAppoinmnets;

    @FXML
    private TextField TxtAmount;

    @FXML
    private DatePicker TxtAppoinmentDate;

    @FXML
    private Text TxtDate;

    @FXML
    private TextField TxtDescription;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtPayDesc;

    @FXML
    private Text PayId;

    @FXML
    private TextField TxtProId;

    @FXML
    private Text TxtRemaining;

    @FXML
    private TextField TxtTherapistId;

    @FXML
    private AnchorPane root;

    @FXML
    private Text txt;

    TherapyProgramManageBO therapyProgramManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);
    PatientManageBO patientManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    TherapistManageBO therapistManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIST);
    TherapySessionBO therapySessionBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_SESSION);
    PaymentManageBO paymentManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT); //
    ScheduleAppoinmentBO scheduleAppoinmentBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SCHEDULE_APPOINMENT);
    RegistrationBO registrationBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);// PaymentManageBO

    @FXML
    void BtnCancelAppoinmentOnAction(ActionEvent event) {
        String sessionId = TxtId.getText();
        System.out.println("sessionId: " + sessionId);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this Record?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if(optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = therapySessionBO.deleteTherapySession(sessionId);
            if(isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Record deleted Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete this record").show();
            }
        }
    }

    @FXML
    void BtnMakeAppoinmentOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String paymentId = PayId.getText();
        String desc = TxtDescription.getText();
        String paymentDesc = TxtPayDesc.getText();
        String amount = TxtAmount.getText();
        String remaining = TxtRemaining.getText();
        LocalDate date = LocalDate.parse(TxtDate.getText());
        LocalDate sessionDate = TxtAppoinmentDate.getValue();

        String programName = (String) CmbTherapyPrograms.getValue();
        String patientName = (String) CmbPatient.getValue();
        String therpistName = (String) CmbTherapist.getValue();
        String status = (String) CmbStatus.getValue();

        String programId = TxtProId.getText();
        String patientId = IdtPatientId.getText();
        String therpistId = TxtTherapistId.getText();

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

        if(desc.isEmpty() || paymentDesc.isEmpty()) {
            TxtDescription.setStyle(errorStyle);
            TxtPayDesc.setStyle(errorStyle);
            TxtAmount.setStyle(errorStyle);
            errorMessage.append("- Details are empty\n");
            hasErrors = true;
        }else {
            TxtDescription.setStyle(defaultStyle);
            TxtPayDesc.setStyle(defaultStyle);
            TxtAmount.setStyle(defaultStyle);
        }

        double paymentAmount = -1;
        try{
            paymentAmount = Double.parseDouble(amount);
            TxtAmount.setStyle(defaultStyle);
        }catch (NumberFormatException e){
            TxtAmount.setStyle(errorStyle);
            errorMessage.append("- Amount  is empty or not a valid number\n");
            hasErrors = true;
        }

        double remainingAmount = -1;
        remainingAmount = Double.parseDouble(remaining);

        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }
        if(patientName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a patient").show();
            return;
        }
        if(therpistName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a therapist").show();
            return;
        }
        if(status == null){
            new Alert(Alert.AlertType.ERROR, "Please select the status").show();
            return;
        }

        TherapySessionDTO therapySessionDTO = new TherapySessionDTO(id,patientId,programId,therpistId,desc,date,sessionDate);
        PaymentDTO paymentDTO = new PaymentDTO(paymentId,date,paymentAmount,remainingAmount,status,id);

        boolean isSaved = scheduleAppoinmentBO.saveSessionAndPayment(therapySessionDTO , paymentDTO);
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Session and Payment saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save session and payment!").show();
        }
    }

    @FXML
    void BtnRescheduleAppoinmentOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String paymentId = PayId.getText();
        String desc = TxtDescription.getText();
        String paymentDesc = TxtPayDesc.getText();
        String amount = TxtAmount.getText();
        String remaining = TxtRemaining.getText();
        LocalDate date = LocalDate.parse(TxtDate.getText());
        LocalDate sessionDate = TxtAppoinmentDate.getValue();

        String programName = (String) CmbTherapyPrograms.getValue();
        String patientName = (String) CmbPatient.getValue();
        String therpistName = (String) CmbTherapist.getValue();
        String status = (String) CmbStatus.getValue();

        String programId = TxtProId.getText();
        String patientId = IdtPatientId.getText();
        String therpistId = TxtTherapistId.getText();

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

        if(desc.isEmpty() || paymentDesc.isEmpty()) {
            TxtDescription.setStyle(errorStyle);
            TxtPayDesc.setStyle(errorStyle);
            TxtAmount.setStyle(errorStyle);
            errorMessage.append("- Details are empty\n");
            hasErrors = true;
        }else {
            TxtDescription.setStyle(defaultStyle);
            TxtPayDesc.setStyle(defaultStyle);
            TxtAmount.setStyle(defaultStyle);
        }

        double paymentAmount = -1;
        try{
            paymentAmount = Double.parseDouble(amount);
            TxtAmount.setStyle(defaultStyle);
        }catch (NumberFormatException e){
            TxtAmount.setStyle(errorStyle);
            errorMessage.append("- Amount  is empty or not a valid number\n");
            hasErrors = true;
        }

        double remainingAmount = -1;
        remainingAmount = Double.parseDouble(remaining);

        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }
        if(patientName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a patient").show();
            return;
        }
        if(therpistName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a therapist").show();
            return;
        }
        if(status == null){
            new Alert(Alert.AlertType.ERROR, "Please select the status").show();
            return;
        }

        TherapySessionDTO therapySessionDTO = new TherapySessionDTO(id,patientId,programId,therpistId,desc,date,sessionDate);
        PaymentDTO paymentDTO = new PaymentDTO(paymentId,date,paymentAmount,remainingAmount,status,id);

        boolean isUpdated = scheduleAppoinmentBO.updateSessionAndPayment(therapySessionDTO , paymentDTO);
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Session and Payment saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save session and payment!").show();
        }
    }

    @FXML
    void CmbPatient(ActionEvent event) {
        try{
            String selectedPatientName = (String) CmbPatient.getSelectionModel().getSelectedItem();
            if(selectedPatientName != null) {
                String patientId = patientManageBO.getPatientIdByName(selectedPatientName);
                IdtPatientId.setText(patientId);

                List<String> registeredPrograms = therapyProgramManageBO.getRegisteredProgramsByPatientId(patientId);
                CmbTherapyPrograms.setItems(FXCollections.observableArrayList(registeredPrograms));
                CmbTherapyPrograms.getSelectionModel().clearSelection();
                TxtProId.setText("");

                CmbTherapist.getItems().clear();
                CmbTherapist.getSelectionModel().clearSelection();
                TxtTherapistId.setText("");

                updateRemainingAmount();
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load patient id").show();
        }
    }

    private void updateRemainingAmount() {
        String patientId = IdtPatientId.getText();
        String programId = TxtProId.getText();

        if(!patientId.isEmpty() && !programId.isEmpty()) {

            try{
                double programFee = therapyProgramManageBO.getProgramFeeById(programId);
                double advancePayment = registrationBO.getAdvancePaymentByPatientAndProgram(patientId, programId);

                List<PaymentDTO> previousPayments = paymentManageBO.getPaymentsByPatientAndProgram(patientId,programId);
                double remainingAmount;

                double totalPreviousPayments = previousPayments.stream()
                        .mapToDouble(PaymentDTO::getAmount)
                        .sum();

                /*if(previousPayments.isEmpty()){
                    double advancePayment = registrationBO.getAdvancePaymentByPatientAndProgram(patientId,programId);
                    remainingAmount = programFee - advancePayment;
                }else{
                    PaymentDTO lastPayment = previousPayments.get(previousPayments.size() - 1);
                    remainingAmount = lastPayment.getRemainingAmount();
                }*/
                remainingAmount = programFee - (advancePayment + totalPreviousPayments);

                TxtRemaining.setText(String.format("%.2f", remainingAmount));
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Fail to calculate remaining amount").show();
            }
        }
    }

    @FXML
    void CmbStatusOnAction(ActionEvent event) {

    }

    @FXML
    void CmbTherapistOnAction(ActionEvent event) {
        try{
            String selectedTherapistName = (String) CmbTherapist.getSelectionModel().getSelectedItem();
            if(selectedTherapistName != null) {
                String therapistId = therapistManageBO.getTherapistIdByName(selectedTherapistName);
                TxtTherapistId.setText(therapistId);
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load therapist id").show();
        }
    }

    @FXML
    void CmbTherapyProgramsOnAction(ActionEvent event) {
        try{
            String selectedProgramName = (String) CmbTherapyPrograms.getSelectionModel().getSelectedItem();
            if(selectedProgramName != null) {
                String programId = therapyProgramManageBO.getProgramIdByName(selectedProgramName);
                TxtProId.setText(programId);

                loadTherapistByProgram(programId);

                updateRemainingAmount();
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load program id").show();
        }
    }

    private void loadTherapistByProgram(String programId) {
        ArrayList<String> therapistNames = therapistManageBO.getTherapistNamesByProgramId(programId);
        CmbTherapist.setItems(FXCollections.observableArrayList(therapistNames));
        CmbTherapist.getSelectionModel().clearSelection();
    }

    @FXML
    void TblSessionAppoinmnetsOnAction(MouseEvent event) {
        TherapySessionTM therapySessionTM = TblSessionAppoinmnets.getSelectionModel().getSelectedItem();
        if(therapySessionTM != null) {
            TxtId.setText(therapySessionTM.getSessionId());
            TxtDate.setText(String.valueOf(therapySessionTM.getDate()));
            IdtPatientId.setText(therapySessionTM.getPatientId());
            TxtProId.setText(therapySessionTM.getProgramId());
            TxtTherapistId.setText(therapySessionTM.getTherapistId());
            CmbPatient.setValue(therapySessionTM.getPatient());
            CmbTherapyPrograms.setValue(therapySessionTM.getProgram());
            CmbTherapist.setValue(therapySessionTM.getTherapist());
            TxtAppoinmentDate.setValue(therapySessionTM.getSessionDate());

        }

        TxtRemaining.setText("");
        TxtAmount.setDisable(true);
        TxtPayDesc.setDisable(true);
        CmbStatus.setDisable(true);

        BtnMakeAppoinment.setDisable(true);
        BtnCancelAppoinment.setDisable(false);
        BtnRescheduleAppoinment.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        ColPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        ColProId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        ColTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        ColPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        ColTherapyProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        ColTherapist.setCellValueFactory(new PropertyValueFactory<>("therapist"));
        ColSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));

        TxtDate.setText(LocalDate.now().toString());

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        CmbPatient.setStyle(defaultStyle);
        CmbTherapyPrograms.setStyle(defaultStyle);
        CmbTherapist.setStyle(defaultStyle);
        TxtAppoinmentDate.setStyle(defaultStyle);
        TxtDescription.setStyle(defaultStyle);
        TxtAmount.setStyle(defaultStyle);
        TxtPayDesc.setStyle(defaultStyle);
        TxtAppoinmentDate.setStyle(defaultStyle);
        CmbStatus.setStyle(defaultStyle);

        try {
            loadProgramNames();
            loadPatientNames();
            loadTherapistNames();
            loadPaymentStatus();
            refreshPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load therapist id").show();
        }
    }

    private void loadPaymentStatus() {
        ObservableList<String> statusList = FXCollections.observableArrayList("Ongoing", "Completed");
        CmbStatus.setItems(statusList);
    }

    private void refreshPage() {
        loadNextSessionId();
        loadNextPaymentId();
        loadTableData();

        BtnMakeAppoinment.setDisable(false);
        BtnCancelAppoinment.setDisable(true);
        BtnRescheduleAppoinment.setDisable(true);

        TxtDescription.setText("");
        TxtPayDesc.setText("");
        TxtAmount.setText("");

        CmbTherapyPrograms.getSelectionModel().clearSelection();
        CmbPatient.getSelectionModel().clearSelection();
        CmbTherapist.getSelectionModel().clearSelection();
        CmbStatus.getSelectionModel().clearSelection();

        CmbTherapyPrograms.setValue(null);
        CmbPatient.setValue(null);
        CmbTherapist.setValue(null);
        CmbStatus.setValue(null);
        TxtAppoinmentDate.setValue(null);

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtDescription.setStyle(defaultStyle);
        TxtAmount.setStyle(defaultStyle);
        TxtPayDesc.setStyle(defaultStyle);
        CmbStatus.setStyle(defaultStyle);
        CmbPatient.setStyle(defaultStyle);
        CmbTherapist.setStyle(defaultStyle);
        CmbTherapyPrograms.setStyle(defaultStyle);

        TxtRemaining.setText("");
        TxtAmount.setDisable(false);
        TxtPayDesc.setDisable(false);
        CmbStatus.setDisable(false);

    }

    private void loadNextPaymentId() {
        String nextPaymentId = paymentManageBO.getNextPaymentId();
        TxtId.setText(nextPaymentId);
    }

    private void loadTableData() {
        ArrayList<TherapySessionDTO> therapySessionDTOS = therapySessionBO.getAllSessions();
        ObservableList<TherapySessionTM> therapySessionTMS = FXCollections.observableArrayList();

        for(TherapySessionDTO therapySessionDTO : therapySessionDTOS) {

            String programName = therapyProgramManageBO.getProgramNameById(therapySessionDTO.getProgramId());
            String patientName = patientManageBO.getPatientNameById(therapySessionDTO.getPatientId());
            String therapistName = therapistManageBO.getTherapistNameById(therapySessionDTO.getTherapistId());

            TherapySessionTM therapySessionTM = new TherapySessionTM(
                    therapySessionDTO.getId(),
                    therapySessionDTO.getDate(),
                    therapySessionDTO.getPatientId(),
                    therapySessionDTO.getProgramId(),
                    therapySessionDTO.getTherapistId(),
                    patientName,
                    programName,
                    therapistName,
                    therapySessionDTO.getSessionDate()
            );
            therapySessionTMS.add(therapySessionTM);
        }
        TblSessionAppoinmnets.setItems(therapySessionTMS);
    }

    private void loadNextSessionId() {
        String nextSessionId = therapySessionBO.getNextSessionId();
        TxtId.setText(nextSessionId);
    }

    private void loadTherapistNames() {
        ArrayList<String> therapistNames = therapistManageBO.getAllTherapistNames();
        CmbTherapist.setItems(FXCollections.observableArrayList(therapistNames));
    }

    private void loadPatientNames() {
        ArrayList<String> patientNames = patientManageBO.getAllPatientNames();
        CmbPatient.setItems(FXCollections.observableArrayList(patientNames));
    }

    private void loadProgramNames() {
        ArrayList<String> programNames = therapyProgramManageBO.getAllProgramsNames();
        CmbTherapyPrograms.setItems(FXCollections.observableArrayList(programNames));
    }
}
