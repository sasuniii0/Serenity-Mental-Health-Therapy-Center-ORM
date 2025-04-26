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
import lk.ijse.gdse.dto.PatientDTO;
import lk.ijse.gdse.dto.tm.PatientTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private TableColumn<PatientTM, String> ColAddress;

    @FXML
    private TableColumn<PatientTM, Integer> ColContactNumber;

    @FXML
    private TableColumn<PatientTM, String> ColEmail;

    @FXML
    private TableColumn<PatientTM, String> ColGender;

    @FXML
    private TableColumn<PatientTM, String> ColId;

    @FXML
    private TableColumn<PatientTM, String> ColName;

    @FXML
    private TableColumn<PatientTM, String> ColNic;

    @FXML
    private RadioButton RadFemale;

    @FXML
    private RadioButton RadMale;

    @FXML
    private TableView<PatientTM> TblPatient;

    @FXML
    private TextField TxtAddress;

    @FXML
    private TextField TxtContactNumber;

    @FXML
    private Text TxtDate;

    @FXML
    private TextField TxtEmail;

    @FXML
    private Text TxtId;

    @FXML
    private TextField TxtName;

    @FXML
    private TextField TxtNic;

    @FXML
    private TextField TxtSearch;

    @FXML
    private AnchorPane root;

    PatientManageBO patientBO =  BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PATIENT);
    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String patientId = TxtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this Patient?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if(optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = patientBO.deletePatient(patientId);
            if(isDeleted) {
                refreshPage();
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete patient").show();
            }
        }
    }

    private void refreshPage() {
        loadNextPatientId();
        loadTableData();

        BtnRegisterPatient.setDisable(false);
        BtnDelete.setDisable(true);
        BtnUpdate.setDisable(true);

        TxtName.setText("");
        TxtAddress.setText("");
        TxtEmail.setText("");
        TxtContactNumber.setText("");
        TxtNic.setText("");
        RadMale.setSelected(false);
        RadFemale.setSelected(false);

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtName.setStyle(defaultStyle);
        TxtAddress.setStyle(defaultStyle);
        TxtEmail.setStyle(defaultStyle);
        TxtContactNumber.setStyle(defaultStyle);
        TxtNic.setStyle(defaultStyle);
    }

    private void loadTableData() {
        ArrayList<PatientDTO> patientDTOS = (ArrayList<PatientDTO>) patientBO.getAllPatient();
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for(PatientDTO patientDTO : patientDTOS){
            PatientTM patientTM = new PatientTM(
                    patientDTO.getId(),
                    patientDTO.getName(),
                    patientDTO.getAddress(),
                    patientDTO.getNic(),
                    patientDTO.getEmail(),
                    patientDTO.getMobileNumber(),
                    patientDTO.getGender()
            );
            patientTMS.add(patientTM);
        }
        TblPatient.setItems(patientTMS);
    }

    private void loadNextPatientId() {
        String nextPatientId = patientBO.getNextPatientId();
        TxtId.setText(nextPatientId);
    }
    @FXML
    void BtnHistoryOnAction(ActionEvent event) {

    }

    private void searchPatient () {
        String searchText = TxtSearch.getText().trim();

        patientBO.searchPatient(searchText);

        if(searchText.isEmpty()){
            loadTableData();
            return;
        }
        List<PatientDTO> patientDTOS = patientBO.searchPatient(searchText);
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for(PatientDTO patientDTO : patientDTOS){
            PatientTM patientTM = new PatientTM(
                    patientDTO.getId(),
                    patientDTO.getName(),
                    patientDTO.getAddress(),
                    patientDTO.getNic(),
                    patientDTO.getEmail(),
                    patientDTO.getMobileNumber(),
                    patientDTO.getGender()
            );
            patientTMS.add(patientTM);
        }
        TblPatient.setItems(patientTMS);
    }
    @FXML
    void BtnRegisterPatientOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String nic = TxtNic.getText();
        String address = TxtAddress.getText();
        String email = TxtEmail.getText();
        String mobile = TxtContactNumber.getText();
        String gender = RadFemale.isSelected() ? "Female" : "Male";

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: white; -fx-background-color: transparent;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: white; -fx-background-color: transparent;";


        if (name.isEmpty() || !name.matches(namePattern)) {
            TxtName.setStyle(errorStyle);
            errorMessage.append("- Name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtName.setStyle(defaultStyle);
        }
        if (address.isEmpty()) {
            TxtAddress.setStyle(errorStyle);
            errorMessage.append("- Address is empty\n");
            hasErrors = true;

        }else{
            TxtAddress.setStyle(defaultStyle);
        }
        if (email.isEmpty() || !email.matches(emailPattern)) {
            TxtEmail.setStyle(errorStyle);
            errorMessage.append("- Email is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtEmail.setStyle(defaultStyle);
        }

        int phone = -1;
        try {
            phone = Integer.parseInt(mobile);
            TxtContactNumber.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtContactNumber.setStyle(errorStyle);
            errorMessage.append("- Phone number is empty or not a valid number\n");
            hasErrors = true;
        }

        if (nic.isEmpty() || !nic.matches(nicPattern)) {
            TxtNic.setStyle(errorStyle);
            errorMessage.append("- NIC is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtNic.setStyle(defaultStyle);
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isSaved = patientBO.addPatient(new PatientDTO(id , name , address , email , phone , nic , gender));
        if (isSaved) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Patient saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save patient!").show();
        }


    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {

    }

    @FXML
    void BtnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String nic = TxtNic.getText();
        String address = TxtAddress.getText();
        String email = TxtEmail.getText();
        String mobile = TxtContactNumber.getText();
        String gender = RadFemale.isSelected() ? "Female" : "Male";

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: white; -fx-background-color: transparent;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: white; -fx-background-color: transparent;";


        if (name.isEmpty() || !name.matches(namePattern)) {
            TxtName.setStyle(errorStyle);
            errorMessage.append("- Name is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtName.setStyle(defaultStyle);
        }
        if (address.isEmpty()) {
            TxtAddress.setStyle(errorStyle);
            errorMessage.append("- Address is empty\n");
            hasErrors = true;

        }else{
            TxtAddress.setStyle(defaultStyle);
        }
        if (email.isEmpty() || !email.matches(emailPattern)) {
            TxtEmail.setStyle(errorStyle);
            errorMessage.append("- Email is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtEmail.setStyle(defaultStyle);
        }

        int phone = -1;
        try {
            phone = Integer.parseInt(mobile);
            TxtContactNumber.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtContactNumber.setStyle(errorStyle);
            errorMessage.append("- Phone number is empty or not a valid number\n");
            hasErrors = true;
        }

        if (nic.isEmpty() || !nic.matches(nicPattern)) {
            TxtNic.setStyle(errorStyle);
            errorMessage.append("- NIC is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtNic.setStyle(defaultStyle);
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        boolean isUpdated = patientBO.updatePatient(new PatientDTO(id , name , address , email , phone , nic , gender));
        if (isUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Patient Updated successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to Updated patient!").show();
        }


    }

    @FXML
    void TblPatientOnAction(MouseEvent event) {
        PatientTM patientTM = TblPatient.getSelectionModel().getSelectedItem();
        if (patientTM != null) {
            TxtId.setText(patientTM.getPatientId());
            TxtName.setText(patientTM.getPatientName());
            TxtAddress.setText(patientTM.getAddress());
            TxtNic.setText(patientTM.getNic());
            TxtEmail.setText(patientTM.getEmail());
            TxtContactNumber.setText(String.valueOf(patientTM.getMobile()));

            if(patientTM.getGender().equalsIgnoreCase("Female")){
                RadFemale.setSelected(true);
                RadMale.setSelected(false);
            }else{
                RadMale.setSelected(true);
                RadFemale.setSelected(false);
            }

            BtnRegisterPatient.setDisable(true);
            BtnDelete.setDisable(false);
            BtnUpdate.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColId.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        ColContactNumber.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        ColGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        String defaultStyle = "-fx-border-color: yellow; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtName.setStyle(defaultStyle);
        TxtAddress.setStyle(defaultStyle);
        TxtEmail.setStyle(defaultStyle);
        TxtContactNumber.setStyle(defaultStyle);
        TxtNic.setStyle(defaultStyle);

        TxtSearch.setOnAction(event ->{
            try{
                searchPatient();
            }catch (Exception e){
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Error searching patient").show();
            }
        });

        try{
            refreshPage();
        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load patient id").show();
        }
    }
}
