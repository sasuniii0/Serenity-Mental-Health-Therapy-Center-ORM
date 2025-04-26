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
import lk.ijse.gdse.bo.custom.RegistrationBO;
import lk.ijse.gdse.bo.custom.TherapyProgramManageBO;
import lk.ijse.gdse.dto.RegistrationDTO;
import lk.ijse.gdse.dto.tm.RegistrationTM;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
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
    private ComboBox<String > CmbPatient;

    @FXML
    private ComboBox<String> CmbTherapyPrograms;

    @FXML
    private TableColumn<RegistrationTM, Double> ColAdvance;

    @FXML
    private TableColumn<RegistrationTM, LocalDate> ColDate;

    @FXML
    private TableColumn<RegistrationTM, String> ColFee;

    @FXML
    private TableColumn<RegistrationTM, String> ColId;

    @FXML
    private TableColumn<RegistrationTM, String> ColPatient;

    @FXML
    private TableColumn<RegistrationTM, String> ColPatientId;


    @FXML
    private TableColumn<RegistrationTM, String> ColProgram;
    @FXML
    private TableColumn<RegistrationTM, String> ColProgramId;

    @FXML
    private TableView<RegistrationTM> TblRegistration;

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

    TherapyProgramManageBO therapyProgramManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);
    RegistrationBO registrationBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.REGISTRATION);
    PatientManageBO patientManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String regId = TxtRegId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this record?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if(optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = registrationBO.deleteRegistration(regId);
            if(isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Record deleted Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete this record").show();
            }
        }
    }

    @FXML
    void BtnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void BtnRegisterOnAction(ActionEvent event) {
        String id = TxtRegId.getText();
        LocalDate date = LocalDate.parse(TextDate.getText());

        String advance = TxtBalance.getText();

        String programName = (String) CmbTherapyPrograms.getValue();
        String programId = TxtProId.getText();

        String patientName = (String) CmbPatient.getValue();
        String patientId = TxtIdName.getText();

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

        double advancePayment = -1;
        try {
            advancePayment = Double.parseDouble(advance);
            TxtBalance.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtBalance.setStyle(errorStyle);
            errorMessage.append("- Advance Payment is empty or not a valid number\n");
            hasErrors = true;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }
        if(patientName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a patient").show();
            return;
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isSaved = registrationBO.saveRegistration(new RegistrationDTO(id ,patientId , programId , date , advancePayment ));
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Registration saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save registration!").show();
        }
    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = TxtRegId.getText();
        LocalDate date = LocalDate.parse(TextDate.getText());

        String advance = TxtBalance.getText();

        String programName = (String) CmbTherapyPrograms.getValue();
        String programId = TxtProId.getText();

        String patientName = (String) CmbPatient.getValue();
        String patientId = TxtIdName.getText();

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

        double advancePayment = -1;
        try {
            advancePayment = Double.parseDouble(advance);
            TxtBalance.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtBalance.setStyle(errorStyle);
            errorMessage.append("- Advance Payment is empty or not a valid number\n");
            hasErrors = true;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }
        if(patientName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a patient").show();
            return;
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isUpdated = registrationBO.updateRegistration(new RegistrationDTO(id ,patientId , programId , date , advancePayment ));
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Registration saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save registration!").show();
        }
    }

    @FXML
    void BtnViewAllOnAction(ActionEvent event) {

    }

    @FXML
    void CmbPatientOnAction(ActionEvent event) {
        try{
            String selectedPatientName = (String) CmbPatient.getSelectionModel().getSelectedItem();
            if(selectedPatientName != null) {
                String patientId = patientManageBO.getPatientIdByName(selectedPatientName);
                TxtIdName.setText(patientId);
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load program id").show();
        }
    }

    @FXML
    void CmbTherapyProgramsOnAction(ActionEvent event) {
        try{
            String selectedProgramName = (String) CmbTherapyPrograms.getSelectionModel().getSelectedItem();
            if(selectedProgramName != null) {
                String programId = therapyProgramManageBO.getProgramIdByName(selectedProgramName);
                TxtProId.setText(programId);
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load program id").show();
        }
    }

    @FXML
    void TblRegistrationOnAction(MouseEvent event) {
        RegistrationTM registrationTM = TblRegistration.getSelectionModel().getSelectedItem();
        if (registrationTM != null) {
            TxtRegId.setText(registrationTM.getRegistrationId());
            TxtIdName.setText(registrationTM.getPatientId());
            TxtProId.setText(registrationTM.getProgramId());
            CmbPatient.setValue(registrationTM.getPatient());
            CmbTherapyPrograms.setValue(registrationTM.getProgram());
            TxtBalance.setText(String.valueOf(registrationTM.getAdvancePayment()));
            TextDate.setText(String.valueOf(registrationTM.getDate()));
        }

        BtnRegister.setDisable(true);
        BtnDelete.setDisable(false);
        BtnUpdate.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColId.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        ColPatientId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        ColProgramId.setCellValueFactory(new PropertyValueFactory<>("programId"));
        ColPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        ColProgram.setCellValueFactory(new PropertyValueFactory<>("program"));
        ColAdvance.setCellValueFactory(new PropertyValueFactory<>("advancePayment"));
        ColDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TextDate.setText(LocalDate.now().toString());

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        CmbPatient.setStyle(defaultStyle);
        CmbTherapyPrograms.setStyle(defaultStyle);
        TxtBalance.setStyle(defaultStyle);

        try {
            loadPatientNames();
            loadProgramNames();
            refreshPage();

        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load registration id").show();
        }
    }
    private void refreshPage() {
        loadNextRegistrationId();
        loadTableData();

        BtnRegister.setDisable(false);
        BtnDelete.setDisable(true);
        BtnUpdate.setDisable(true);

        TxtBalance.setText("");
        CmbTherapyPrograms.getSelectionModel().clearSelection();
        CmbPatient.getSelectionModel().clearSelection();

        CmbTherapyPrograms.setValue(null);
        CmbPatient.setValue(null);

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtBalance.setStyle(defaultStyle);
        CmbTherapyPrograms.setStyle(defaultStyle);
        CmbPatient.setStyle(defaultStyle);
    }

    private void loadTableData() {
        ArrayList<RegistrationDTO> registrationDTOS = registrationBO.getAllRegistrations();
        ObservableList<RegistrationTM> registrationTMS = FXCollections.observableArrayList();

        for (RegistrationDTO registrationDTO : registrationDTOS) {

            String patientName = patientManageBO.getPatientNameById(registrationDTO.getPatientId());
            String programName = therapyProgramManageBO.getProgramNameById(registrationDTO.getProgramId());

            RegistrationTM registrationTM = new RegistrationTM(
                    registrationDTO.getId(),
                    registrationDTO.getPatientId(),
                    registrationDTO.getProgramId(),
                    patientName,
                    programName,
                    registrationDTO.getAdvancePayment(),
                    registrationDTO.getDate()

            );
            registrationTMS.add(registrationTM);
        }
        TblRegistration.setItems(registrationTMS);
    }

    private void loadNextRegistrationId() {
        String nextRegistrationId = registrationBO.getNextRegistrationId();
        TxtRegId.setText(nextRegistrationId);
    }

    private void loadProgramNames() {
        ArrayList<String> programNames = therapyProgramManageBO.getAllProgramsNames();
        CmbTherapyPrograms.setItems(FXCollections.observableArrayList(programNames));
    }

    private void loadPatientNames() {
        ArrayList<String> patientNames = patientManageBO.getAllPatientNames();
        CmbPatient.setItems(FXCollections.observableArrayList(patientNames));
    }
}
