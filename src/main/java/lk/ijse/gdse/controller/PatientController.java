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
import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.dto.PatientDTO;
import lk.ijse.gdse.dto.TherapyProgramDTO;
import lk.ijse.gdse.dto.tm.PatientTM;
import lk.ijse.gdse.entity.Patient;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

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
    private ComboBox<String> CmbTherapyProgram;

    @FXML
    private TableColumn<PatientTM, String> ColAddress;

    @FXML
    private TableColumn<PatientTM, String> ColContactNumber;

    @FXML
    private TableColumn<PatientTM, Date> ColDateOfBirth;

    @FXML
    private TableColumn<PatientTM, String> ColEmail;

    @FXML
    private TableColumn<PatientTM, String> ColId;

    @FXML
    private TableColumn<PatientTM, String> ColName;

    @FXML
    private TableColumn<PatientTM, String> ColNic;

    @FXML
    private TableColumn<PatientTM, String> ColTherapyProgram;

    @FXML
    private TableColumn<PatientTM, Double> ColUpfrontPayment;

    @FXML
    private TableView<PatientTM> TblPatient;

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

    private Map<String, TherapyProgramDTO> programMap = new HashMap<>();


    PatientManageBO patientManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    TherapistManageBO therapyProgramBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIST);

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String id = TxtId.getText();

        try {
            boolean isDeleted = patientManageBO.deletePatient(id);
            if (isDeleted) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete Patient");
                alert.setHeaderText("Patient Deleted");
                alert.setContentText("Patient has been deleted successfully."); // Replace with the appropriate message
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void BtnHistoryOnAction(ActionEvent event) {

    }

    @FXML
    void BtnRegisterPatientOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String nic = TxtNic.getText();
        String address = TxtAddress.getText();
        String contactNumber = TxtContactNumber.getText();
        String email = TxtEmail.getText();
        LocalDate dateOfBirth = TxtDateOfBirth.getValue();
        Date dob = java.sql.Date.valueOf(dateOfBirth);
        String therapyProgramName = CmbTherapyProgram.getSelectionModel().getSelectedItem();

        Double upfrontPayment = Double.parseDouble(TxtUpfrontPayment.getText());
        TherapyProgramDTO selectedProgram = programMap.get(therapyProgramName);

        if (selectedProgram == null) {
            System.out.println("No program found for: " + therapyProgramName);
            return;
        }

        Double fullProgramFee = selectedProgram.getFee();
        Double remainingBalance = fullProgramFee - upfrontPayment;

        try {
            // Optional: you could include `remainingBalance` in the DTO if needed
            PatientDTO patientDTO = new PatientDTO(
                    id,
                    name,
                    nic,
                    address,
                    contactNumber,
                    email,
                    dob,
                    therapyProgramName,
                    upfrontPayment
            );

            boolean isAdded = patientManageBO.addPatient(patientDTO);
            if (isAdded) {
                TxtId.setText("");
                TxtName.setText("");
                TxtNic.setText("");
                TxtAddress.setText("");
                TxtContactNumber.setText("");
                TxtEmail.setText("");
                TxtDate.setText("");
                CmbTherapyProgram.getSelectionModel().clearSelection();
                TxtUpfrontPayment.setText("");
                loadAllPatient();

                // Optional: Show success and remaining balance
                new Alert(Alert.AlertType.INFORMATION, "Patient registered. Remaining balance: " + remainingBalance).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnSearchOnAction(ActionEvent event) {
        String searchId = TxtSearch.getText();

        try {
            PatientDTO patient = (PatientDTO) patientManageBO.searchPatient(searchId);
            if (patient != null) {
                TxtId.setText(patient.getId());
                TxtName.setText(patient.getName());
                TxtNic.setText(patient.getNic());
                TxtAddress.setText(patient.getAddress());
                TxtContactNumber.setText(patient.getContactNumber());
                TxtEmail.setText(patient.getEmail());
                TxtDate.setText(String.valueOf(patient.getDateOfBirth()));
                TxtUpfrontPayment.setText(String.valueOf(patient.getUpfrontPayment()));
                CmbTherapyProgram.setValue(patient.getTherapyPrograms());

                // Fill other fields as needed
            } else {
                new Alert(Alert.AlertType.WARNING, "Patient not found!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error occurred while searching!").show();
        }
    }


    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String nic = TxtNic.getText();
        String address = TxtAddress.getText();
        String contactNumber = TxtContactNumber.getText();
        String email = TxtEmail.getText();
        LocalDate dateOfBirth = TxtDateOfBirth.getValue();
        Date dob = java.sql.Date.valueOf(dateOfBirth);
        String therapyProgramName = CmbTherapyProgram.getSelectionModel().getSelectedItem();

        Double upfrontPayment = Double.parseDouble(TxtUpfrontPayment.getText());
        TherapyProgramDTO selectedProgram = programMap.get(therapyProgramName);
        if (selectedProgram == null) {
            System.out.println("No program found for: " + therapyProgramName);
            return;
        }

        Double fullProgramFee = selectedProgram.getFee();
        Double remainingBalance = fullProgramFee - upfrontPayment;

        try {
            PatientDTO patientDTO = new PatientDTO(
                    id,
                    name,
                    nic,
                    address,
                    contactNumber,
                    email,
                    dob,
                    therapyProgramName,
                    upfrontPayment
            );

            boolean isUpdated = patientManageBO.updatePatient(patientDTO);
            if (isUpdated) {
                TxtId.setText("");
                TxtName.setText("");
                TxtNic.setText("");
                TxtAddress.setText("");
                TxtContactNumber.setText("");
                TxtEmail.setText("");
                TxtDate.setText("");
                CmbTherapyProgram.getSelectionModel().clearSelection();
                TxtUpfrontPayment.setText("");
                loadAllPatient();

                // Optional: Show success and remaining balance
                new Alert(Alert.AlertType.INFORMATION, "Patient updated. Remaining balance: " + remainingBalance).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void CmbTherapyProgramOnAction(ActionEvent event) {
        CmbTherapyProgram.getSelectionModel().getSelectedItem();
        String selectedProgramName = CmbTherapyProgram.getSelectionModel().getSelectedItem();
        TherapyProgramDTO selectedProgram = programMap.get(selectedProgramName);
        if (selectedProgram != null) {
            TxtProgramFee.setText(String.valueOf(selectedProgram.getFee()));
        }

    }

    private void loadPrograms() {
        try {
            List<TherapyProgramDTO> therapyPrograms = therapyProgramBO.getAllTherapyPrograms();
            for (TherapyProgramDTO therapyProgram : therapyPrograms) {
                programMap.put(therapyProgram.getName(), therapyProgram);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    @FXML
    void TblPatientOnAction(MouseEvent event) {
        TblPatient.getSelectionModel().getSelectedItem();
        PatientTM selectedPatient = TblPatient.getSelectionModel().getSelectedItem();

        if (selectedPatient != null) {
            TxtId.setText(selectedPatient.getId());
            TxtName.setText(selectedPatient.getName());
            TxtNic.setText(selectedPatient.getNic());
            TxtAddress.setText(selectedPatient.getAddress());
            TxtContactNumber.setText(selectedPatient.getContactNumber());
            TxtEmail.setText(selectedPatient.getEmail());
            TxtDate.setText(String.valueOf(selectedPatient.getDateOfBirth()));
            CmbTherapyProgram.setValue(selectedPatient.getTherapyPrograms());
            TxtUpfrontPayment.setText(String.valueOf(selectedPatient.getUpfrontPayment()));
        }

    }

    public void loadTableData() {
        ObservableList<PatientTM> obList = FXCollections.observableArrayList();

        List<PatientDTO> allPatient = patientManageBO.getAllPatient();
        for (PatientDTO patient : allPatient) {
            obList.add(new PatientTM(
                    patient.getId(),
                    patient.getName(),
                    patient.getNic(),
                    patient.getAddress(),
                    patient.getContactNumber(),
                    patient.getEmail(),
                    patient.getDateOfBirth(),
                    patient.getTherapyPrograms(),
                    String.valueOf(patient.getUpfrontPayment())
            ));
        }
        TblPatient.setItems(obList);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TxtDate.setText(String.valueOf(LocalDate.now()));
        setCellValueFactory();
        loadPrograms();
        loadAllPatient();
        loadTableData();
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String programName : programMap.keySet()) {
            obList.add(programName);
        }
        CmbTherapyProgram.setItems(obList);
    }

    private void loadAllPatient() {
        ObservableList<PatientTM> obList = FXCollections.observableArrayList();

        List<PatientDTO> allPatient = patientManageBO.getAllPatient();
    }

    public void setCellValueFactory() {
        ColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColDateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        ColTherapyProgram.setCellValueFactory(new PropertyValueFactory<>("therapyPrograms"));
        ColUpfrontPayment.setCellValueFactory(new PropertyValueFactory<>("upfrontPayment"));
    }
}
