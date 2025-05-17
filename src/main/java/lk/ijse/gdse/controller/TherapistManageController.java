package lk.ijse.gdse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.gdse.bo.BOFactory;
import lk.ijse.gdse.bo.custom.TherapistManageBO;
import lk.ijse.gdse.bo.custom.TherapyProgramManageBO;
import lk.ijse.gdse.dto.TherapistDTO;
import lk.ijse.gdse.dto.tm.TherapistTM;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapistManageController implements Initializable {

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
    private ComboBox<String> CmbTherapyProgram;

    @FXML
    private TableColumn<TherapistTM, String> ColAddress;

    @FXML
    private TableColumn<TherapistTM, String> ColEmail;

    @FXML
    private TableColumn<TherapistTM, String> ColId;

    @FXML
    private TableColumn<TherapistTM, String> ColName;

    @FXML
    private TableColumn<TherapistTM, Integer> ColPhone;

    @FXML
    private TableColumn<TherapistTM, String> ColProId;

    @FXML
    private TableColumn<TherapistTM, String> ColTherapyProgarm;

    @FXML
    private TableView<TherapistTM> TblTherapist;

    @FXML
    private TextField TxtAddress;

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

    TherapistManageBO therapistManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPIST);
    TherapyProgramManageBO therapyProgramManageBO = BOFactory.getBoFactory().getBO(BOFactory.BOTypes.THERAPY_PROGRAM);

    @FXML
    void BtnAddOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String nic = TxtEmail.getText();
        String address = TxtAddress.getText();
        String mobile = TxtPhone.getText();

        String programName = String.valueOf(CmbTherapyProgram.getValue());
        String programId = TxtProgramId.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

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

        int phone = -1;
        try {
            phone = Integer.parseInt(mobile);
            TxtPhone.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtPhone.setStyle(errorStyle);
            errorMessage.append("- Phone number is empty or not a valid number\n");
            hasErrors = true;
        }

        if (nic.isEmpty() || !nic.matches(nicPattern)) {
            TxtEmail.setStyle(errorStyle);
            errorMessage.append("- NIC is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtEmail.setStyle(defaultStyle);
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }

        boolean isSaved = therapistManageBO.addTherapist(new TherapistDTO(id , programId , name , address , phone , nic ));
        if (isSaved) {
            refreshPage();
            loadNextTherapistId();
            loadTableData();
            new Alert(Alert.AlertType.INFORMATION, "Therapist saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save therapist!").show();
        }

    }

    @FXML
    void BtnDeleteOnAction(ActionEvent event) {
        String therapistId = TxtId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this Therapist?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> optionalButtonType = alert.showAndWait();

        if(optionalButtonType.isPresent() && optionalButtonType.get() == ButtonType.YES) {
            boolean isDeleted = therapistManageBO.deleteTherapist(therapistId);
            if(isDeleted) {
                refreshPage();
                loadTableData();
                loadNextTherapistId();
                new Alert(Alert.AlertType.INFORMATION, "Therapist deleted Successfully").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Failed to delete therapist").show();
            }
        }
    }

    @FXML
    void BtnResetOnAction(ActionEvent event) {
        refreshPage();
    }

    @FXML
    void BtnUpdateOnAction(ActionEvent event) {
        String id = TxtId.getText();
        String name = TxtName.getText();
        String nic = TxtEmail.getText();
        String address = TxtAddress.getText();
        String mobile = TxtPhone.getText();

        String programName = String.valueOf(CmbTherapyProgram.getValue());
        String programId = TxtProgramId.getText();

        String namePattern = "^[A-Za-z ]+$";
        String nicPattern = "^[0-9]{9}[vVxX]$|^[0-9]{12}$";
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        boolean hasErrors = false;
        StringBuilder errorMessage = new StringBuilder("Please correct the following errors:\n");

        String errorStyle = "-fx-border-color: red; -fx-text-fill: black; -fx-background-color: white;";
        String defaultStyle = "-fx-border-color: green; -fx-text-fill: black; -fx-background-color: white;";

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

        int phone = -1;
        try {
            phone = Integer.parseInt(mobile);
            TxtPhone.setStyle(defaultStyle);
        } catch (NumberFormatException e) {
            TxtPhone.setStyle(errorStyle);
            errorMessage.append("- Phone number is empty or not a valid number\n");
            hasErrors = true;
        }

        if (nic.isEmpty() || !nic.matches(nicPattern)) {
            TxtEmail.setStyle(errorStyle);
            errorMessage.append("- NIC is empty or in an incorrect format\n");
            hasErrors = true;

        }else{
            TxtEmail.setStyle(defaultStyle);
        }


        if (hasErrors) {
            new Alert(Alert.AlertType.ERROR, errorMessage.toString()).show();
            return;
        }

        if(programName == null){
            new Alert(Alert.AlertType.ERROR, "Please select a program").show();
            return;
        }

        boolean ipUpdated = therapistManageBO.updateTherapist(new TherapistDTO(id , programId , name , address , phone , nic ));
        if (ipUpdated) {
            refreshPage();
            new Alert(Alert.AlertType.INFORMATION, "Therapist saved successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to save therapist!").show();
        }

    }

    @FXML
    void CmbTherapyProgramOnAction(ActionEvent event) {
        try{
            String selectedProgramName = (String) CmbTherapyProgram.getSelectionModel().getSelectedItem();
            if(selectedProgramName != null) {
                String programId = therapyProgramManageBO.getProgramIdByName(selectedProgramName);
                TxtProgramId.setText(programId);
            }
        }catch(Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to load program id").show();
        }
    }

    @FXML
    void TblTherapistOnAction(MouseEvent event) {
        TherapistTM therapistTM = TblTherapist.getSelectionModel().getSelectedItem();
        if (therapistTM != null) {
            TxtId.setText(therapistTM.getTherapistId());
            TxtName.setText(therapistTM.getName());
            TxtAddress.setText(therapistTM.getAddress());
            TxtEmail.setText(therapistTM.getNic());
            TxtPhone.setText(String.valueOf(therapistTM.getMobile()));
            CmbTherapyProgram.setValue(therapistTM.getProgram());
            TxtProgramId.setText(therapistTM.getProgramId());
        }

        BtnAdd.setDisable(true);
        BtnDelete.setDisable(false);
        BtnUpdate.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ColId.setCellValueFactory(new PropertyValueFactory<>("therapistId"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ColEmail.setCellValueFactory(new PropertyValueFactory<>("nic"));
        ColPhone.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        ColTherapyProgarm.setCellValueFactory(new PropertyValueFactory<>("program"));
        ColProId.setCellValueFactory(new PropertyValueFactory<>("programId"));

        String defaultStyle = "-fx-border-color: #3498db; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtName.setStyle(defaultStyle);
        TxtAddress.setStyle(defaultStyle);
        TxtPhone.setStyle(defaultStyle);
        TxtEmail.setStyle(defaultStyle);
        CmbTherapyProgram.setStyle(defaultStyle);

        loadProgramNames();
        loadNextTherapistId();
        loadTableData();
    }

    private void loadProgramNames() {
        ArrayList<String> programNames = therapyProgramManageBO.getAllProgramsNames();
        CmbTherapyProgram.setItems(FXCollections.observableArrayList(programNames));
    }

    private void refreshPage() {
        loadNextTherapistId();
        loadTableData();

        BtnAdd.setDisable(false);
        BtnDelete.setDisable(true);
        BtnUpdate.setDisable(true);

        TxtName.setText("");
        TxtAddress.setText("");
        TxtPhone.setText("");
        TxtEmail.setText("");
        TxtProgramId.setText("");

        CmbTherapyProgram.getSelectionModel().clearSelection();
        CmbTherapyProgram.setValue(null);

        String defaultStyle = "-fx-border-color: #3498db; -fx-text-fill: black; -fx-background-color: white; -fx-border-width: 2px;";

        TxtName.setStyle(defaultStyle);
        TxtAddress.setStyle(defaultStyle);
        TxtPhone.setStyle(defaultStyle);
        TxtEmail.setStyle(defaultStyle);
        CmbTherapyProgram.setStyle(defaultStyle);

    }

    private void loadTableData() {
        ArrayList<TherapistDTO> therapistDTOS = (ArrayList<TherapistDTO>) therapistManageBO.getAllTherapist();
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

        for(TherapistDTO therapistDTO : therapistDTOS) {

            String programName = therapyProgramManageBO.getProgramNameById(therapistDTO.getProgramId());

            TherapistTM therapistTM = new TherapistTM(
                    therapistDTO.getId(),
                    therapistDTO.getName(),
                    therapistDTO.getAddress(),
                    therapistDTO.getNic(),
                    therapistDTO.getMobileNumber(),
                    programName,
                    therapistDTO.getProgramId()
            );
            therapistTMS.add(therapistTM);
        }
        TblTherapist.setItems(therapistTMS);

    }
    private void loadNextTherapistId() {
        String nextTherapistId = therapistManageBO.getNextTherapistId();
        TxtId.setText(nextTherapistId);
    }

}
